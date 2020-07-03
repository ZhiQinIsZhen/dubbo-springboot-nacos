package com.liyz.dubbo.api.web.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/15 17:32
 */
@ApiModel(value = "ImageVO", description = "图片验证码信息")
@Getter
@Setter
public class ImageVO implements Serializable {
    private static final long serialVersionUID = -1857900151615116181L;

    @ApiModelProperty(value = "图片token", example = "fksld232")
    private String imageToken;

    @ApiModelProperty(value = "图片验证码Base64", example = "fjlafjl43892")
    private String imageBase64;
}
