package com.liyz.dubbo.api.web.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/26 16:26
 */
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = -4977164266819144794L;

    @NotBlank(groups = {Get.class}, message = "姓名不能为空")
    private String name;

    @NotBlank(groups = {Get.class}, message = "身份证不能为空")
    private String cardNo;

    public interface Get {};
}
