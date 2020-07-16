package com.liyz.dubbo.service.search.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/16 16:33
 */
@Data
public class RiskConsensusBO implements Serializable {
    private static final long serialVersionUID = -2006229914007431754L;

    private Long id;

    private String content;

    private String createTime;

    private String finalTitle;

    private String name;

    private String pickedAbstract;

    private String publishTime;

    private String remark;

    private Double sentimentScore;

    private Integer sentimentType;

    private String source;

    private String sourceType;

    private String title;

    private String webSite;
}
