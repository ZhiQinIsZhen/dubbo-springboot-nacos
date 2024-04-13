package com.liyz.dubbo.service.pdf.service.impl;

import com.liyz.dubbo.service.pdf.service.HtmlTemplateService;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.IContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:13
 */
@Service
@EnableConfigurationProperties({ThymeleafProperties.class})
public class HtmlTemplateServiceImpl implements HtmlTemplateService {

    private final ThymeleafProperties properties;

    public HtmlTemplateServiceImpl(ThymeleafProperties properties) {
        this.properties = properties;
    }

    /**
     * 根据资源文件解析模板
     *
     * @param template 模板文件名
     * @param context 需要替换的上下文
     * @return html文件
     */
    @Override
    public String processResource(String template, IContext context) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
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
        return engine.process(template, context);
    }

    /**
     * 直接根据html字符解析模板
     *
     * @param template html字符
     * @param context 需要替换的上下文
     * @return html文件
     */
    @Override
    public String processSimple(String template, IContext context) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        return engine.process(template, context);
    }
}
