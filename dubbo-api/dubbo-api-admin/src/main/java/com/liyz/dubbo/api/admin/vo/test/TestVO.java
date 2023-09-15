package com.liyz.dubbo.api.admin.vo.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.util.annotation.LyzJsonProperty;
import io.swagger.annotations.ApiModelProperty;
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

    @JsonPropertyDescription("1111")
    @ApiModelProperty(value = "名字")
    @Desensitization(value = DesensitizationType.REAL_NAME)
    private String name;

    @LyzJsonProperty("dog")
    @ApiModelProperty(value = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    @Desensitization(value = DesensitizationType.DFA)
    private String mobile;

    @Desensitization(value = DesensitizationType.DFA)
    private String email;

    @Desensitization(value = DesensitizationType.DFA)
    private String realName;
}
