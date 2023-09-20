package com.liyz.dubbo.service.search.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:14
 */
@Getter
@Setter
public class SearchBO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * list查询最大数量
     */
    private Integer listMaxCount = 50;

    private Integer trackTotalHits = 1000;

    private Integer slop;
}
