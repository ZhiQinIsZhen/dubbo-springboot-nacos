package com.liyz.dubbo.api.openapi3.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/12 16:59
 */
@Getter
@Setter
public class TestVO implements Serializable {
    private static final long serialVersionUID = -8814136605692691847L;

    @Schema(description = "名字", example = "至秦")
    private String name;

    @Schema(description = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
}
