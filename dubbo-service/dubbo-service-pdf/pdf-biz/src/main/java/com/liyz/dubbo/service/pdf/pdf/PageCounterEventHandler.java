package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.layout.Document;
import lombok.SneakyThrows;

/**
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/13 18:49
 */
public class PageCounterEventHandler implements IEventHandler {
    private Document doc;

    public PageCounterEventHandler(Document doc) {
        this.doc = doc;
    }

    @SneakyThrows
    @Override
    public void handleEvent(Event event) {
        System.out.println("当前进入第"+doc.getPdfDocument().getNumberOfPages()+"页");
    }
}
