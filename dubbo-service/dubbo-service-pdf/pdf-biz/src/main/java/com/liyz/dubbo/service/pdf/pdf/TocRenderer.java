package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.IRenderer;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/6 13:51
 */
public class TocRenderer extends ColumnDocumentRenderer {
    private Set<Integer> set = new HashSet<>();
    public TocRenderer(Document document, Rectangle[] columns) {
        super(document, columns);
    }

    public TocRenderer(Document document, boolean immediateFlush, Rectangle[] columns) {
        super(document, immediateFlush, columns);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new TocRenderer(document, immediateFlush, columns);
    }

    @Override
    protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
        LayoutArea layoutArea = super.updateCurrentArea(overflowResult);
        // System.out.println("进入updateCurrentArea,当前第"+currentArea.getPageNumber()+"页");
        Rectangle bBox = layoutArea.getBBox();
        if(currentArea.getPageNumber() == GenPdfContext.getContentMaxPageNumber()+1){
            // 第一个目录页有目录两个字，高度减少一点点
            bBox.setHeight(bBox.getHeight()-TocEventHandler.height);
        }
        return layoutArea;
    }
}
