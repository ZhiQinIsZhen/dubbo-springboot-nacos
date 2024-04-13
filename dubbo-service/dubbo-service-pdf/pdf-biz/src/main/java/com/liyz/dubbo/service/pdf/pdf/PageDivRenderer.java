package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.renderer.DivRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.liyz.dubbo.service.pdf.test.directory.Dire;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/25 16:46
 */
public class PageDivRenderer extends DivRenderer {

    /**
     * Creates a DivRenderer from its corresponding layout object.
     *
     * @param modelElement the {@link Div} which this object should manage
     */
    public PageDivRenderer(Div modelElement) {
        super(modelElement);
//        this.toc = toc;
    }

    @Override
    public IRenderer getNextRenderer() {
        return new PageDivRenderer((Div) modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        LayoutResult layout = super.layout(layoutContext);
        // 获得页码
        int pageNumber = layoutContext.getArea().getPageNumber();
        Object id = getProperty(Property.ID);
        if (id != null) {
            Dire toc = GenPdfContext.getTocById((String) id);
            if (toc != null && toc.getPageNumber()<1) {
                toc.setPageNumber(pageNumber);
            }
            //System.out.println("div:id="+id+"\t页码："+pageNumber);
        }
        return layout;
    }
}
