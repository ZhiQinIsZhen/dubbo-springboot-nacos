package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.layout.element.IElement;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/20 18:24
 */
public interface PdfFilter {
    /**
     * 把元素添加到 {@link com.itextpdf.layout.Document}之前
     * @param element
     */
    void filter(IElement element);
}
