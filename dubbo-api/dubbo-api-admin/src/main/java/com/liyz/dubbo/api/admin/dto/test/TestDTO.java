package com.liyz.dubbo.api.admin.dto.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/12 16:55
 */
@Getter
@Setter
public class TestDTO implements Serializable {
    private static final long serialVersionUID = 5512564697160213915L;

    @ApiModelProperty(value = "名字")
    @NotBlank(groups = {Hello.class}, message = "名字不能为空")
    @Length(min = 2, max = 10, groups = {Hello.class}, message = "请输入长度2到10长度的名字")
    private String name;

    @ApiModelProperty(value = "时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public interface Hello {}
}
