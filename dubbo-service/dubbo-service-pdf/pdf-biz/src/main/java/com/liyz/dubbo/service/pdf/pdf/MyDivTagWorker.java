package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.tags.DivTagWorker;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.layout.element.Div;
import com.itextpdf.styledxmlparser.node.IElementNode;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/25 17:17
 */
public class MyDivTagWorker extends DivTagWorker {

    /**
     * Creates a new {@link DivTagWorker} instance.
     *
     * @param element the element
     * @param context the context
     */
    public MyDivTagWorker(IElementNode element, ProcessorContext context) {
        super(element, context);
        if(CssConstants.BLOCK.equalsIgnoreCase(getDisplay())){
            // 如果是正常显示的div，使用自定义渲染器，这个id需要自己去标记
            String id = element.getAttribute("id");
            if(StringUtils.isNotBlank(id)){
                Div div = (Div) getElementResult();
                div.setNextRenderer(new PageDivRenderer(div));
            }
        }
    }


}
