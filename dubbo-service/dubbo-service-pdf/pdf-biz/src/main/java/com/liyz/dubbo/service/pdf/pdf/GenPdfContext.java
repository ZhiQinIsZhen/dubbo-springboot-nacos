package com.liyz.dubbo.service.pdf.pdf;

import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.itextpdf.kernel.font.PdfFont;
import com.liyz.dubbo.service.pdf.test.directory.Dire;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/6/2 14:56
 */
@Slf4j
public class GenPdfContext {

    private static final String TOC_KEY = "toc_key";
    private static final String CONTENT_MAX_PAGENUMBER_KEY = "content_max_pagenumber_key";
    private static final String TIMING_KEY = "timing_key";
    /**
     * 企业规模
     */
    public static final String ENTERPRISE_SCALE_KEY = "enterprise_scale_key";
    public static final String RISK_ASSESS_ID_KEY = "risk_assess_id";
    public static final String OUT_RISK_ASSESS_ID_KEY = "out_risk_assess_id";
    private static final ThreadLocal<Map<String, Object>> context = new InheritableThreadLocal<>();

    private static void ensureResourcesInitialized(){
        if (context.get() == null){
            context.set(new HashMap<>(8));
        }
    }
    public static final void putToc(Map<String, Dire> tocMap){
        ensureResourcesInitialized();
        context.get().put(TOC_KEY, tocMap);
    }
    public static final Dire getTocById(String id){
        Map<String, Object> map = context.get();
        if (map != null) {
            Object o = map.get(TOC_KEY);
            if (o != null) {
                return ((Map<String, Dire>)o).get(id);
            }
        }
        return null;
    }

    /**
     * 记录内容页的最大页是pageNumber
     * @param pageNumber
     */
    public static final void markContentMaxPageNumber(int pageNumber){
        ensureResourcesInitialized();
        context.get().put(CONTENT_MAX_PAGENUMBER_KEY, pageNumber);
    }
    public static final int getContentMaxPageNumber(){
        ensureResourcesInitialized();
        return (int) context.get().getOrDefault(CONTENT_MAX_PAGENUMBER_KEY, 0);
    }

    public static final void timing(Supplier<String> message){
        ensureResourcesInitialized();
        Stopwatch stopwatch = (Stopwatch) context.get().computeIfAbsent(TIMING_KEY, key -> Stopwatch.createStarted());
        if(null != message){
            log.info("{}，已耗时：{}s", message.get(), stopwatch.elapsed(TimeUnit.MILLISECONDS)/1000f);
        }
    }

    /**
     * 保证一个PdfFont对象在一次PDF生成过程中（严格来说是一个{@link com.itextpdf.layout.Document}对象的生命周期内）是复用的，
     * 可大大减少PDF文件大小、提升PDF生成效率。
     * @return
     */
    public static PdfFont getPingFangFontRegular(){
        ensureResourcesInitialized();
        return (PdfFont) context.get()
                .computeIfAbsent(PdfFontUtils.PingFang_Regular, PdfFontUtils::createFont);
    }

    /**
     * 保证一个PdfFont对象在一次PDF生成过程中（严格来说是一个{@link com.itextpdf.layout.Document}对象的生命周期内）是复用的，
     * 可大大减少PDF文件大小、提升PDF生成效率。
     * @return
     */
    public static PdfFont getPingFangFontMedium(){
        ensureResourcesInitialized();
        return (PdfFont) context.get()
                .computeIfAbsent(PdfFontUtils.PingFang_Medium, PdfFontUtils::createFont);
    }
    /**
     * 保证一个PdfFont对象在一次PDF生成过程中（严格来说是一个{@link com.itextpdf.layout.Document}对象的生命周期内）是复用的，
     * 可大大减少PDF文件大小、提升PDF生成效率。
     * @return
     */
    public static PdfFont getPingFangFontExtraLight(){
        ensureResourcesInitialized();
        return (PdfFont) context.get()
                .computeIfAbsent(PdfFontUtils.PingFang_ExtraLight, PdfFontUtils::createFont);
    }
    /**
     * 保证一个PdfFont对象在一次PDF生成过程中（严格来说是一个{@link com.itextpdf.layout.Document}对象的生命周期内）是复用的，
     * 可大大减少PDF文件大小、提升PDF生成效率。
     * @return
     */
    public static PdfFont getPingFangFontLight(){
        ensureResourcesInitialized();
        return (PdfFont) context.get()
                .computeIfAbsent(PdfFontUtils.PingFang_Light, PdfFontUtils::createFont);
    }
    /**
     * 保证一个PdfFont对象在一次PDF生成过程中（严格来说是一个{@link com.itextpdf.layout.Document}对象的生命周期内）是复用的，
     * 可大大减少PDF文件大小、提升PDF生成效率。
     * @return
     */
    public static PdfFont getPingFangFontBold(){
        ensureResourcesInitialized();
        return (PdfFont) context.get()
                .computeIfAbsent(PdfFontUtils.PingFang_Bold, PdfFontUtils::createFont);
    }

    /**
     * 保证一个PdfFont对象在一次PDF生成过程中（严格来说是一个{@link com.itextpdf.layout.Document}对象的生命周期内）是复用的，
     * 可大大减少PDF文件大小、提升PDF生成效率。
     * @return
     */
    public static PdfFont getPingFangFontHeavy(){
        ensureResourcesInitialized();
        return (PdfFont) context.get()
                .computeIfAbsent(PdfFontUtils.PingFang_Heavy, PdfFontUtils::createFont);
    }
    public static void put(String key, Object value){
        ensureResourcesInitialized();
        context.get().put(key, value);
    }
    public static Object get(String key){
        return Optional.ofNullable(context.get())
                .map(m -> m.get(key))
                .orElse(null);
    }
    public static final void remove(){
        context.remove();
    }
}
