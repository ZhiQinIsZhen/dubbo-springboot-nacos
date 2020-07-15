package com.liyz.dubbo.service.file.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/24 17:12
 */
@Data
public class FileInfoBO implements Serializable {
    private static final long serialVersionUID = 4002855402379971257L;

    private byte[] bytes;

    private String fileContentType;

    private String fileName;

    @NotBlank(groups = {Down.class}, message = "文件key不能为空")
    private String fileKey;

    @NotNull(groups = {Down.class}, message = "文件类型不能为空")
    private Integer fileType;

    private Integer isInactive = 0;

    public interface Down {}
}
