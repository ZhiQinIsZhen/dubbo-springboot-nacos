package com.liyz.dubbo.service.staff.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.staff.bo.StaffLogoutLogBO;
import com.liyz.dubbo.service.staff.model.StaffLogoutLogDO;
import com.liyz.dubbo.service.staff.remote.RemoteStaffLogoutLogService;
import com.liyz.dubbo.service.staff.service.StaffLogoutLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 11:05
 */
@DubboService
public class RemoteStaffLogoutLogServiceImpl implements RemoteStaffLogoutLogService {

    @Resource
    private StaffLogoutLogService staffLogoutLogService;

    /**
     * 根据staffId分页查询登出日志
     *
     * @param staffId 员工ID
     * @param pageBO 分页信息
     * @return 登出日志
     */
    @Override
    public RemotePage<StaffLogoutLogBO> page(Long staffId, PageBO pageBO) {
        Page page = staffLogoutLogService.page(
                Page.of(pageBO.getPageNum(), pageBO.getPageSize()),
                Wrappers.lambdaQuery(StaffLogoutLogDO.builder().staffId(staffId).build())
        );
        return BeanUtil.copyProperties(page, StaffLogoutLogBO.class);
    }
}
