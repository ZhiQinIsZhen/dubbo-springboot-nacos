package com.liyz.dubbo.api.admin.vo.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/9/27 15:30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeightRandomVO implements Serializable {
    private static final long serialVersionUID = 3334569175413122559L;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "权重")
    private Integer weight;
}
