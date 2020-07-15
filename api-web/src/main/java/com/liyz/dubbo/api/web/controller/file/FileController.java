package com.liyz.dubbo.api.web.controller.file;

import com.liyz.dubbo.api.web.dto.file.FileDTO;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.controller.util.HttpRequestUtil;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.common.web.security.annotation.Anonymous;
import com.liyz.dubbo.service.file.bo.FileInfoBO;
import com.liyz.dubbo.service.file.bo.FileInfoListBO;
import com.liyz.dubbo.service.file.constant.FileType;
import com.liyz.dubbo.service.file.remote.RemoteFileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/16 17:07
 */
@Api(value = "文件服务", tags = "文件服务")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @DubboReference(version = "1.0.0")
    RemoteFileService remoteFileService;

    @ApiOperation(value = "批量上传图片,支持JPG、PNG、JPEG、BMP", notes = "type说明：0：上传头像，1：身份认证，3：banner图片，4：logo")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/upload")
    public Result<List<String>> upload(@ApiParam(value = "0", required = true) @RequestParam("fileType") Integer fileType,
                         @RequestParam("files") MultipartFile[] files) throws IOException {
        if (fileType == null || files == null || files.length <= 0) {
            return Result.error(CommonCodeEnum.ParameterError);
        }
        FileType type = FileType.getByCode(fileType);
        if (type == null) {
            return Result.error(CommonCodeEnum.ParameterError);
        }
        List<FileInfoBO> boList = new ArrayList<>(files.length);
        for (MultipartFile multipartFile : files) {
            FileInfoBO fileInfoBO = new FileInfoBO();
            fileInfoBO.setFileContentType(multipartFile.getContentType());
            fileInfoBO.setFileName(multipartFile.getOriginalFilename());
            fileInfoBO.setBytes(multipartFile.getBytes());
            boList.add(fileInfoBO);
        }
        FileInfoListBO listBO = new FileInfoListBO();
        listBO.setFileType(type);
        listBO.setFiles(boList);
        List<String> list = remoteFileService.upload(listBO);
        return Result.success(list);
    }

    @Anonymous
    @ApiOperation(value = "下载文件", notes = "type说明：0：上传头像，1：身份认证，3：banner图片，4：logo")
    @GetMapping("/down")
    public Result download(@Validated({FileDTO.File.class}) FileDTO fileDTO) {
        HttpServletResponse response = HttpRequestUtil.getResponse();
        FileInfoBO fileInfoBO = remoteFileService.download(CommonConverterUtil.beanCopy(fileDTO, FileInfoBO.class));
        if (fileInfoBO != null) {
            response.setContentType(fileInfoBO.getFileContentType());
            response.setCharacterEncoding("utf-8");
            InputStream inputStream;
            try {
                response.setHeader("Content-Disposition", "inline;fileName=" + URLEncoder.encode(fileInfoBO.getFileName(), "UTF-8"));
                OutputStream nos = response.getOutputStream();
                inputStream = new ByteArrayInputStream(fileInfoBO.getBytes());
                int len;
                byte[] buffer = new byte[2048];
                while ((len = inputStream.read(buffer)) != -1) {
                    nos.write(buffer, 0, len);
                }
                nos.flush();
            } catch (IOException e) {
                log.error("download file write fail", e);
            }
        }
        return Result.success();
    }

    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "删除文件", notes = "type说明：0：上传头像，1：身份认证，3：banner图片，4：logo")
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@Validated({FileDTO.File.class}) FileDTO fileDTO) {
        FileType type = FileType.getByCode(fileDTO.getFileType());
        if (type == null) {
            return Result.error(CommonCodeEnum.ParameterError);
        }
        FileInfoBO fileInfoBO = new FileInfoBO();
        fileInfoBO.setFileKey(fileDTO.getFileKey());
        fileInfoBO.setFileType(fileDTO.getFileType());
        return Result.success(remoteFileService.delete(fileInfoBO));
    }

    @ApiOperation(value = "修改图片,支持JPG、PNG、JPEG、BMP",
            notes = "type说明：0：上传头像，1：身份认证，3：banner图片，4：logo")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/update")
    public Result<String> update(@ApiParam(value = "0", required = true) Integer fileType,
                         @ApiParam(value = "1234567890", required = true) String fileKey,
                         @RequestParam("file") MultipartFile file) throws IOException {
        if (fileType == null || file == null || StringUtils.isBlank(fileKey)) {
            return Result.error(CommonCodeEnum.ParameterError);
        }
        FileType type = FileType.getByCode(fileType);
        if (type == null) {
            return Result.error(CommonCodeEnum.ParameterError);
        }
        FileInfoBO fileInfoBO = new FileInfoBO();
        fileInfoBO.setFileContentType(file.getContentType());
        fileInfoBO.setFileName(file.getOriginalFilename());
        fileInfoBO.setBytes(file.getBytes());
        fileInfoBO.setFileKey(fileKey);
        fileInfoBO.setFileType(fileType);
        return Result.success(remoteFileService.update(fileInfoBO));
    }
}
