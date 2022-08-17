package com.liyz.dubbo.security.encrypt.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/17 10:12
 */
@Getter
@Setter
public class DataResultVO implements Serializable {
    private static final long serialVersionUID = 2212612018585480533L;

    private String data;

    private Long time;
}
