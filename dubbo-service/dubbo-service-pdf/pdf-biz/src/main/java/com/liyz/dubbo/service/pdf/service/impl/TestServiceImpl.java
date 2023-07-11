package com.liyz.dubbo.service.pdf.service.impl;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/11 10:20
 */
public class TestServiceImpl {

    public static SpringTemplateEngine test() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        ThymeleafProperties properties = new ThymeleafProperties();
        engine.setEnableSpringELCompiler(properties.isEnableSpringElCompiler());
        engine.setRenderHiddenMarkersBeforeCheckboxes(properties.isRenderHiddenMarkersBeforeCheckboxes());

        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new ClassPathXmlApplicationContext("META-INF/ac.xml"));
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

        engine.addTemplateResolver(resolver);
        engine.addDialect(new Java8TimeDialect());
        return engine;
    }

    public static String html() {
        SpringTemplateEngine engine = test();
        final Context context = new Context();
        String html = engine.process("", context);
        return html;
    }
}
