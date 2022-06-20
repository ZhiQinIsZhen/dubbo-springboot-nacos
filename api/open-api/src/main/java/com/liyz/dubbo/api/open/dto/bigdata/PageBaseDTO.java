package com.liyz.dubbo.api.open.dto.bigdata;

import com.liyz.dubbo.api.open.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/20 10:59
 */
@Getter
@Setter
public class PageBaseDTO extends PageDTO {

    @ApiModelProperty(value = "关键词")
    private String keyword;
}
