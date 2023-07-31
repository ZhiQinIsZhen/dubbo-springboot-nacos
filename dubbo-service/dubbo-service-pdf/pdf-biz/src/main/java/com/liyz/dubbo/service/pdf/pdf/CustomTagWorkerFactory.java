package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.html.TagConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.styledxmlparser.node.IElementNode;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/12 10:06
 */
public class CustomTagWorkerFactory extends DefaultTagWorkerFactory {
    private PdfDocument pdfDocument;

    public CustomTagWorkerFactory(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    @Override
    public ITagWorker getCustomTagWorker(IElementNode tag, ProcessorContext context) {
//        if(TagConstants.HTML.equals(tag.name())){
//            return new CustomHtmlTagWorker(tag, context);
//        }
        if(TagConstants.SVG.equals(tag.name())){
            MySvgTagWorker svgTagWorker = new MySvgTagWorker(tag, context);
            svgTagWorker.setPdfDocument(pdfDocument);
            return svgTagWorker;
        }
        if(TagConstants.DIV.equals(tag.name())){
            String display = tag.getStyles() != null ? tag.getStyles().get(CssConstants.DISPLAY) : null;
            String id = tag.getAttribute("id");
            if(StringUtils.isNotBlank(id)){
                // System.out.println("div:id="+ id+"\tdisplay="+display);
            }
            if(CssConstants.BLOCK.equalsIgnoreCase(display)){
                return new MyDivTagWorker(tag, context);
            }
        }
        return super.getCustomTagWorker(tag, context);
    }
}
