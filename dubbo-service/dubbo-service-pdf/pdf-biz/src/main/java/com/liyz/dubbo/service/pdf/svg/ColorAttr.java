package com.liyz.dubbo.service.pdf.svg;

import lombok.Data;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/16 9:09
 */
@Data
public class ColorAttr {
    public static final ColorAttr NONE = new ColorAttr("none");
    private String color;
    private boolean refDefs = false;

    public ColorAttr() {
    }

    public ColorAttr(String color) {
        this.color = color;
    }

    public ColorAttr(String color, boolean refDefs) {
        this.color = color;
        this.refDefs = refDefs;
    }

    public String color(){
        return refDefs ? "url(#"+color+")" : color;
    }
}
