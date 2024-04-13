package com.liyz.dubbo.service.pdf.svg.shape;

import com.liyz.dubbo.service.pdf.svg.ColorAttr;
import com.liyz.dubbo.service.pdf.svg.SvgAttr;
import com.liyz.dubbo.service.pdf.utils.SvgUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMRectElement;
import org.apache.batik.anim.dom.SVGOMTextElement;
import org.w3c.dom.Element;

import java.awt.geom.Point2D;
import java.util.function.BiConsumer;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/21 13:12
 */
@Data
@AllArgsConstructor
public class SvgTitle {
    private SVGOMDocument doc;
    private String[] titles;
    private ColorAttr[] colors;
    /**
     * 基础偏移量
     */
    private Point2D offset;
    /**
     * 标题与标题的偏移量
     */
    private Point2D step;
    /**
     * 标题自定义设置
     */
    private BiConsumer<SVGOMTextElement, Point2D> titleCustomSetup;

    public SvgTitle(SVGOMDocument doc, String[] titles, ColorAttr[] colors, Point2D offset, Point2D step) {
        this.doc = doc;
        this.titles = titles;
        this.colors = colors;
        this.offset = offset;
        this.step = step;
    }

    public void draw(){
        Element svg = doc.getDocumentElement();
        for (int i = 0; i < titles.length; i++) {
            double x = offset.getX()+ i*step.getX();
            double y = offset.getY() + i*step.getY();
            SVGOMRectElement rect1 = SvgUtils.createRect(doc);
            new SvgAttr(rect1)
                    .original(x, y+6.8, 20.5, 9.65)
                    .fill(colors[i])
                    .rx(4.56);

            SVGOMTextElement text1 = SvgUtils.createText(doc);
            text1.setTextContent(titles[i]);
            if(titleCustomSetup == null){
                new SvgAttr(text1)
                        .font("16", null)
                        .coordinate(x+ 29.63, y+17);
            }else{
                titleCustomSetup.accept(text1, new Point2D.Double(x,y));
            }
            svg.appendChild(rect1);
            svg.appendChild(text1);
        }
    }
}
