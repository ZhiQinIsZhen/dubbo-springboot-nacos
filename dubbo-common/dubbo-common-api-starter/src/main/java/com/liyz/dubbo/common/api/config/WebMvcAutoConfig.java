package com.liyz.dubbo.common.api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.liyz.dubbo.common.api.advice.GlobalControllerExceptionAdvice;
import com.liyz.dubbo.common.api.advice.LyzApiResponseBodyAdvice;
import com.liyz.dubbo.common.api.deserializer.JsonTrimDeserializer;
import com.liyz.dubbo.common.api.error.ErrorApiController;
import com.liyz.dubbo.common.api.resolver.AuthUserArgumentResolver;
import com.liyz.dubbo.common.desensitize.filter.JacksonDesensitizationContextValueFilter;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.common.util.serializer.DoubleSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * Desc:mvc auto config
 * <p>这里注意使用{@link WebMvcConfigurer}与{@link WebMvcConfigurationSupport}区别
 * 主要区别在于{@link org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration}中的
 * condition {ConditionalOnMissingBean({WebMvcConfigurationSupport.class})}，使用support则springboot原生的config则不会
 * 创建，并且在{@link org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration}
 * 的父类{@link DelegatingWebMvcConfiguration}中会找出所有的{@link WebMvcConfigurer}进行逐步配置
 * </p>
 *
 * 注: 所以建议大家使用{@link WebMvcConfigurer}来或者自己的mvc配置
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 10:30
 */
@Slf4j
@Configuration
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
public class WebMvcAutoConfig implements WebMvcConfigurer {

    public WebMvcAutoConfig() {
        log.info("module dubbo-common-api-starter init");
    }

    @Bean
    public GlobalControllerExceptionAdvice globalControllerExceptionAdvice() {
        return new GlobalControllerExceptionAdvice();
    }

    @Bean
    public ErrorApiController errorApiController(ServerProperties serverProperties) {
        return new ErrorApiController(serverProperties);
    }

    @Bean
    public LyzApiResponseBodyAdvice lyzApiResponseBodyAdvice() {
        return new LyzApiResponseBodyAdvice();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        if (!CollectionUtils.isEmpty(converters)) {
            Optional<HttpMessageConverter<?>> optional = converters.stream().filter(item -> item instanceof MappingJackson2HttpMessageConverter).findFirst();
            optional.ifPresent(item -> {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) item;
                ObjectMapper objectMapper = converter.getObjectMapper();
                SimpleModule simpleModule = new SimpleModule();
                simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
                simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
                simpleModule.addSerializer(Double.class, new DoubleSerializer());
                simpleModule.addSerializer(Double.TYPE, new DoubleSerializer());
                simpleModule.addSerializer(String.class, new JacksonDesensitizationContextValueFilter());
                simpleModule.addDeserializer(String.class, new JsonTrimDeserializer());
                objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.PATTERN_DATE_TIME));
                objectMapper.setTimeZone(TimeZone.getTimeZone(DateUtil.TIME_ZONE_GMT8));
                objectMapper.registerModule(simpleModule);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
                objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            });
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthUserArgumentResolver());
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(WebMvcRequestHandlerProvider.class)
    public static class FixNpeForSpringfoxHandlerProviderBeanPostProcessorConfiguration {
        @Bean
        public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
            return new BeanPostProcessor() {

                @Override
                public Object postProcessAfterInitialization(@Nonnull Object bean, @Nonnull String beanName) throws BeansException {
                    if (bean instanceof WebMvcRequestHandlerProvider) {
                        customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                    }
                    return bean;
                }

                private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                    List<T> copy = mappings.stream()
                            .filter(mapping -> mapping.getPatternParser() == null)
                            .collect(Collectors.toList());
                    mappings.clear();
                    mappings.addAll(copy);
                }

                @SuppressWarnings("unchecked")
                private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                    try {
                        Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                        field.setAccessible(true);
                        return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }
                }
            };
        }
    }
}
