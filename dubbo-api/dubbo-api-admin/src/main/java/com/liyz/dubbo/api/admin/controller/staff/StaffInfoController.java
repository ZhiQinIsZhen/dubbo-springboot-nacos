package com.liyz.dubbo.api.admin.controller.staff;

import com.liyz.dubbo.api.admin.dto.staff.StaffLogPageDTO;
import com.liyz.dubbo.api.admin.vo.staff.StaffInfoVO;
import com.liyz.dubbo.api.admin.vo.staff.StaffLoginLogVO;
import com.liyz.dubbo.api.admin.vo.staff.StaffLogoutLogVO;
import com.liyz.dubbo.common.api.dto.PageDTO;
import com.liyz.dubbo.common.api.result.PageResult;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.staff.bo.StaffInfoBO;
import com.liyz.dubbo.service.staff.bo.StaffLoginLogBO;
import com.liyz.dubbo.service.staff.bo.StaffLogoutLogBO;
import com.liyz.dubbo.service.staff.remote.RemoteStaffInfoService;
import com.liyz.dubbo.service.staff.remote.RemoteStaffLoginLogService;
import com.liyz.dubbo.service.staff.remote.RemoteStaffLogoutLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/16 16:38
 */
@Api(tags = "员工信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffInfoController {

    @DubboReference
    private RemoteStaffInfoService remoteStaffInfoService;
    @DubboReference
    private RemoteStaffLoginLogService remoteStaffLoginLogService;
    @DubboReference
    private RemoteStaffLogoutLogService remoteStaffLogoutLogService;

    @ApiOperation("查询当前登录员工信息")
    @GetMapping("/current")
    public Result<StaffInfoVO> userInfo() {
        AuthUserBO authUserBO = AuthContext.getAuthUser();
        return Result.success(BeanUtil.copyProperties(remoteStaffInfoService.getByStaffId(authUserBO.getAuthId()), StaffInfoVO.class));
    }

    @PreAuthorize("hasAuthority('DUBBO-API-ADMIN:STAFFINFO'.toUpperCase())")
    @ApiOperation("分页查询员工信息")
    @GetMapping("/page")
    public PageResult<StaffInfoVO> page(PageDTO page) {
        RemotePage<StaffInfoBO> remotePage = remoteStaffInfoService.page(BeanUtil.copyProperties(page, PageBO.class));
        return PageResult.success(BeanUtil.copyProperties(remotePage, StaffInfoVO.class));
    }

    @PreAuthorize("hasAuthority('DUBBO-API-ADMIN:STAFFLOG'.toUpperCase())")
    @ApiOperation("分页查询员工登录日志")
    @GetMapping("/loginLogs/page")
    public PageResult<StaffLoginLogVO> pageLoginLogs(StaffLogPageDTO page) {
        AuthUserBO authUserBO = AuthContext.getAuthUser();
        page = Objects.nonNull(page) ? page : new StaffLogPageDTO();
        page.setStaffId(Objects.nonNull(page.getStaffId()) ? page.getStaffId() : authUserBO.getAuthId());
        RemotePage<StaffLoginLogBO> remotePage = remoteStaffLoginLogService.page(page.getStaffId(), BeanUtil.copyProperties(page, PageBO.class));
        return PageResult.success(BeanUtil.copyProperties(remotePage, StaffLoginLogVO.class));
    }

    @PreAuthorize("hasAuthority('DUBBO-API-ADMIN:STAFFLOG'.toUpperCase())")
    @ApiOperation("分页查询员工登出日志")
    @GetMapping("/logoutLogs/page")
    public PageResult<StaffLogoutLogVO> pageLogoutLogs(StaffLogPageDTO page) {
        AuthUserBO authUserBO = AuthContext.getAuthUser();
        page = Objects.nonNull(page) ? page : new StaffLogPageDTO();
        page.setStaffId(Objects.nonNull(page.getStaffId()) ? page.getStaffId() : authUserBO.getAuthId());
        RemotePage<StaffLogoutLogBO> remotePage = remoteStaffLogoutLogService.page(page.getStaffId(), BeanUtil.copyProperties(page, PageBO.class));
        return PageResult.success(BeanUtil.copyProperties(remotePage, StaffLogoutLogVO.class));
    }
}
