package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * PDF页头
 * @Description
 * @Author ChenHao
 * @Date 2022/5/13 18:49
 */
@Slf4j
public class HeaderEventHandler implements IEventHandler {

    private Document document;
    private HeaderParams params;
    public static final float height = 63f;

    public HeaderEventHandler(Document document, HeaderParams params) {
        this.document = document;
        this.params = params;
    }

    @SneakyThrows
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        int numberOfPages = pdfDoc.getNumberOfPages();
        if(numberOfPages < params.getStartPageNumber()){
            return;
        }
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        Canvas canvas = new Canvas(page, pageSize);
        // 页眉背景图
        ImageData imageData = ImageDataFactory.create(new ClassPathResource(params.getBkImgClassPath()).getURL());
        // 背景尺寸
        BackgroundSize backgroundSize = new BackgroundSize();
        // 铺满
        backgroundSize.setBackgroundSizeToValues(UnitValue.createPercentValue(100), UnitValue.createPercentValue(100));
        BackgroundImage backgroundImage = new BackgroundImage.Builder()
                .setImage(new PdfImageXObject(imageData))
                .setBackgroundSize(backgroundSize)
                .build();
        // 定义页眉DIV
        Div headerDiv = new Div()
                .setBackgroundImage(backgroundImage)
//                .setBorder(new SolidBorder(DeviceRgb.RED, 0.5f))
                .setHeight(height);
        // 报告名称P
        Paragraph reportText = new Paragraph(params.getReportName()).setMargin(0)
                .setFont(GenPdfContext.getPingFangFontMedium()).setFontSize(14).setFixedLeading(15);
        String reportNo = params.getReportNo();
        if (StringUtils.isNotBlank(reportNo)) {
            reportNo="报告编号："+reportNo;
        }
        // 报告编号P
        Paragraph reportNoText = new Paragraph(reportNo).setMargin(0).setMarginTop(7)
                .setFont(GenPdfContext.getPingFangFontRegular()).setFontSize(10).setFixedLeading(10).setOpacity(0.63f);
        // 包含报告名称和报告编号的DIV
        Div textDiv = new Div().setFontColor(new DeviceRgb(255, 255, 255))
                .setPaddingRight(59).setPaddingTop(15)
                .add(reportText)
                .add(reportNoText)
                .setTextAlignment(TextAlignment.RIGHT);
//        PdfUtils.print(doc, textDiv);
        headerDiv.add(textDiv);
        // 被评估企业logo
        String corpLogo = params.getCorpLogo();
        float logoSize = 28f;
        if(StringUtils.isNotBlank(corpLogo)){
            // 如果有企业logo，则设置
            try {
                Image logoImage = new Image(ImageDataFactory.create(corpLogo));
                Div logoDiv = new Div()
                        .add(
                                logoImage.setWidth(logoSize).setMaxHeight(logoSize).setBorderRadius(new BorderRadius(3,3))
                        ).setFixedPosition(170.5f, pageSize.getHeight()-(28.5f+17.25f), logoSize);
                headerDiv.add(logoDiv);
            }catch (Exception e){
                log.warn("请求资源失败："+corpLogo, e);
            }
        }
        // 被评估企业名称
        String corpName = params.getCorpName();
        float corpNameWidth = 400f;float corpNameFont = 17f;
        PdfFont pingFangFontHeavy = GenPdfContext.getPingFangFontHeavy();
        if(pingFangFontHeavy.getWidth(corpName,corpNameFont) > corpNameWidth){
            corpNameFont = 12f;
        }
        Div corp = new Div().add(
                new Paragraph(corpName).setFontColor(DeviceRgb.WHITE).setMargin(0)
                        .setFontSize(corpNameFont).setFont(pingFangFontHeavy).setFixedLeading(28.5f)
        ).setFixedPosition(// 设置企业DIV的位置，如果没有企业logo需要靠左边一点
                StringUtils.isNotBlank(corpLogo) ? 205 : 165,
                pageSize.getHeight()-(28.5f+17.25f),corpNameWidth);
        headerDiv.add(corp);
        canvas.add(headerDiv).close();
//        new Canvas(pdfCanvas, rect)
//                .add(headerDiv)
//                .close();

    }
}
