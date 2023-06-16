package com.liyz.dubbo.service.staff.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.common.service.util.LoginUserContext;
import com.liyz.dubbo.service.staff.bo.StaffInfoBO;
import com.liyz.dubbo.service.staff.remote.RemoteStaffInfoService;
import com.liyz.dubbo.service.staff.service.StaffInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 10:48
 */
@Slf4j
@DubboService
public class RemoteStaffInfoServiceImpl implements RemoteStaffInfoService {

    @Resource
    private StaffInfoService staffInfoService;

    /**
     * 根据staffId获取用户信息
     *
     * @param staffId 员工ID
     * @return 员工信息
     */
    @Override
    public StaffInfoBO getByStaffId(Long staffId) {
        log.info("attachment id : {}", LoginUserContext.getLoginId());
        return BeanUtil.copyProperties(staffInfoService.getById(staffId), StaffInfoBO.class);
    }

    /**
     * 分页查询员工信息
     *
     * @param pageBO 分页信息
     * @return 员工信息
     */
    @Override
    public RemotePage<StaffInfoBO> page(PageBO pageBO) {
        Page page = staffInfoService.page(Page.of(pageBO.getPageNum(), pageBO.getPageSize()));
        return BeanUtil.copyProperties(page, StaffInfoBO.class);
    }
}
