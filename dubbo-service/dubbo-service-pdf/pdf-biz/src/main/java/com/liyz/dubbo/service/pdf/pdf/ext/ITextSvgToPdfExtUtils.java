package com.liyz.dubbo.service.pdf.pdf.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/12/1 18:27
 */
@Slf4j
public class ITextSvgToPdfExtUtils {
//    private static final ClassPool CLASS_POOL;
//
//    private static String AbstractSvgNodeRenderer = "com.itextpdf.svg.renderers.impl.AbstractSvgNodeRenderer";
//    static {
//        CLASS_POOL = ClassPool.getDefault();
//        try {
////            if("jar".equalsIgnoreCase(getRunProtocol())){
////                CLASS_POOL.appendClassPath("BOOT-INF/lib/svg-7.2.1.jar");
//                InputStream inputStream = new ClassPathResource("classresources/AbstractSvgNodeRenderer.txt").getInputStream();
//                byte[] bytes = IOUtils.readFully(inputStream, inputStream.available());
//                CLASS_POOL.appendClassPath(new ByteArrayClassPath(AbstractSvgNodeRenderer, bytes));
////            }
//        } catch (Exception e) {
//            log.warn("javassist insertClassPath失败！", e);
//        }
//    }
//    /**
//     * 支持透明度
//     * iText框架的SVG转PDF对透明度支持很差，几乎不支持；这里主要针对方式二进行简单修改后是支持的。
//     * 方式一：fill="rgba(67, 173, 127, 0.50)"      必须是rgba格式
//     * 方式二：fill="url(#xxx)" fill-opacity="0.4"
//     * @see com.itextpdf.svg.renderers.impl.AbstractSvgNodeRenderer#getColorFromAttributeValue
//     * @throws Exception
//     */
//    private static final void supportFillOpacity() throws Exception{
//
//        CtClass c = CLASS_POOL.get(AbstractSvgNodeRenderer);
//        CtMethod m = c.getDeclaredMethod("getColorFromAttributeValue");
//        // 把写死的变量resolvedOpacity换成了parentOpacity
//        String javaCode = "return new com.itextpdf.layout.properties.TransparentColor(resolvedColor, parentOpacity);";
//        m.insertAt(453, true, javaCode);
//        c.toClass();
//        log.info("supportFillOpacity，成功！");
//    }

//    public static final void ext(){
//        try {
//            supportFillOpacity();
//        } catch (Exception e) {
//            log.warn("修改iText源码失败！某些扩展的支持将失效!", e);
//        }
//    }
    private static String getRunProtocol(){
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        String protocol = defaultClassLoader.getResource("").getProtocol();
        log.info("当前程序运行protocol：{}", protocol);
        return protocol;
    }
    public static void main(String[] args) throws IOException {
//        ext();

        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        System.out.println(defaultClassLoader.getResource("").getProtocol());
        Properties properties = System.getProperties();
        Enumeration<?> enumeration = properties.propertyNames();
        while(enumeration.hasMoreElements()){
            Object o = enumeration.nextElement();
            System.out.println(o + "\t" +properties.get(o));
        }
    }
}
