package com.liyz.dubbo.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:Page query base class
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 15:34
 */
@Getter
@Setter
public class BasePageBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pageNum = 1L;

    private Long pageSize = 10L;
}
