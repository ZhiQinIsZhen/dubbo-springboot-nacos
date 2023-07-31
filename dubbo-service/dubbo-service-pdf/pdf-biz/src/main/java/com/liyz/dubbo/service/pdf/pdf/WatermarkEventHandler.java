package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/27 13:31
 */
public class WatermarkEventHandler implements IEventHandler {
    private String watermarkText;

    public WatermarkEventHandler(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        PageSize pageSize = pdfDoc.getDefaultPageSize();
        PdfFont defaultFont = GenPdfContext.getPingFangFontRegular();
//        PdfFont defaultFont = PdfFontFactory.createFont("front/PingFang Regular.ttf", PdfEncodings.IDENTITY_H);
        float fontSize = 14;
        // 定义水印文本块
        Paragraph paragraph = new Paragraph(watermarkText).setFont(defaultFont).setFontSize(fontSize).setFontColor(new DeviceRgb(237,237,237));
        // 基于这个值计算水印密度
        float density = Math.max(defaultFont.getWidth(watermarkText, fontSize), 200);
        // 水印倾斜角度
        float radAngle = 0.3f;
        // Creating a dictionary that maps resource names to graphics state parameter dictionaries
        // 定义水印文本透明度
        PdfExtGState gs = new PdfExtGState().setFillOpacity(1f);
        // 页面宽度
        float width = pageSize.getWidth();
        // 页面高度
        float height = pageSize.getHeight();
        // 水印X轴步长
        float xStepSize = density*1f;
        // 水印Y轴步长
        float yStepSize = density*0.75f;
        //
//        PdfCanvas over = new PdfCanvas(pdfDoc.getPage(i));
        //        over.setFillColor(ColorConstants.BLACK);
        pdfCanvas.saveState();
        pdfCanvas.setExtGState(gs);
        Canvas canvas = new Canvas(pdfCanvas, pdfDoc.getDefaultPageSize());
        // 两层循环画该页的水印
        for(float x=0f;width>x-xStepSize;x+=xStepSize){
            for(float y=0f;height>y-yStepSize*2;y+=yStepSize){
                canvas.showTextAligned(paragraph, x, y, pdfDoc.getNumberOfPages(), TextAlignment.CENTER, VerticalAlignment.TOP, radAngle);
            }
        }
        canvas.close();
        pdfCanvas.restoreState();
    }
}
