package com.liyz.dubbo.api.admin.dto.test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/3 14:39
 */
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "jsonType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = JsonTypeNameDTO.class, name = "1"),
        @JsonSubTypes.Type(value = JsonTypeAgeDTO.class, name = "2")
})
public class JsonTypeBaseDTO implements Serializable {
    private static final long serialVersionUID = -9167354945119771695L;

    @ApiModelProperty(value = "多态类型")
    private String jsonType;
}
