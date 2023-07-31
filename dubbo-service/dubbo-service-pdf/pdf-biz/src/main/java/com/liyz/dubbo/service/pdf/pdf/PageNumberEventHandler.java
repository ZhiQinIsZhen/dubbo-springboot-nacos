package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/28 15:31
 */
public class PageNumberEventHandler implements IEventHandler {
    /**
     * 从第几页开始画页码
     */
    private int startPage = 1;

    public PageNumberEventHandler(int startPage) {
        this.startPage = startPage;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        int numberOfPages = pdfDoc.getNumberOfPages();
        if(numberOfPages < startPage){
            return;
        }
        int contentMaxPageNumber = GenPdfContext.getContentMaxPageNumber();
        if(0 != contentMaxPageNumber && numberOfPages > contentMaxPageNumber){
            return;
        }
        PdfPage page = pdfDoc.getPage(numberOfPages);
        Rectangle pageSize = page.getPageSize();
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();
        Canvas canvas = new Canvas(page, pageSize);
        Div div = new Div();
        Style style = new Style();
        PdfFont pingFangFontHeavy = PdfFontUtils.getPingFangFontHeavy();
//        System.out.println(pingFangFontHeavy.getContentWidth(new PdfString("99")));
        style
//                .setHeight(14)
                .setFont(pingFangFontHeavy).setFontSize(12f)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorderRadius(new BorderRadius(3))
                .setBackgroundColor(DeviceRgb.WHITE)
                .setFixedPosition(width - 35f, height - 40.5f, 27);
        div.addStyle(style);
        div.add(new Paragraph(String.valueOf(numberOfPages)).setMargin(0).setFixedLeading(27));
        canvas.add(div)
                .close();
    }
}
