package com.liyz.dubbo.api.open.vo.sms;

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
 * @date 2022/4/27 16:26
 */
@ApiModel("ImageVO")
@Getter
@Setter
public class ImageVO implements Serializable {
    private static final long serialVersionUID = 5724329280200019648L;

    @ApiModelProperty(value = "图片token")
    private String imageToken;

    @ApiModelProperty(value = "图片验证码zip压缩")
    private String imageBase64;
}
