package com.liyz.dubbo.service.search.service.abs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.common.collect.Maps;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.common.util.deserializer.DateTimeDeserializer;
import com.liyz.dubbo.common.util.serializer.DoubleSerializer;
import com.liyz.dubbo.service.search.bo.BaseBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.exception.RemoteSearchServiceException;
import com.liyz.dubbo.service.search.properties.BaseProperties;
import com.liyz.dubbo.service.search.query.BasePageQuery;
import com.liyz.dubbo.service.search.service.SearchService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:24
 */
public abstract class AbstractSearchService<BO extends BaseBO, BaseQuery extends BasePageQuery> implements SearchService<BO, BaseQuery>, ApplicationListener<ContextRefreshedEvent> {

    private static final Map<SearchType, SearchService<BaseBO, BasePageQuery>> SEARCH_TYPE_MAP = Maps.newEnumMap(SearchType.class);
    protected final Class<BO> boClass;
    protected final Class<BaseQuery> queryClass;
    protected BaseProperties properties;
    public static ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
            .json()
            .createXmlMapper(false)
            .dateFormat(new SimpleDateFormat(DateUtil.PATTERN_DATE_TIME))
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .timeZone(TimeZone.getTimeZone(DateUtil.TIME_ZONE_GMT8))
            .build()
            .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
            .registerModule(new SimpleModule()
                    .addSerializer(Long.class, ToStringSerializer.instance)
                    .addSerializer(Long.TYPE, ToStringSerializer.instance)
                    .addSerializer(Double.class, new DoubleSerializer())
                    .addSerializer(Double.TYPE, new DoubleSerializer())
                    .addDeserializer(Date.class, new DateTimeDeserializer()));

    public AbstractSearchService() {
        Type genType = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genType;
        this.boClass = (Class<BO>) parameterizedType.getActualTypeArguments()[0];
        this.queryClass = (Class<BaseQuery>) parameterizedType.getActualTypeArguments()[1];
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        SEARCH_TYPE_MAP.put(this.getSearchType(), applicationContext.getBean(this.getClass()));
        this.properties = BaseProperties.SEARCH_TYPE_MAP.get(this.getSearchType());
    }

    public static SearchService<BaseBO, BasePageQuery> getSearchService(SearchType searchType) {
        return getSearchService(searchType, false);
    }

    public static SearchService<BaseBO, BasePageQuery> getSearchService(SearchType searchType, boolean noServiceException) {
        SearchService<BaseBO, BasePageQuery> service = SEARCH_TYPE_MAP.get(searchType);
        if (noServiceException && Objects.isNull(service)) {
            throw new RemoteSearchServiceException(CommonExceptionCodeEnum.PARAMS_VALIDATED);
        }
        return service;
    }

    @SneakyThrows
    protected static <T> T readValue(String content, Class<T> clazz) {
        if (StringUtils.isBlank(content) || Objects.isNull(clazz)) {
            return null;
        }
        return objectMapper.readValue(content, clazz);
    }

    /**
     * 获取搜索类型
     *
     * @return 搜索类型
     */
    protected abstract SearchType getSearchType();
}
