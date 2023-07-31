package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.layout.element.IAbstractElement;
import com.itextpdf.layout.element.IElement;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/20 18:26
 */
public class PdfFilterChain {
    private List<PdfFilter> filters = new ArrayList<>();

    public void exec(IElement element){
        if(null == element){
            return;
        }
        for (PdfFilter filter : filters) {
            filter.filter(element);
        }
        if(element instanceof IAbstractElement){
            List<IElement> children = ((IAbstractElement) element).getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                for (IElement child : children) {
                    exec(child);
                }
            }
        }
    }

    public PdfFilterChain addFilter(PdfFilter filter){
        this.filters.add(filter);
        return this;
    }
}
