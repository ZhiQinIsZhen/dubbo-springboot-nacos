package com.liyz.dubbo.common.remote.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/16 15:06
 */
@Data
public class PageBaseBO implements Serializable {
    private static final long serialVersionUID = 8201898456460930652L;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
