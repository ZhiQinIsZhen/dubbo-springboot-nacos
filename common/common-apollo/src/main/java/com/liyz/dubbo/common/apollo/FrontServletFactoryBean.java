package com.liyz.dubbo.common.apollo;

import com.ctrip.framework.apollo.spring.config.PropertySourcesProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import javax.servlet.MultipartConfigElement;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:22
 */
@Slf4j
@RequiredArgsConstructor
public class FrontServletFactoryBean implements FactoryBean<ServletRegistrationBean>, ApplicationContextAware {

    private final Class config;
    private final String contextPath;
    private ApplicationContext applicationContext;
    private PropertySourcesProcessor propertySourcesProcessor;
    private MultipartConfigElement multipartConfigElement;

    @Override
    public ServletRegistrationBean getObject() {
        ConfigurableEnvironment environment = (ConfigurableEnvironment) applicationContext.getEnvironment();

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setEnvironment(environment);
        context.register(DelegatingWebMvcConfiguration.class, config);
        // placeHolder处理
        PropertySourcesPlaceholderConfigurer placeHolderProcessor = new PropertySourcesPlaceholderConfigurer();
        placeHolderProcessor.setEnvironment(environment);
        context.addBeanFactoryPostProcessor(placeHolderProcessor);
        if (propertySourcesProcessor != null) {
            log.info("@@Add apollo propertySourcesProcessor to {}", context);
            context.addBeanFactoryPostProcessor(propertySourcesProcessor);
        }

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setDetectAllViewResolvers(true);
        dispatcherServlet.setApplicationContext(context);
        // controller不接收options请求
        dispatcherServlet.setDispatchOptionsRequest(false);
        // 找不到请求处理器返回404而不抛出NoHandlerFoundException
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(false);
        ServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet, contextPath);
        if (multipartConfigElement != null) {
            log.info("@@Add multipartConfigElement to {}", registration);
            registration.setMultipartConfig(multipartConfigElement);
        }
        registration.setLoadOnStartup(1);
        registration.setName(config.getName());
        return registration;
    }

    @Override
    public Class<?> getObjectType() {
        return ServletRegistrationBean.class;
    }

    @Autowired(required = false)
    public void setPropertySourcesProcessor(PropertySourcesProcessor propertySourcesProcessor) {
        this.propertySourcesProcessor = propertySourcesProcessor;
    }

    @Autowired(required = false)
    public void setMultipartConfigElement(MultipartConfigElement multipartConfigElement) {
        this.multipartConfigElement = multipartConfigElement;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
