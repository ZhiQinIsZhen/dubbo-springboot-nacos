package com.liyz.dubbo.api.openapi3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "测试请求DTO", description = "测试请求")
public class TestDTO implements Serializable {
    private static final long serialVersionUID = 5512564697160213915L;

    @Schema(description = "名字", example = "至秦")
    @NotBlank(groups = {Hello.class}, message = "名字不能为空")
    @Length(min = 2, max = 10, groups = {Hello.class}, message = "请输入长度2到10长度的名字")
    private String name;

    @Schema(description = "时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public interface Hello {}
}
