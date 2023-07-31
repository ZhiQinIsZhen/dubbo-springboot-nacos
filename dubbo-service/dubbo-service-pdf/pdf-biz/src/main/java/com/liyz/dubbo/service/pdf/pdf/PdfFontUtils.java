package com.liyz.dubbo.service.pdf.pdf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.io.font.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.styledxmlparser.resolver.font.BasicFontProvider;
import com.liyz.dubbo.service.pdf.exception.PdfExceptionCodeEnum;
import com.liyz.dubbo.service.pdf.exception.RemotePdfServiceException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/5/9 17:30
 */
@UtilityClass
@Slf4j
public class PdfFontUtils {
    /**
     * 苹方 特细
     */
    public static final String PingFang_ExtraLight = "pingfang_extralight";
    /**
     * 苹方 细体
     */
    public static final String PingFang_Light = "pingfang_light";
    /**
     * 苹方 常规
     * 在html中使用这个字体：font-family: pingfang_regular
     * 找的这些字体文件规范性差，通过别名方式选择字体
     */
    public static final String PingFang_Regular = "pingfang_regular";
    /**
     * 苹方 中等
     */
    public static final String PingFang_Medium = "pingfang_medium";
    /**
     * 苹方 粗体
     */
    public static final String PingFang_Bold = "pingfang_bold";
    /**
     * 苹方 特粗
     */
    public static final String PingFang_Heavy = "pingfang_heavy";
    /**
     * 微软雅黑
     */
    public static final String WEIRUANYAHEI_REGULAR = "weiruanyahei_regular";
    /**
     * FontSet可被多个线程共享，但操作不是线程安全的。
     */
    public static final FontSet FONT_SET = new FontSet();
    static {
        // 注册字体
        try {
            FontProgramFactory.registerFont("front/PingFang Regular.ttf", PingFang_Regular);
            FontProgramFactory.registerFont("front/PingFang Medium.ttf", PingFang_Medium);
            FontProgramFactory.registerFont("front/PingFang Bold.ttf", PingFang_Bold);
            FontProgramFactory.registerFont("front/PingFang Heavy.ttf", PingFang_Heavy);
            FontProgramFactory.registerFont("front/PingFang Light.ttf", PingFang_Light);
            FontProgramFactory.registerFont("front/PingFang ExtraLight.ttf", PingFang_ExtraLight);
            FontProgramFactory.registerFont("front/WeiRuanYaHei-1.ttf", WEIRUANYAHEI_REGULAR);

            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(PingFang_Regular), PdfEncodings.IDENTITY_H, PingFang_Regular);
            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(PingFang_Medium), PdfEncodings.IDENTITY_H, PingFang_Medium);
            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(PingFang_Bold), PdfEncodings.IDENTITY_H, PingFang_Bold);
            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(PingFang_Heavy), PdfEncodings.IDENTITY_H, PingFang_Heavy);
            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(PingFang_Light), PdfEncodings.IDENTITY_H, PingFang_Light);
            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(PingFang_ExtraLight), PdfEncodings.IDENTITY_H, PingFang_ExtraLight);
            FONT_SET.addFont(FontProgramFactory.createRegisteredFont(WEIRUANYAHEI_REGULAR), PdfEncodings.IDENTITY_H, WEIRUANYAHEI_REGULAR);
        } catch (IOException e) {
            log.error("PdfFontUtils初始化失败!", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static PdfFont getPingFangFontRegular(){
        return createFont(PingFang_Regular);
    }

    public static PdfFont getPingFangFontMedium(){
        return createFont(PingFang_Medium);
    }

    public static PdfFont getPingFangFontExtraLight(){
        return createFont(PingFang_ExtraLight);
    }

    public static PdfFont getPingFangFontLight(){
        return createFont(PingFang_Light);
    }

    public static PdfFont getPingFangFontBold(){
        return createFont(PingFang_Bold);
    }
    public static PdfFont getPingFangFontHeavy(){
        return createFont(PingFang_Heavy);
    }

    /**
     * 微软雅黑
     * @return
     */
    public static PdfFont getWeiRuanYaHeiFont(){
        return createFont(WEIRUANYAHEI_REGULAR);
    }

    public static PdfFont createFont(String fontNameLowerCase) {
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(
                    FontProgramFactory.createRegisteredFont(fontNameLowerCase),
                    PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (IOException e) {
            log.error("创建字体失败。", e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.FRONT_ERROR);
        }
        return font;
    }

    /**
     * 支持中文
     * @param properties
     */
    public static final void supportChinese(ConverterProperties properties){
        properties.setFontProvider(
                new BasicFontProvider(FONT_SET, PingFang_Regular)
        );
    }

    public static void main(String[] args) throws IOException {

//        System.out.println(getPingFangFontRegular());
//        System.out.println(getPingFangFontBold());
//        System.out.println(getPingFangFontLight());
//        System.out.println(getWeiRuanYaHeiFont());

        printFontFileDesc("front/PingFang Regular.ttf");
        printFontFileDesc("front/PingFang Bold.ttf");
        printFontFileDesc("front/PingFang Light.ttf");
        printFontFileDesc("front/PingFang ExtraLight.ttf");
        printFontFileDesc("front/PingFang Heavy.ttf");
        printFontFileDesc("front/PingFang Medium.ttf");

//        ObjectMapper objectMapper = new ObjectMapper();
//        FontProgram fontProgram = FontProgramFactory.createRegisteredFont(PingFang_Regular);
//        FontProgramDescriptor descriptor = FontProgramDescriptorFactory.fetchDescriptor(fontProgram);
////        log.info("文件：{}", fontProgram.get);
//        log.info("文件描述：{}", objectMapper.writeValueAsString(descriptor));
//        log.info("fontNameLowerCase：{}\n", descriptor.getFontNameLowerCase());

    }

    private static void printFontFileDesc(String classpathFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FontProgram fontProgram = FontProgramFactory.createFont(classpathFile, true);
        FontProgramDescriptor descriptor = FontProgramDescriptorFactory.fetchDescriptor(fontProgram);
        log.info("文件：{}", classpathFile);
        log.info("文件描述：{}", objectMapper.writeValueAsString(descriptor));
        log.info("fontNameLowerCase：{}\n", descriptor.getFontNameLowerCase());
    }
}
