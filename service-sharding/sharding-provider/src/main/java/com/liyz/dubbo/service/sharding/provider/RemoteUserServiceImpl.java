package com.liyz.dubbo.service.sharding.provider;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.service.sharding.bo.UserBO;
import com.liyz.dubbo.service.sharding.model.UserDO;
import com.liyz.dubbo.service.sharding.remote.RemoteUserService;
import com.liyz.dubbo.service.sharding.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:54
 */
@DubboService(version = "1.0.0")
public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    UserService userService;

    @Override
    public Long addUser(UserBO user) {
        return userService.addUser(CommonConverterUtil.beanCopy(user, UserDO.class));
    }

    @Override
    public List<UserBO> list() {
        return CommonConverterUtil.ListTransform(userService.list(), UserBO.class);
    }

    @Override
    public UserBO findById(Long id) {
        return CommonConverterUtil.beanCopy(userService.findById(id), UserBO.class);
    }

    @Override
    public UserBO findByName(String name) {
        return CommonConverterUtil.beanCopy(userService.findByName(name), UserBO.class);
    }

    @Override
    public Page<UserBO> page(PageBaseBO pageBaseBO) {
        PageHelper.startPage(pageBaseBO.getPageNum(), pageBaseBO.getPageSize());
        List<UserDO> doList = userService.list();
        PageInfo<UserDO> doPageInfo = new PageInfo<>(doList);
        Page<UserBO> boPage = CommonConverterUtil.transformPage(doPageInfo, UserBO.class);
        return boPage;
    }
}
