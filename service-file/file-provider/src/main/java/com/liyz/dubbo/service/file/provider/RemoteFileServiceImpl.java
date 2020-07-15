package com.liyz.dubbo.service.file.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.base.util.DateUtil;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.service.file.bo.FileInfoBO;
import com.liyz.dubbo.service.file.bo.FileInfoListBO;
import com.liyz.dubbo.service.file.config.FileSnowflakeConfig;
import com.liyz.dubbo.service.file.constant.FileType;
import com.liyz.dubbo.service.file.model.FileInfoDO;
import com.liyz.dubbo.service.file.remote.RemoteFileService;
import com.liyz.dubbo.service.file.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 17:29
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteFileServiceImpl implements RemoteFileService {

    private static final String DEFAULT_ROOT_PATH = "/file";

    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    FileSnowflakeConfig fileSnowflakeConfig;

    @Override
    public List<String> upload(@NotNull FileInfoListBO fileInfoListBO) {
        if (fileInfoListBO.getFileType() == null || CollectionUtils.isEmpty(fileInfoListBO.getFiles())) {
            throw new RemoteServiceException(CommonCodeEnum.ParameterError);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder path = new StringBuilder();
        List<String> list = new ArrayList<>(fileInfoListBO.getFiles().size());
        for (FileInfoBO file : fileInfoListBO.getFiles()) {
            String[] exts = file.getFileName().split("\\.");
            FileInfoDO fileInfoDO = new FileInfoDO();
            fileInfoDO.setFileKey(String.valueOf(fileSnowflakeConfig.getId()));
            fileInfoDO.setFileName(file.getFileName());
            fileInfoDO.setFileContentType(file.getFileContentType());
            fileInfoDO.setFileType(fileInfoListBO.getFileType().getCode());
            fileInfoDO.setFileExt(exts[exts.length-1]);
            fileInfoDO.setCreateTime(DateUtil.convertLocalDateTimeToDate(localDateTime));
            fileInfoDO.setUpdateTime(DateUtil.convertLocalDateTimeToDate(localDateTime));
            //判断文件是否已经存在
            String fileMd5 = DigestUtils.md5DigestAsHex(file.getBytes());
            FileInfoDO fileDO = fileInfoService.getByMd5(fileMd5);
            if (Objects.isNull(fileDO)) {
                path.append(DEFAULT_ROOT_PATH).append(fileInfoListBO.getFileType().getSubPath()).append(localDateTime.getYear()).append("/")
                        .append(localDateTime.getMonthValue()).append("/").append(localDateTime.getDayOfMonth());
                File upFile = new File(path.toString());
                upFile.setWritable(true, false);
                if (!upFile.exists()) {
                    upFile.mkdirs();
                }
                path.append("/").append(fileInfoDO.getFileKey()).append(".").append(fileInfoDO.getFileExt());
                File dest = new File(path.toString());
                try {
                    FileUtils.copyInputStreamToFile(new ByteArrayInputStream(file.getBytes()), dest);
                } catch (IOException e) {
                    log.error("save file fail error : ", e);
                }
            } else {
                path.append(fileDO.getFilePath());
            }
            fileInfoDO.setFileMd5(fileMd5);
            fileInfoDO.setFilePath(path.toString());
            fileInfoService.save(fileInfoDO);
            path.setLength(0);
            list.add(fileInfoDO.getFileKey());
        }
        return list;
    }

    @Override
    public FileInfoBO download(@NotNull FileInfoBO fileInfoBO) {
        FileInfoDO fileInfoDO = fileInfoService.getOne(CommonConverterUtil.beanCopy(fileInfoBO, FileInfoDO.class));
        if (fileInfoDO == null) {
            throw new RemoteServiceException(CommonCodeEnum.NoData);
        }
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(fileInfoDO.getFilePath()));
            FileInfoBO result = CommonConverterUtil.beanCopy(fileInfoDO, FileInfoBO.class);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int len;
            while((len = fis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            fis.close();
            result.setBytes(bos.toByteArray());
            return result;
        } catch (Exception e) {
            log.error("download fail error", e);
            throw new RemoteServiceException(CommonCodeEnum.NoData);
        }
    }

    @Override
    public boolean delete(@NotNull FileInfoBO fileInfoBO) {
        //这里的删除为逻辑删除，非物理删除
        FileInfoDO fileInfoDO = new FileInfoDO();
        fileInfoDO.setFileKey(fileInfoBO.getFileKey());
        fileInfoDO.setFileType(fileInfoBO.getFileType());
        fileInfoDO.setIsInactive(1);
        fileInfoDO.setUpdateTime(DateUtil.convertLocalDateTimeToDate(LocalDateTime.now()));
        int count = fileInfoService.updateById(fileInfoDO);
        return count > 0 ? true : false;
    }

    @Override
    public String update(@NotNull FileInfoBO fileInfoBO) {
        FileType fileType = FileType.getByCode(fileInfoBO.getFileType());
        FileInfoDO old = fileInfoService.getById(fileInfoBO.getFileKey());
        if (Objects.isNull(old)) {
            throw new RemoteServiceException(CommonCodeEnum.OldFileNotExist);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder path = new StringBuilder();
        String[] exts = fileInfoBO.getFileName().split("\\.");
        FileInfoDO fileInfoDO = new FileInfoDO();
        fileInfoDO.setFileKey(String.valueOf(fileSnowflakeConfig.getId()));
        fileInfoDO.setFileName(fileInfoBO.getFileName());
        fileInfoDO.setFileContentType(fileInfoBO.getFileContentType());
        fileInfoDO.setFileType(fileType.getCode());
        fileInfoDO.setFileExt(exts[exts.length-1]);
        fileInfoDO.setCreateTime(DateUtil.convertLocalDateTimeToDate(localDateTime));
        fileInfoDO.setUpdateTime(DateUtil.convertLocalDateTimeToDate(localDateTime));
        //判断文件是否已经存在
        String fileMd5 = DigestUtils.md5DigestAsHex(fileInfoBO.getBytes());
        FileInfoDO fileDO = fileInfoService.getByMd5(fileMd5);
        if (Objects.isNull(fileDO)) {
            path.append(DEFAULT_ROOT_PATH).append(fileType.getSubPath()).append(localDateTime.getYear()).append("/")
                    .append(localDateTime.getMonthValue()).append("/").append(localDateTime.getDayOfMonth());
            File upFile = new File(path.toString());
            upFile.setWritable(true, false);
            if (!upFile.exists()) {
                upFile.mkdirs();
            }
            path.append("/").append(fileInfoDO.getFileKey()).append(".").append(fileInfoDO.getFileExt());
            File dest = new File(path.toString());
            try {
                FileUtils.copyInputStreamToFile(new ByteArrayInputStream(fileInfoBO.getBytes()), dest);
            } catch (IOException e) {
                log.error("save file fail error : ", e);
            }
        } else {
            path.append(fileDO.getFilePath());
        }
        fileInfoDO.setFilePath(path.toString());
        FileInfoDO newDO = CommonConverterUtil.beanCopy(old, FileInfoDO.class);
        newDO.setIsInactive(1);
        newDO.setFileKey(fileInfoDO.getFileKey());
        fileInfoService.save(newDO);

        fileInfoDO.setFileMd5(fileMd5);
        fileInfoDO.setFileKey(old.getFileKey());
        fileInfoDO.setCreateTime(old.getCreateTime());
        fileInfoService.updateById(fileInfoDO);
        return fileInfoDO.getFileKey();
    }
}
