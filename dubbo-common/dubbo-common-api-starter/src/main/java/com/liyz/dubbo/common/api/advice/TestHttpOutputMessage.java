package com.liyz.dubbo.common.api.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/31 20:31
 */
public class TestHttpOutputMessage implements HttpOutputMessage {
    @Override
    public OutputStream getBody() throws IOException {
        return null;
    }

    @Override
    public HttpHeaders getHeaders() {
        return null;
    }
}
