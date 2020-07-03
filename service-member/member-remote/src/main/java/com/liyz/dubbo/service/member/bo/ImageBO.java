package com.liyz.dubbo.service.member.bo;

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
@Getter
@Setter
public class ImageBO implements Serializable {
    private static final long serialVersionUID = -1857900151615116181L;

    private String imageToken;

    private String imageBase64;

    public interface Image {}
}
