package com.liyz.dubbo.service.staff.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.staff.bo.StaffRoleBO;
import com.liyz.dubbo.service.staff.model.StaffRoleDO;
import com.liyz.dubbo.service.staff.remote.RemoteStaffRoleService;
import com.liyz.dubbo.service.staff.service.StaffRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/13 13:44
 */
@DubboService
public class RemoteStaffRoleServiceImpl implements RemoteStaffRoleService {

    @Resource
    private StaffRoleService staffRoleService;

    /**
     * 给员工绑定一个角色
     *
     * @param staffRoleBO 员工角色参数
     * @return 员工角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public StaffRoleBO bindRole(StaffRoleBO staffRoleBO) {
        staffRoleService.save(BeanUtil.copyProperties(staffRoleBO, StaffRoleDO.class));
        return staffRoleBO;
    }

    /**
     * 查询员工拥有的角色
     *
     * @param staffId 员工ID
     * @return 员工角色
     */
    @Override
    public List<StaffRoleBO> listByStaffId(Long staffId) {
        return BeanUtil.copyProperties(staffRoleService.list(Wrappers.query(StaffRoleDO.builder().staffId(staffId).build())), StaffRoleBO.class);
    }
}
