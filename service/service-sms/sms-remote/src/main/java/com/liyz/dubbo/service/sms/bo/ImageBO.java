package com.liyz.dubbo.service.sms.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:图片验证码
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 16:31
 */
@Getter
@Setter
public class ImageBO implements Serializable {
    private static final long serialVersionUID = -226660306713679847L;

    private String imageToken;

    private String imageCode;

    public interface Image {}
}
