package com.liyz.dubbo.api.open.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/8/27 16:15
 */
@Getter
@Setter
public class PageDTO implements Serializable {
    private static final long serialVersionUID = -8112579193480486838L;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
