package com.liyz.dubbo.service.search.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/10 15:06
 */
@Getter
@Setter
public class BasePageQuery extends PageQuery {
    private static final long serialVersionUID = -4518623596531429000L;

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
     * 主键IDs
     */
    private List<String> ids;

    /**
     * list查询最大数量
     */
    private Integer listMaxCount = 50;

    /**
     * 跟踪命中数
     */
    private Integer trackTotalHits = 1000;

    /**
     * 权重
     */
    private Integer slop;
}
