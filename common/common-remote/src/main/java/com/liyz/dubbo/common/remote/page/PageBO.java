package com.liyz.dubbo.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:分页page
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 14:19
 */
@Getter
@Setter
public class PageBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
