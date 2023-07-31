package com.liyz.dubbo.service.pdf.pdf;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.Property;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/6/8 13:55
 */
@Slf4j
public class PdfStyleFilter implements PdfFilter {
    private Document document;

    public PdfStyleFilter(Document document) {
        this.document = document;
    }

    @Override
    public void filter(IElement element) {
        if(ElementPropertyContainer.class.isAssignableFrom(element.getClass())){
            ElementPropertyContainer epc = ElementPropertyContainer.class.cast(element);
            Object id = epc.getProperty(Property.ID);
            if(null != id){
                // 设置锚点
                epc.setDestination((String) id);
            }
            fontStyle(epc);
        }
    }

    private void fontStyle(ElementPropertyContainer epc) {
        // 依据FONT_WEIGHT来调整字体粗细，可读性可维护性好
        Object fontWeight = epc.getProperty(Property.FONT_WEIGHT);
        if(null != fontWeight){
            /**
             * normal	默认值。定义标准的字符。
             * bold	    定义粗体字符。
             * bolder	定义更粗的字符。
             * lighter	定义更细的字符。
             * 数字     定义由细到粗的字符。400 等同于 normal，而 700 等同于 bold。
             */
            String fw = fontWeight.toString();
            if(StringUtils.containsAnyIgnoreCase(fw, "bold","bolder")){
                epc.setFont(GenPdfContext.getPingFangFontHeavy());
            }else if(StringUtils.containsAnyIgnoreCase(fw, "normal","lighter")){

            }else{
                try {
                    int i = Integer.parseInt((String) fontWeight);
                    if (i >= 600) {
                        epc.setFont(GenPdfContext.getPingFangFontHeavy());
                    }
                } catch (NumberFormatException e) {
                    log.info("字体粗细解析配置异常，fontWeight={}", fontWeight);
                }
            }
        }
        // 字体斜体样式支持
        Optional.ofNullable(epc.getProperty(Property.FONT_STYLE))
                .map(Object::toString)
                .ifPresent(fontStyle -> {
                    if(StringUtils.equalsAnyIgnoreCase(fontStyle, "italic","oblique")){
                        epc.setItalic();
                    }
                });
    }
}
