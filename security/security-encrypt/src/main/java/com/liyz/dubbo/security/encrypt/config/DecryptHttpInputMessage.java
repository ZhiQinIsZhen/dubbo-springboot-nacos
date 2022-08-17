package com.liyz.dubbo.security.encrypt.config;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.security.encrypt.annotation.Encrypt;
import com.liyz.dubbo.security.encrypt.exception.RemoteSecurityServiceException;
import com.liyz.dubbo.security.encrypt.exception.SecurityExceptionCodeEnum;
import com.liyz.dubbo.security.encrypt.remote.RemoteAlgorithmService;
import com.liyz.dubbo.security.encrypt.vo.DataResultVO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/17 9:55
 */
public class DecryptHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;
    private InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, Encrypt encrypt, RemoteAlgorithmService remoteAlgorithmService) throws IOException {
        this.headers = inputMessage.getHeaders();
        this.body = inputMessage.getBody();
        String body = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.isBlank(body)) {
            return;
        }
        DataResultVO dataResultVO = JsonMapperUtil.readValue(body, DataResultVO.class);
        if (Objects.isNull(dataResultVO)) {
            return;
        }
        if (encrypt.checkTime() && Objects.nonNull(dataResultVO.getTime()) && System.currentTimeMillis() - dataResultVO.getTime() > encrypt.time()) {
            throw new RemoteSecurityServiceException(SecurityExceptionCodeEnum.DATA_TIME_OUT);
        }
        AuthUser authUser = AuthContext.getAuthUser();
        String afterBody = remoteAlgorithmService.decrypt(dataResultVO.getData(), authUser.getUserId());
        this.body = IOUtils.toInputStream(afterBody, StandardCharsets.UTF_8);
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
