package com.liyz.dubbo.api.web.dto.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/16 16:33
 */
@Data
public class RiskConsensusDTO implements Serializable {
    private static final long serialVersionUID = -2006229914007431754L;

    @ApiModelProperty(value = "信息id", example = "2012")
    private Long id;

    @ApiModelProperty(value = "信息北荣", example = "孙悟空大闹天空。。。。")
    private String content;

    @ApiModelProperty(value = "信息创建时间", example = "2012-10-10 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String finalTitle;

    private String name;

    private String pickedAbstract;

    @ApiModelProperty(value = "消息发布时间", example = "2012-10-10 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    @ApiModelProperty(value = "备注", example = "打得好")
    private String remark;

    @ApiModelProperty(value = "消息正负得分", example = "1.1")
    private Double sentimentScore;

    @ApiModelProperty(value = "消息正负类型", example = "1")
    private Integer sentimentType;

    @ApiModelProperty(value = "信息来源", example = "裁判文书网")
    private String source;

    @ApiModelProperty(value = "信息来源类型", example = "1")
    private String sourceType;

    @ApiModelProperty(value = "标题", example = "大闹天空")
    private String title;

    @ApiModelProperty(value = "网站地址", example = "www.baidu.com")
    private String webSite;
}
