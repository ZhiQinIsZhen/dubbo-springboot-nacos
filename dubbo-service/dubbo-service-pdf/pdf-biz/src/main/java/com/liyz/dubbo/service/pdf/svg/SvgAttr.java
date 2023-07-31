package com.liyz.dubbo.service.pdf.svg;

import com.liyz.dubbo.service.pdf.utils.SvgNumUtils;
import org.apache.batik.anim.dom.SVGOMElement;
import org.apache.batik.util.SVGConstants;

import java.util.function.Function;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/7 10:52
 */
public class SvgAttr {
    private SVGOMElement element;

    public SvgAttr(SVGOMElement element) {
        this.element = element;
    }
    public SvgAttr d(String d){
        setAttrValue(SVGConstants.SVG_D_ATTRIBUTE, d);
        return this;
    }

    public SvgAttr rx(double rx){
        setAttrValue(SVGConstants.SVG_RX_ATTRIBUTE, SvgNumUtils.doubleString(rx));
        return this;
    }

    public SvgAttr original(double x, double y, double width, double height){
        coordinate(x, y);
        size(width, height);
        return this;
    }
    public SvgAttr coordinate(double x, double y){
        setAttrValue(SVGConstants.SVG_X_ATTRIBUTE, SvgNumUtils.doubleString(x));
        setAttrValue(SVGConstants.SVG_Y_ATTRIBUTE, SvgNumUtils.doubleString(y));
        return this;
    }
    public SvgAttr size(double width, double height){
        setAttrValue(SVGConstants.SVG_WIDTH_ATTRIBUTE, SvgNumUtils.doubleString(width));
        setAttrValue(SVGConstants.SVG_HEIGHT_ATTRIBUTE, SvgNumUtils.doubleString(height));
        return this;
    }

    public SvgAttr lineXy(double x1, double y1, double x2, double y2){
        setAttrValue(SVGConstants.SVG_X1_ATTRIBUTE, SvgNumUtils.doubleString(x1));
        setAttrValue(SVGConstants.SVG_Y1_ATTRIBUTE, SvgNumUtils.doubleString(y1));
        setAttrValue(SVGConstants.SVG_X2_ATTRIBUTE, SvgNumUtils.doubleString(x2));
        setAttrValue(SVGConstants.SVG_Y2_ATTRIBUTE, SvgNumUtils.doubleString(y2));
        return this;
    }

    public SvgAttr stroke(String stroke, double strokeWidth){
        setAttrValue(SVGConstants.SVG_STROKE_ATTRIBUTE, stroke);
        setAttrValue(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, SvgNumUtils.doubleString(strokeWidth));
        return this;
    }

    public SvgAttr strokeDasharray(String strokeDasharray){
        setAttrValue(SVGConstants.SVG_STROKE_DASHARRAY_ATTRIBUTE, strokeDasharray);
        return this;
    }
    public SvgAttr strokeLinecap(String strokeLinecap){
        setAttrValue(SVGConstants.SVG_STROKE_LINECAP_ATTRIBUTE, strokeLinecap);
        return this;
    }

    public SvgAttr transform(String transform){
        setAttrValue(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, transform);
        return this;
    }

    public SvgAttr fill(String fill){
        setAttrValue(SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        return this;
    }
    public SvgAttr fill(ColorAttr color){
        setAttrValue(SVGConstants.SVG_FILL_ATTRIBUTE, color, ColorAttr::color);
        return this;
    }
    public SvgAttr fillOpacity(double opacity){
        setAttrValue(SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, opacity);
        return this;
    }

    public SvgAttr font(String fontSize, String fontFamily){
        setAttrValue(SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize);
        setAttrValue(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, fontFamily);
        return this;
    }

    public SvgAttr fontWeight(String fontWeight){
        setAttrValue(SVGConstants.SVG_FONT_WEIGHT_ATTRIBUTE, fontWeight);
        return this;
    }

    public SvgAttr textAnchor(String textAnchor){
        setAttrValue(SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, textAnchor);
        return this;
    }

    /**
     * circle的3个专有属性设置
     * @param cx
     * @param cy
     * @param r
     */
    public SvgAttr circle(double cx, double cy, double r){
        setAttrValue(SVGConstants.SVG_CX_ATTRIBUTE, SvgNumUtils.doubleString(cx));
        setAttrValue(SVGConstants.SVG_CY_ATTRIBUTE, SvgNumUtils.doubleString(cy));
        setAttrValue(SVGConstants.SVG_R_ATTRIBUTE, SvgNumUtils.doubleString(r));
        return this;
    }

    /**
     * 以下元素可以使用points属性：
     * <polyline>
     * <polygon>
     */
    public SvgAttr points(String points){
        setAttrValue(SVGConstants.SVG_POINTS_ATTRIBUTE, points);
        return this;
    }

    public SvgAttr id(String id){
        setAttrValue(SVGConstants.SVG_ID_ATTRIBUTE, id);
        return this;
    }

    public SvgAttr clipPath(String clipPath){
        setAttrValue(SVGConstants.SVG_CLIP_PATH_ATTRIBUTE, clipPath);
        return this;
    }

    /**
     * linearGradient专有属性设置
     */
    public SvgAttr linearGradient(String x1, String y1, String x2, String y2){
        setAttrValue(SVGConstants.SVG_X1_ATTRIBUTE, x1);
        setAttrValue(SVGConstants.SVG_Y1_ATTRIBUTE, y1);
        setAttrValue(SVGConstants.SVG_X2_ATTRIBUTE, x2);
        setAttrValue(SVGConstants.SVG_Y2_ATTRIBUTE, y2);
        return this;
    }

    /**
     * stop专有属性设置
     * iText暂不支持stopOpacity
     * @see com.itextpdf.svg.renderers.impl.LinearGradientSvgNodeRenderer#parseStops(float)
     */
    public SvgAttr stop(String offset, String stopColor, String stopOpacity){
        setAttrValue(SVGConstants.SVG_OFFSET_ATTRIBUTE, offset);
        setAttrValue(SVGConstants.SVG_STOP_COLOR_ATTRIBUTE, stopColor);
        setAttrValue(SVGConstants.SVG_STOP_OPACITY_ATTRIBUTE, stopOpacity);
        return this;
    }

    private void setAttrValue(String attrName, Object value) {
        if (value != null) {
            element.setAttributeNS(null, attrName, value.toString());
        }
    }
    private <T> void setAttrValue(String attrName, T value, Function<T, String> func) {
        if (value != null) {
            String strValue = func != null ? func.apply(value) : value.toString();
            element.setAttributeNS(null, attrName, strValue);
        }
    }

}
