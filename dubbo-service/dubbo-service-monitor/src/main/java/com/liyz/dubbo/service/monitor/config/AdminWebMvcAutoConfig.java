package com.liyz.dubbo.service.monitor.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Optional;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/26 20:34
 */
@Slf4j
@Configuration
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
public class AdminWebMvcAutoConfig extends WebMvcConfigurationSupport {

    /**
     * 允许加载本地静态资源
     *
     * @param registry 资源注册器
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/templates/",
                        "classpath:/META-INF/spring-boot-admin-server-ui/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        if (!CollectionUtils.isEmpty(converters)) {
            Optional<HttpMessageConverter<?>> optional = converters.stream().filter(item -> item instanceof MappingJackson2HttpMessageConverter).findFirst();
            optional.ifPresent(item -> {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) item;
                ObjectMapper objectMapper = converter.getObjectMapper();
                //生成JSON时,将所有Long转换成String
                SimpleModule simpleModule = new SimpleModule();
                simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
                simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
                simpleModule.addDeserializer(Registration.class, new RegistrationDeserializer());
                objectMapper.registerModule(simpleModule);
                //BigDecimal转化为PlainToString
                objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            });
        }
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(60 * 1000L);
        configurer.registerCallableInterceptors(timeoutInterceptor());
        configurer.setTaskExecutor(threadPoolTaskExecutor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("WYF-Thread-");
        taskExecutor.setDaemon(true);
        return taskExecutor;
    }
}
