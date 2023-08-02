package com.liyz.dubbo.common.api.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/31 20:31
 */
public class TestHttpInputMessage implements HttpInputMessage {

    @Override
    public InputStream getBody() throws IOException {
        return null;
    }

    @Override
    public HttpHeaders getHeaders() {
        return null;
    }
}
