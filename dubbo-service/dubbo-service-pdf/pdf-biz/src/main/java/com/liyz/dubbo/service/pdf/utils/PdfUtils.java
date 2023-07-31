package com.liyz.dubbo.service.pdf.utils;

import com.google.common.collect.Lists;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.styledxmlparser.util.FontFamilySplitterUtil;
import com.liyz.dubbo.service.pdf.pdf.*;
import com.liyz.dubbo.service.pdf.test.directory.Dire;
import lombok.experimental.UtilityClass;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/11 9:17
 */
@UtilityClass
public class PdfUtils {
    public static List<IElement> convert2pdfElement(String html, String classPathPrefix, PdfDocument pdfDocument) {
        ConverterProperties properties = PdfUtils.createConverterProperties(classPathPrefix);
        properties.setTagWorkerFactory(new CustomTagWorkerFactory(pdfDocument));
        // 可显示alt属性，比如img标签的alt属性
        // pdfDocument.setTagged();

        List<IElement> elements = HtmlConverter.convertToElements(
                html,
                properties
        );
        return elements;
    }
    public static void addBookmark(PdfOutline parent, List<Dire> tcList){
        for (Dire tc : tcList) {
            PdfOutline pdfOutline = parent.addOutline(tc.getName());
            pdfOutline.addDestination(PdfDestination.makeDestination(new PdfString(tc.getDest())));
            List<Dire> children = tc.getChildrenList();
            if (!CollectionUtils.isEmpty(children)) {
                addBookmark(pdfOutline, children);
            }
        }
    }

    public static ConverterProperties createConverterProperties(String classPathPrefix) {
        ConverterProperties properties = new ConverterProperties();
        properties.setImmediateFlush(false);
        properties.setBaseUri(classPathPrefix);
        properties.setResourceRetriever(new MyResourceRetriever(classPathPrefix));
        PdfFontUtils.supportChinese(properties);
        return properties;
    }

    public static void addWatermark(String srcPdf, String outPdf, String watermarkText) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcPdf), new PdfWriter(outPdf));
        // 水印字体
