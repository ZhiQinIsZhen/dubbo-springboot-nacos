package com.liyz.dubbo.service.staff.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.staff.bo.StaffLoginLogBO;
import com.liyz.dubbo.service.staff.model.StaffLoginLogDO;
import com.liyz.dubbo.service.staff.remote.RemoteStaffLoginLogService;
import com.liyz.dubbo.service.staff.service.StaffLoginLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:50
 */
@DubboService
public class RemoteStaffLoginLogServiceImpl implements RemoteStaffLoginLogService {

    @Resource
    private StaffLoginLogService staffLoginLogService;

    /**
     * 根据staffId分页查询登录日志
     *
     * @param staffId 员工ID
     * @param pageBO 分页信息
     * @return 员工登录日志
     */
    @Override
    public RemotePage<StaffLoginLogBO> page(Long staffId, PageBO pageBO) {
        Page page = staffLoginLogService.page(
                Page.of(pageBO.getPageNum(), pageBO.getPageSize()),
                Wrappers.lambdaQuery(StaffLoginLogDO.builder().staffId(staffId).build())
        );
        return BeanUtil.copyProperties(page, StaffLoginLogBO.class);
    }
}
