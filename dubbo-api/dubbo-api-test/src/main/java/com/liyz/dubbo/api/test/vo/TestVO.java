package com.liyz.dubbo.api.test.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/5/17 15:29
 */
@Getter
@Setter
public class TestVO implements Serializable {
    private static final long serialVersionUID = -7125989692536337045L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;
}
