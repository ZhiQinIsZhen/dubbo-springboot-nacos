package com.liyz.dubbo.service.pdf.service;

import org.thymeleaf.context.IContext;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:07
 */
public interface HtmlTemplateService {

    /**
     * 根据资源文件解析模板
     *
     * @param template 模板文件名
     * @param context 需要替换的上下文
     * @return html文件
     */
    String processResource(String template, IContext context);

    /**
     * 直接根据html字符解析模板
     *
     * @param template html字符
     * @param context 需要替换的上下文
     * @return html文件
     */
    String processSimple(String template, IContext context);
}
