package com.liyz.dubbo.common.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/2/1 14:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GZipUtil {

    private static final int BUFFER = 1024;


    /**
     * 压缩
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] compress(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(data);
        gzip.close();
        return out.toByteArray();
    }

    /**
     * 压缩
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] compress(String data) throws IOException {
        if (data == null || data.length() == 0) {
            return null;
        }
        return compress(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解压
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] decompress(byte[] data) throws IOException {
        if (data == null || data.length == 0) {
            return data;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        GZIPInputStream gis = new GZIPInputStream(in);
        byte[] buffer = new byte[BUFFER];
        int n;
        while ((n = gis.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        gis.close();
        data = out.toByteArray();
        out.flush();
        out.close();
        in.close();
        return data;
    }

    /**
     * 解压
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static String decompress(String data) throws IOException {
        if (data == null || data.length() == 0) {
            return null;
        }
        byte[] bytes = decompress(data.getBytes(StandardCharsets.UTF_8));
        return new String(bytes);
    }
 }
