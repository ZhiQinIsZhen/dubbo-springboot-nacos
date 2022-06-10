package com.liyz.dubbo.common.oss.core;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 13:30
 */
@Getter
@AllArgsConstructor
public class OSSBucket {

    private OSS oss;
    private String name;

    public PutObjectResult putObject(String key, InputStream input, ObjectMetadata metadata)
            throws OSSException, ClientException {
        return oss.putObject(name, key, input, metadata);
    }

    public PutObjectResult putObject(String key, InputStream input)
            throws OSSException, ClientException {
        return oss.putObject(name, key, input);
    }

    public OSSObject getObject(String key) throws OSSException, ClientException {
        return oss.getObject(name, key);
    }

    public OSSObject getObject(GetObjectRequest getObjectRequest) throws OSSException, ClientException {
        return oss.getObject(getObjectRequest);
    }

    public ObjectMetadata getObject(GetObjectRequest getObjectRequest, File file) throws OSSException, ClientException {
        return oss.getObject(getObjectRequest, file);
    }

    public void deleteObject(String key) throws OSSException, ClientException {
        oss.deleteObject(name, key);
    }

    public URL generatePresignedUrl(String key, Date expiration, HttpMethod method)
            throws ClientException {
        return oss.generatePresignedUrl(name, key, expiration, method);
    }
}
