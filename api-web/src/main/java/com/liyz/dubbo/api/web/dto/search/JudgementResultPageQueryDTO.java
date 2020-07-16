package com.liyz.dubbo.api.web.dto.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liyz.dubbo.api.web.dto.page.PageBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/16 15:12
 */
@Data
public class JudgementResultPageQueryDTO extends PageBaseDTO {
    private static final long serialVersionUID = -3549952111209773867L;

    @ApiModelProperty(value = "关键词", example = "2012")
    private String keyWord;

    @ApiModelProperty(value = "查询开始时间", example = "2011-12-12 11:11:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "查询结束时间", example = "2012-12-12 11:11:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
