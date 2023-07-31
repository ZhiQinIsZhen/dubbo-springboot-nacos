package com.liyz.dubbo.service.pdf.service;

import org.thymeleaf.context.IContext;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 15:44
 */
public interface Html2PdfService {

    /**
     * html转化为pdf
     *
     * @param html html内容
     * @param context 上下文
     */
    void html2Pdf(String html, IContext context);
}
