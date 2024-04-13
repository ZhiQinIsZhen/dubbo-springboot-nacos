package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

/**
 * PDF页尾
 * @Description
 * @Author ChenHao
 * @Date 2022/7/19 10:30
 */
public class FooterEventHandler implements IEventHandler {
    private Document document;
    private FooterParams params;

    public FooterEventHandler(Document document, FooterParams params) {
        this.document = document;
        this.params = params;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        Rectangle pageSize = docEvent.getPage().getPageSize();
        // 当前页数
        int currentPage = pdfDoc.getNumberOfPages();
        if(currentPage < params.getStartPageNumber()){
            return;
        }
        // PDF内容最大页数
        int contentMaxPageNumber = GenPdfContext.getContentMaxPageNumber();
        if(contentMaxPageNumber > 0 && currentPage > contentMaxPageNumber){
            // 目录页不需要页尾
            return;
        }
        Canvas canvas = new Canvas(docEvent.getPage(), pageSize);
        Div footerDiv = new Div().setHeight(23.5f).setPaddingTop(10.5f)
                .setBackgroundColor(new DeviceRgb(255, 128, 71), 0.1f)
                .setFixedPosition(0,0, pageSize.getWidth());
        Paragraph p = new Paragraph("声明：以下数据为"+params.getGenDate()+"生成的评估结果，后续发生数据变动，不在本报告展示。")
                .setMargin(0).setTextAlignment(TextAlignment.CENTER).setFontSize(13f).setFixedLeading(13f)
                .setFont(GenPdfContext.getPingFangFontMedium()).setFontColor(new DeviceRgb(255, 128, 71));
        footerDiv.add(p);
//        PdfUtils.print(document,footerDiv);
//        PdfUtils.print(document,p);
        canvas.add(footerDiv).close();
    }
}
