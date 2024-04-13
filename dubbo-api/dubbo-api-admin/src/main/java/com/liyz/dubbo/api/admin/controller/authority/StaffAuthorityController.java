package com.liyz.dubbo.api.admin.controller.authority;

import com.liyz.dubbo.api.admin.dto.authority.StaffRoleDTO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.staff.bo.StaffRoleBO;
import com.liyz.dubbo.service.staff.remote.RemoteStaffRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/16 15:25
 */
@Api(tags = "员工权限信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/staff/authority")
public class StaffAuthorityController {

    @DubboReference
    private RemoteStaffRoleService remoteStaffRoleService;

    @PreAuthorize("hasAuthority('DUBBO-API-ADMIN:STAFF-BIND-ROLE'.toUpperCase())")
    @ApiOperation("给员工绑定一个角色")
    @PostMapping("/bindRole")
    public Result<Boolean> bindRole(@RequestBody @Validated StaffRoleDTO staffRoleDTO) {
        AuthUserBO authUser = AuthContext.getAuthUser();
        staffRoleDTO.setStaffId(Objects.nonNull(staffRoleDTO.getStaffId()) ? staffRoleDTO.getStaffId() : authUser.getAuthId());
        remoteStaffRoleService.bindRole(BeanUtil.copyProperties(staffRoleDTO, StaffRoleBO.class));
        return Result.success(Boolean.TRUE);
    }
}
