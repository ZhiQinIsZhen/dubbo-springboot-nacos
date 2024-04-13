package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.styledxmlparser.css.util.CssDimensionParsingUtils;
import com.itextpdf.svg.SvgConstants;
import com.itextpdf.svg.renderers.SvgDrawContext;
import com.itextpdf.svg.renderers.impl.LineSvgNodeRenderer;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/15 10:37
 */
public class MyLineSvgNodeRenderer extends LineSvgNodeRenderer {
    @Override
    public void doDraw(SvgDrawContext context) {
        if (this.attributesAndStyles != null) {
            PdfCanvas currentCanvas = context.getCurrentCanvas();
            // 虚线支持
            String strokeDasharray = getAttributeOrDefault(SvgConstants.Attributes.STROKE_DASHARRAY, "");
            strokeDasharray = strokeDasharray.trim();
            if(StringUtils.isNotBlank(strokeDasharray)){
                /*
                 * 暂时只支持简单的虚线定义，例如： 2 2
                 * SVG规范的定义和PDF规范的定义存在差异
                 */
                String[] split = strokeDasharray.split("(\\s+)|,");
                int len = Math.min(2, split.length);
                float[] floats = new float[len];
                for (int i = 0; i < len; i++) {
                    floats[i] = CssDimensionParsingUtils.parseAbsoluteLength(split[i]);
                }
                currentCanvas.setLineDash(floats, 0);
            }
        }

        super.doDraw(context);
    }
}
