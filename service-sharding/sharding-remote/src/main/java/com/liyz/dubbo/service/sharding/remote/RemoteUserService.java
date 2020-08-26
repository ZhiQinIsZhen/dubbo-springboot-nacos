package com.liyz.dubbo.service.sharding.remote;

import com.github.pagehelper.PageInfo;
import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.sharding.bo.UserBO;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:51
 */
public interface RemoteUserService {

    Long addUser(UserBO user);

    List<UserBO> list();

    UserBO findById(Long id);

    UserBO findByName(String name);

    PageInfo<UserBO> page(PageBaseBO pageBaseBO);
}
