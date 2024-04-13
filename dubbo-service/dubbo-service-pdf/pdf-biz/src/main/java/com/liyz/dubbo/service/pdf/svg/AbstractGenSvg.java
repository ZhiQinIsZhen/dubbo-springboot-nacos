package com.liyz.dubbo.service.pdf.svg;

import lombok.Getter;
import lombok.Setter;

import java.awt.geom.Dimension2D;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/11 17:01
 */
public abstract class AbstractGenSvg implements GenSvg{
    /**
     * svg尺寸大小
     */
    protected Dimension2D size;
    /**
     * svg尺寸单位，默认 px
     * @see org.w3c.css.values.CssUnitsCSS3
     * @see org.apache.batik.css.parser.CSSLexicalUnit
     */
    @Setter
    @Getter
    protected String sizeUnit = "px";

    public AbstractGenSvg(Dimension2D size) {
        this.size = size;
    }
    public String widthValue(){
        return this.size.getWidth() + sizeUnit;
    }
    public String heightValue(){
        return this.size.getHeight() + sizeUnit;
    }



}
