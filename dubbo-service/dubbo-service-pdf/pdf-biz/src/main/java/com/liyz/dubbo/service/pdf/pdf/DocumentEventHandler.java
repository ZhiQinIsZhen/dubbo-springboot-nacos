package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/7/19 16:08
 */
public class DocumentEventHandler implements IEventHandler {
    private Document document;
    private int contentStartPage;

    public DocumentEventHandler(Document document, int contentStartPage) {
        this.document = document;
        this.contentStartPage = contentStartPage;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        int numberOfPages = pdfDoc.getNumberOfPages();
        // document的默认margin是36
        if(numberOfPages < contentStartPage){
            document.setMargins(0,0,0,0);
            return;
        }
        document.setMargins(50,0,46,0);
    }
}
