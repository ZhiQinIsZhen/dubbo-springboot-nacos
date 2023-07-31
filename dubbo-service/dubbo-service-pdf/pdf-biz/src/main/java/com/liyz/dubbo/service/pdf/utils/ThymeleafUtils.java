package com.liyz.dubbo.service.pdf.utils;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 17:08
 */
public class ThymeleafUtils {

    public static final SpringTemplateEngine newSpringTemplateEngine() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("META-INF/ac-test.xml");
        ThymeleafProperties properties = new ThymeleafProperties();
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(properties.isEnableSpringElCompiler());
        engine.setRenderHiddenMarkersBeforeCheckboxes(properties.isRenderHiddenMarkersBeforeCheckboxes());
        engine.addTemplateResolver(defaultTemplateResolver(ac, properties));
        engine.addDialect(new Java8TimeDialect());
        return engine;
    }

    private static SpringResourceTemplateResolver defaultTemplateResolver(ApplicationContext ac, ThymeleafProperties properties) {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(ac);
        resolver.setPrefix(properties.getPrefix());
        resolver.setSuffix(properties.getSuffix());
        resolver.setTemplateMode(properties.getMode());
        if (properties.getEncoding() != null) {
            resolver.setCharacterEncoding(properties.getEncoding().name());
        }
        resolver.setCacheable(properties.isCache());
        Integer order = properties.getTemplateResolverOrder();
        if (order != null) {
            resolver.setOrder(order);
        }
        resolver.setCheckExistence(properties.isCheckTemplate());
        return resolver;
    }
}
