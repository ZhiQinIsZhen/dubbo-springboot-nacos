package com.liyz.dubbo.api.web.dto.file;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/16 17:35
 */
@Setter
@Getter
public class FileDTO implements Serializable {
    private static final long serialVersionUID = 2480982510380198773L;

    @ApiModelProperty(value = "文件类型", example = "2", required = true)
    @NotNull(groups = {File.class}, message = "文件类型不能为空")
    private Integer fileType;

    @ApiModelProperty(value = "文件key", example = "2", required = true)
    @NotBlank(groups = {File.class}, message = "文件key不能为空")
    private String fileKey;

    public interface File {}
}
