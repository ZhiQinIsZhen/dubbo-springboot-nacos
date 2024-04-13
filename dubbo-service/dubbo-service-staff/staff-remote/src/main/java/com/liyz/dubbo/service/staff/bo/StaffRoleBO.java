package com.liyz.dubbo.service.staff.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/13 13:40
 */
@Getter
@Setter
public class StaffRoleBO implements Serializable {
    private static final long serialVersionUID = -5019855188001537438L;

    /**
     * 员工ID
     */
    private Long staffId;

    /**
     * 角色ID
     */
    private Integer roleId;
}
