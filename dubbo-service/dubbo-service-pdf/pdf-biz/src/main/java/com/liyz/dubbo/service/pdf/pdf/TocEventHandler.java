package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.SneakyThrows;

/**
 * 目录页相关
 * @Description
 * @Author ChenHao
 * @Date 2022/5/13 18:49
 */
public class TocEventHandler implements IEventHandler {
    public static final float height = 120;


    public TocEventHandler() {

    }

    @SneakyThrows
    @Override
    public void handleEvent(Event event) {
        // System.out.println("进入"+this.getClass().getName());
        if(GenPdfContext.getContentMaxPageNumber() < 1){
            // PDF内容还没有画完，先return
            return;
        }
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PageSize pageSize = pdfDoc.getDefaultPageSize();
        float lineTop = 0f;
        if(pdfDoc.getNumberOfPages() == GenPdfContext.getContentMaxPageNumber()+1){
            // 第一个目录页
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
            Rectangle rect = new Rectangle(0, pageSize.getTop()-HeaderEventHandler.height-height, pageSize.getWidth(), height);
            Paragraph text = new Paragraph("目录").setMargin(0)
                    .setFont(GenPdfContext.getPingFangFontBold()).setFontSize(32).setFixedLeading(33);
            float divBorderWeight = 0f;float paddingTop = 43.5f;
            Div headerDiv = new Div()
//                    .setBorder(new SolidBorder(DeviceRgb.RED, divBorderWeight))
                    .add(text).setTextAlignment(TextAlignment.CENTER).setPaddingTop(paddingTop)
                    .setHeight(height-2*divBorderWeight-paddingTop);
//            PdfUtils.print(doc, text);
//            PdfUtils.print(doc, headerDiv);
            new Canvas(pdfCanvas, rect)
                    .add(headerDiv)
                    .close();
            lineTop = height;
        }

        // 画一根垂直分割线
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        // Create a 100% Magenta color
        // Color magentaColor = new DeviceCmyk(0.f, 1.f, 0.f, 0.f);
        canvas
                .setStrokeColor(new DeviceRgb(151,151,151))
                .setLineWidth(0.5f)
                .moveTo(421, 30)
                .lineTo(421, pageSize.getTop()-HeaderEventHandler.height-lineTop-20)
                .closePathStroke();
    }
}
