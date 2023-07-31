package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.styledxmlparser.node.IElementNode;
import com.itextpdf.svg.SvgConstants;
import com.itextpdf.svg.renderers.INoDrawSvgNodeRenderer;
import com.itextpdf.svg.renderers.ISvgNodeRenderer;
import com.itextpdf.svg.renderers.factories.DefaultSvgNodeRendererFactory;
import com.itextpdf.svg.renderers.impl.DefsSvgNodeRenderer;
import com.itextpdf.svg.renderers.impl.PathSvgNodeRenderer;
import com.liyz.dubbo.service.pdf.pdf.ext.MyPathSvgNodeRendererProxy;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/15 10:49
 */
public class MySvgNodeRendererFactory extends DefaultSvgNodeRendererFactory {

    @Override
    public ISvgNodeRenderer createSvgNodeRendererForTag(IElementNode tag, ISvgNodeRenderer parent) {
        if(SvgConstants.Tags.LINE.equals(tag.name())){
            MyLineSvgNodeRenderer result = new MyLineSvgNodeRenderer();
            // copy from com.itextpdf.svg.renderers.factories.DefaultSvgNodeRendererFactory.createSvgNodeRendererForTag
            // DefsSvgNodeRenderer should not have parental relationship with any renderer, it only serves as a storage
            if (parent != null && !(result instanceof INoDrawSvgNodeRenderer) && !(parent instanceof DefsSvgNodeRenderer)) {
                result.setParent(parent);
            }
            return result;
        }
        else if(SvgConstants.Tags.PATH.equals(tag.name())){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(PathSvgNodeRenderer.class);
            enhancer.setCallback(new MyPathSvgNodeRendererProxy());
            PathSvgNodeRenderer pathSvgNodeRenderer = (PathSvgNodeRenderer) enhancer.create();
            return pathSvgNodeRenderer;
        }
        return super.createSvgNodeRendererForTag(tag, parent);
    }
}