//        PdfFont defaultFont = GenPdfContext.getPingFangFontRegular();
        PdfFont defaultFont = PdfFontFactory.createFont("front/PingFang Regular.ttf", PdfEncodings.IDENTITY_H);
        // 定义水印文本块
        Paragraph paragraph = new Paragraph(watermarkText).setFont(defaultFont).setFontSize(12);
        // 基于这个值计算水印密度
        float density = Math.max(defaultFont.getWidth(watermarkText, 12), 160);
        // 水印倾斜角度
        float radAngle = 0.3f;
        // Creating a dictionary that maps resource names to graphics state parameter dictionaries
        // 定义水印文本透明度
        PdfExtGState gs = new PdfExtGState().setFillOpacity(0.2f);
        // 页面宽度
        Rectangle rectangle = pdfDoc.getPage(1).getPageSize();
        float width = rectangle.getWidth();
        // 页面高度
        float height = rectangle.getHeight();
        // 水印X轴步长
        float xStepSize = density*1f;
        // 水印Y轴步长
        float yStepSize = density*0.5f;
        // 给所有页面添加水印
        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
            PdfCanvas over = new PdfCanvas(pdfDoc.getPage(i));
            //        over.setFillColor(ColorConstants.BLACK);
            over.saveState();
            over.setExtGState(gs);
            Canvas canvas = new Canvas(over, pdfDoc.getDefaultPageSize());
            // 两层循环画该页的水印
            for(float x=0f;width>x-xStepSize;x+=xStepSize){
                for(float y=0f;height>y-yStepSize*2;y+=yStepSize){
                    canvas.showTextAligned(paragraph, x, y, i, TextAlignment.CENTER, VerticalAlignment.TOP, radAngle);
                }
            }
            canvas.close();
            over.restoreState();
        }
        pdfDoc.close();
    }

    public static void print(Document doc, AbstractElement element){
        IRenderer renderer =  element.createRendererSubTree();
        renderer.setParent(new DocumentRenderer(doc));

        // Simulate the positioning of the renderer to find out how much space the header table will occupy.
        LayoutResult result = renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.A3)));
        Rectangle bBox = result.getOccupiedArea().getBBox();
        System.out.println("元素的坐标："+bBox.getX() + "," +bBox.getY() + "\t\t" + "元素的宽高："+bBox.getWidth() + " " +bBox.getHeight());
    }

    public static void addToc(Document document,
                              ArrayList<TabStop> tabStops,
                              List<Dire> childrenList) {
        addToc(document, tabStops, childrenList, 18, null, null);
    }
    public static void addToc(Document document,
                              ArrayList<TabStop> tabStops,
                              List<Dire> childrenList,
                              Integer tocLineHeight) {
        addToc(document, tabStops, childrenList, tocLineHeight, null, null);
    }
    public static void addToc(Document document,
                               ArrayList<TabStop> tabStops,
                               List<Dire> childrenList,
                               Integer tocLineHeight,
                               BiConsumer<Dire, Div> consumerDiv, // 每个目录都是一个DIV标签
                               BiConsumer<Dire, Paragraph> consumerP // 目录的DIV下包含P标签
    ) {
        for (Dire tc : childrenList) {
            Div div = new Div().setMargin(0).setMarginLeft(40).setMarginRight(40).setMarginTop(8f).setMarginBottom(8f);
            if(consumerDiv != null){
                consumerDiv.accept(tc, div);
            }
            Paragraph p = createTocParagraph(tabStops, tc, tocLineHeight);
            if (consumerP != null) {
                consumerP.accept(tc, p);
            }
            div.add(p);
//            PdfUtils.print(document, p);
//            PdfUtils.print(document, div);
            document.add(div);
            List<Dire> children = tc.getChildrenList();
            if (!CollectionUtils.isEmpty(children)) {
                addToc(document, tabStops, children, tocLineHeight,consumerDiv, consumerP);
            }
        }
    }
    private static Paragraph createTocParagraph(ArrayList<TabStop> tabStops,
                                                Dire tc) {
        return createTocParagraph(tabStops, tc, 18);
    }
    private static Paragraph createTocParagraph(ArrayList<TabStop> tabStops,
                                                Dire tc,
                                                Integer tocLineHeight) {
        // pdf注释
        PdfAnnotation la1 = new PdfLinkAnnotation(new Rectangle(0, 0, 0, 0))
                .setHighlightMode(PdfAnnotation.HIGHLIGHT_INVERT)
                .setAction(PdfAction.createGoTo(tc.getDest()))
                //.setBorderStyle(PdfAnnotation.STYLE_UNDERLINE)
                .setBorder(new PdfAnnotationBorder(0,0,0));
        // 设置跳转
        Link link = new Link(tc.getName(), (PdfLinkAnnotation)la1);
        Paragraph paragraph = new Paragraph().setMargin(0)
                .addTabStops(tabStops)
                .add(link)
                .add(new Tab())
                .add(String.valueOf(tc.getPageNumber()))
                .setFontSize(15).setFixedLeading(tocLineHeight)
                .setFont(GenPdfContext.getPingFangFontMedium())
                .setFontColor(new DeviceRgb(78,89,105));
        if(tc.getLevel() > 1){
            // 设置文本缩进
            paragraph.setFirstLineIndent((tc.getLevel()-1)*15);
        }else{
            paragraph.setFontColor(new DeviceRgb(39,36,63))
                    .setFont(GenPdfContext.getPingFangFontBold());
//            if(String2Utils.equalsAny(tc.getDest(), AccurateTocConstant.ST_RESULT, AccurateTocConstant.ST_SUMMARY, AccurateTocConstant.ST_DETAIL)){
//                paragraph.setFontSize(18).setFixedLeading(18);
//            }
        }

//        // 设置段落行间距，这里设置为字体大小的0.5倍
//        paragraph.setMultipliedLeading(0.9f);
        return paragraph;
    }
    private static Div addTocBlue(int order) {
        // 特殊一级目录设置小蓝条
        float bottom = PageSize.A3.getTop() - TocEventHandler.height - HeaderEventHandler.height-23-(order*34);
        Div blue = new Div().setWidth(3).setHeight(13).setBackgroundColor(new DeviceRgb(41, 112, 246))
                .setFixedPosition(28, bottom,3);
        return blue;
    }
    public static final double pt2px(double pt){
        return pt/0.75;
    }

    /**
     * 调整PDF页的顺序，需要把目录页调整到首页后面
     * @param dest 新PDF存储路径
     * @param contentPdfDest PDF路径
     * @param contentMaxPageNumber PDF主体内容最大页数（后面是目录页）
     * @throws IOException
     */
    public static void reorderPage(String dest, String contentPdfDest, int contentMaxPageNumber) throws IOException {
        WriterProperties writerProperties = new WriterProperties();
        writerProperties.setFullCompressionMode(true);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest, writerProperties));
        pdfDoc.setDefaultPageSize(PageSize.A3);
        pdfDoc.initializeOutlines();
        // 读取内容PDF
        PdfDocument pdfContentDoc = new PdfDocument(new PdfReader(contentPdfDest));
        List<Integer> pages = Lists.newArrayList();
        int maxPageNumber = pdfContentDoc.getNumberOfPages();
        if(contentMaxPageNumber > maxPageNumber){
            throw new RuntimeException("contentMaxPageNumber（"+contentMaxPageNumber+"）不可大于maxPageNumber（"+maxPageNumber+"）。");
        }
        // 首页
        pages.add(1);
        // 目录页
        for (int i = contentMaxPageNumber+1; i <= maxPageNumber; i++) {
            pages.add(i);
        }
        // 内容页
        for (int i = 2; i <= contentMaxPageNumber; i++) {
            pages.add(i);
        }
        // 复制page，顺便调整顺序
        pdfContentDoc.copyPagesTo(pages, pdfDoc);
        pdfContentDoc.close();
        pdfDoc.close();
    }

    public static void main(String[] args) {
        System.out.println(FontFamilySplitterUtil.splitFontFamily("DianHei"));
        System.out.println(FontFamilySplitterUtil.splitFontFamily("DianHei-UltLight"));
        System.out.println(FontFamilySplitterUtil.splitFontFamily("MF DianHei(Noncommercial)-UltLight"));

        ApplicationHome applicationHome = new ApplicationHome();
        // applicationHome.
    }
}
