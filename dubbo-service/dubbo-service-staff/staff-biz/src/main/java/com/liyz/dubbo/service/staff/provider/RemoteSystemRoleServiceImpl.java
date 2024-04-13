package com.liyz.dubbo.service.staff.provider;

import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.staff.bo.SystemRoleBO;
import com.liyz.dubbo.service.staff.model.SystemRoleDO;
import com.liyz.dubbo.service.staff.remote.RemoteSystemRoleService;
import com.liyz.dubbo.service.staff.service.SystemRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/11 13:26
 */
@DubboService
public class RemoteSystemRoleServiceImpl implements RemoteSystemRoleService {

    @Resource
    private SystemRoleService systemRoleService;

    /**
     * 创建一个角色
     *
     * @param systemRoleBO 系统角色参数
     * @return 系统角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemRoleBO addSystemRole(SystemRoleBO systemRoleBO) {
        systemRoleService.save(BeanUtil.copyProperties(systemRoleBO, SystemRoleDO.class));
        return systemRoleBO;
    }

    /**
     * 查询角色列表
     *
     * @return 系统角色列表
     */
    @Override
    public List<SystemRoleBO> list() {
        return BeanUtil.copyProperties(systemRoleService.list(), SystemRoleBO.class);
    }
}
