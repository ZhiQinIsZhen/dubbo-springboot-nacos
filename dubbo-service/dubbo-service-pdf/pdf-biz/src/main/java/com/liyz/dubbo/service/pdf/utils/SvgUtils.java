package com.liyz.dubbo.service.pdf.utils;

import lombok.experimental.UtilityClass;
import org.apache.batik.anim.dom.*;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * @Description
 * @Author ChenHao
 * @Date 2022/11/4 17:25
 */
@UtilityClass
public class SvgUtils {

    public static final SVGOMDocument createDoc(){
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        SVGOMDocument doc = (SVGOMDocument)impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_SVG_TAG, null);
        return doc;
    }
    public static final SVGOMDefsElement createDefs(SVGOMDocument doc){
        return (SVGOMDefsElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_DEFS_TAG);
    }
    public static final SVGOMPathElement createPath(SVGOMDocument doc){
        return (SVGOMPathElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_PATH_TAG);
    }

    public static final SVGOMGElement createGroup(SVGOMDocument doc) {
        return (SVGOMGElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    }
    public static final SVGOMTextElement createText(SVGOMDocument doc) {
        return (SVGOMTextElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_TEXT_TAG);
    }

    public static final SVGOMTSpanElement createTspan(SVGOMDocument doc) {
        return (SVGOMTSpanElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_TSPAN_TAG);
    }

    public static final SVGOMRectElement createRect(SVGOMDocument doc) {
        return (SVGOMRectElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    }
    public static final SVGOMLineElement createLine(SVGOMDocument doc) {
        return (SVGOMLineElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_LINE_TAG);
    }

    public static final SVGOMCircleElement createCircle(SVGOMDocument doc) {
        return (SVGOMCircleElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_CIRCLE_TAG);
    }
    public static final SVGOMPolylineElement createPolyline(SVGOMDocument doc) {
        return (SVGOMPolylineElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_POLYLINE_TAG);
    }
    public static final SVGOMPolygonElement createPolygon(SVGOMDocument doc) {
        return (SVGOMPolygonElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_POLYGON_TAG);
    }
    public static final SVGOMClipPathElement createClipPath(SVGOMDocument doc) {
        return (SVGOMClipPathElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_CLIP_PATH_TAG);
    }
    public static final SVGOMLinearGradientElement createLinearGradient(SVGOMDocument doc){
        return (SVGOMLinearGradientElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_LINEAR_GRADIENT_TAG);
    }
    public static final SVGOMStopElement createStop(SVGOMDocument doc){
        return (SVGOMStopElement)doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_STOP_TAG);
    }

    public static final void appendToDefs(SVGOMDocument doc, SVGOMElement element){
        NodeList nodeList = doc.getElementsByTagName(SVGConstants.SVG_DEFS_TAG);
        if(nodeList.getLength() < 1){
            SVGOMDefsElement defs = createDefs(doc);
            doc.getDocumentElement().appendChild(defs);
            defs.appendChild(element);
        }else{
            SVGOMDefsElement defs = (SVGOMDefsElement) nodeList.item(0);
            defs.appendChild(element);
        }
    }

    public static void output(SVGDocument doc){
        Path path = null;
        try {
            path = Files.createTempFile("svgdemo", ".svg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        output(doc, path);
    }

    public static void output(SVGDocument doc, Path path){
        try {
            output(doc, Files.newOutputStream(path));
            Files.readAllLines(path)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void output(SVGDocument doc, OutputStream out){
        OutputStreamWriter writer=null;
        try {
            writer = new OutputStreamWriter(out);
            SVGTranscoder svgTranscoder = new SVGTranscoder();
            TranscoderInput transcoderInput = new TranscoderInput(doc);
            TranscoderOutput output = new TranscoderOutput(writer);
            svgTranscoder.transcode(transcoderInput, output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void display(SVGDocument doc)  {
//        try {
//            JSVGCanvas canvas = new JSVGCanvas();
//            JFrame f = new JFrame();
//            f.getContentPane().add(canvas);
//            canvas.setSVGDocument(doc);
//            f.pack();
//            f.setVisible(true);
//            synchronized (SvgUtils.class) {
//                System.out.println("暂停");
//                SvgUtils.class.wait();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
