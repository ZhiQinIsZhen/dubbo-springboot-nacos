package com.liyz.dubbo.service.pdf.service.impl;

import com.google.common.collect.Lists;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TabAlignment;
import com.liyz.dubbo.service.pdf.pdf.*;
import com.liyz.dubbo.service.pdf.service.Html2PdfService;
import com.liyz.dubbo.service.pdf.test.directory.DirectoryBO;
import com.liyz.dubbo.service.pdf.test.directory.TestDirectoryBO;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.IContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 15:47
 */
@Service
public class Html2PdfServiceImpl implements Html2PdfService {

    @Override
    public void html2Pdf(String html, IContext context) {
        try {
            DirectoryBO rapToc = new TestDirectoryBO();
            String dest = Files.createTempFile("projecthtml2pdftest", ".pdf").toString();
            WriterProperties writerProperties = new WriterProperties();
            writerProperties.setFullCompressionMode(true);
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest, writerProperties));
            pdfDocument.setDefaultPageSize(PageSize.A3);
            List<IElement> elements = PdfUtils.convert2pdfElement(
                    html,
                    "templates/",
                    pdfDocument
            );

            Document document = new Document(pdfDocument, PageSize.A3, false);
            GenPdfContext.putToc(rapToc.getRoot().convert2map());
            // 页头
            HeaderParams headerParams = new HeaderParams(
                    "大傻逼公司",
                    "http://img3.tianyancha.com/api/03ea95b396838f1d47cf59ab773bc5cf.png",
                    "FGGFGFHGFGHGFHGFHFGFFGGFGFHGFGHGFHGFHFGF",
                    "项目深度评估报告",
                    "templates/image/hd.png",
                    2
            );
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new DocumentEventHandler(document, 2));
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderEventHandler(document, headerParams));
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new PageNumberEventHandler(2));
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEventHandler(document, new FooterParams(
                    "2999-12-12", 2)));
            // 目录
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new TocEventHandler());
            pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new WatermarkEventHandler("仅供FGGFGFHGFGHGFHGFHFGFFGGFGFHGFGHGFHGFHFGF参考"));
            PdfFilterChain pdfFilterChain = new PdfFilterChain().addFilter(new PdfStyleFilter(document));
            for (IElement element : elements) {
                pdfFilterChain.exec(element);
                document.add((IBlockElement) element);
            }
            // 先把内容flush
            document.flush();
            // 记录内容的最大页数, 开始生成目录
            GenPdfContext.markContentMaxPageNumber(pdfDocument.getNumberOfPages());

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            // 生成目录页
            // 目录页
            float colWidth = 420.5f;
            float colHeight = PageSize.A3.getHeight() - HeaderEventHandler.height;
            // 定义目录页
            Rectangle[] columns = {
                    new Rectangle(0, 0, colWidth, colHeight),
                    new Rectangle(colWidth + 1, 0, colWidth, colHeight)};
            document.setRenderer(new TocRenderer(document, columns));
            document.add(new AreaBreak(AreaBreakType.LAST_PAGE));

            // 生成目录
            ArrayList<TabStop> tabStops = Lists.newArrayList(new TabStop(340, TabAlignment.RIGHT, new DottedLine()));
            List firstLevelTocList = rapToc.getRoot().getChildrenList();
            PdfUtils.addToc(document, tabStops, firstLevelTocList, 18);

            // 生成书签
            PdfOutline outlinesRoot = pdfDocument.getOutlines(false);
            PdfUtils.addBookmark(outlinesRoot, firstLevelTocList);
            document.close();
            GenPdfContext.remove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
