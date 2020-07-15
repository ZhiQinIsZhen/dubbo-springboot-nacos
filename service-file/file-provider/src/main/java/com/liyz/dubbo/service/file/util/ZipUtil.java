package com.liyz.dubbo.service.file.util;

import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/17 15:54
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ZipUtil {

    public static final byte[] compress(byte[] decompress) {
        if(decompress == null)
            return null;

        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;

        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(decompress);
            zout.closeEntry();
            compressed = out.toByteArray();
        } catch(IOException e) {
            compressed = null;
        } finally {
            if(zout != null) {
                try{zout.close();} catch(IOException e){}
            }
            if(out != null) {
                try{out.close();} catch(IOException e){}
            }
        }
        return compressed;
    }

    /**
     * 解压缩
     * @param compressed
     * @return
     */
    public static final String decompress(byte[] compressed) {
        if (compressed == null)
            return null;
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed;
        try {
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            ZipEntry entry = zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = new String(out.toByteArray(),"GBK");
        } catch (IOException e) {
            decompressed = null;
            throw new RuntimeException("解压缩字符串数据出错", e);
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                    log.debug("关闭ZipInputStream", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("关闭ByteArrayInputStream", e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.debug("关闭ByteArrayOutputStream", e);
                }
            }
        }
        return decompressed;
    }

    private static final String path = "C:\\Users\\liyangzhen\\Desktop\\dubbo-admin\\test\\dubbo-admin-0.1.jar";

    private static final String outPath = "C:\\file\\test\\FILE_NAME.zip";

    public static void file() {
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(outPath);
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int len;
            while((len = fis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            fis.close();
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(bos.toByteArray()), zipFile);
            printInfo(beginTime);
        } catch (Exception e) {
            log.error("download fail error", e);
            throw new RemoteServiceException(CommonCodeEnum.NoData);
        }

    }

    public static void fileChanel() throws Exception {
        long beginTime = System.currentTimeMillis();
        FileChannel fileChannel = new FileInputStream(path).getChannel();
        System.out.println(fileChannel.position());
        System.out.println(fileChannel.size());
        ByteBuffer byteBuffer= ByteBuffer.allocate(2*1024);
        int n=0;
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();//缓冲区写——> 读
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear();//缓冲区不会被自动覆盖，需要主动调用该方法
        }
        fileChannel.force(true);
        fileChannel.close();

    }

    public static void zipFileBuffer() {
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(outPath);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间

            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path))) {
                zipOut.putNextEntry(new ZipEntry("FILE_NAME.jar"));
                int temp = 0;
                while ((temp = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(temp);
                }
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFileChannel() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(outPath);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            try (FileChannel fileChannel = new FileInputStream(path).getChannel()) {
                zipOut.putNextEntry(new ZipEntry("FILE_NAME.jar"));
                fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
            }
            printInfo(beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printInfo(long beginTime) {
        System.out.println("耗时:"+ (System.currentTimeMillis()-beginTime));
    }
}
