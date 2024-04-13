package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.tags.SvgTagWorker;
import com.itextpdf.html2pdf.attach.util.AccessiblePropHelper;
import com.itextpdf.html2pdf.attach.util.ContextMappingHelper;
import com.itextpdf.html2pdf.util.SvgProcessingUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.Image;
import com.itextpdf.styledxmlparser.node.IElementNode;
import com.itextpdf.styledxmlparser.node.INode;
import com.itextpdf.svg.exceptions.SvgProcessingException;
import com.itextpdf.svg.processors.ISvgProcessorResult;
import com.itextpdf.svg.processors.impl.DefaultSvgProcessor;
import com.itextpdf.svg.processors.impl.SvgConverterProperties;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/13 10:58
 */
public class MySvgTagWorker implements ITagWorker {
    /**
     * 自己加的
     */
    @Setter
    private PdfDocument pdfDocument;

    private static final Logger LOGGER = LoggerFactory.getLogger(SvgTagWorker.class);

    private Image svgImage;
    private ISvgProcessorResult processingResult;

    /**
     * Creates a new {@link SvgTagWorker} instance.
     *
     * @param element the element
     * @param context the context
     */
    public MySvgTagWorker(IElementNode element, ProcessorContext context) {
        svgImage = null;
        SvgConverterProperties props = ContextMappingHelper.mapToSvgConverterProperties(context);
        /*
         * 扩展默认的rendererFactory
         * 1. 使line支持dash-array
         */
         props.setRendererFactory(new MySvgNodeRendererFactory());
        try {
            processingResult = new DefaultSvgProcessor().process((INode) element, props);
        } catch (SvgProcessingException spe) {
//            LOGGER.error(Html2PdfLogMessageConstant.UNABLE_TO_PROCESS_SVG_ELEMENT, spe);
        }
        context.startProcessingInlineSvg();
    }


    @Override
    public void processEnd(IElementNode element, ProcessorContext context) {
        // 主要区别在这里
        if ((this.pdfDocument != null ) && processingResult != null) {

            SvgProcessingUtil util = new SvgProcessingUtil(context.getResourceResolver());
            svgImage = util.createImageFromProcessingResult(processingResult, this.pdfDocument);

            AccessiblePropHelper.trySetLangAttribute(svgImage, element);
            context.endProcessingInlineSvg();
        }
    }

    @Override
    public boolean processContent(String content, ProcessorContext context) {
        return false;
    }

    @Override
    public boolean processTagChild(ITagWorker childTagWorker, ProcessorContext context) {
        return false;
    }

    @Override
    public IPropertyContainer getElementResult() {
        return svgImage;
    }
}
