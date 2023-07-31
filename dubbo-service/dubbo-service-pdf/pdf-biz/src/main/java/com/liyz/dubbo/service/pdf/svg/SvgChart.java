package com.liyz.dubbo.service.pdf.svg;

public interface SvgChart<T> {
    String createSvg(T data);
}
