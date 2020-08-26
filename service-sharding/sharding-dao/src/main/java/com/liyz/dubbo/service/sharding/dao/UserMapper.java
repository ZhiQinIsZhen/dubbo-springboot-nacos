package com.liyz.dubbo.service.sharding.dao;

import com.liyz.dubbo.service.sharding.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:48
 */
@Mapper
public interface UserMapper {

    Long addUser(UserDO user);

    List<UserDO> list();

    UserDO findById(Long id);

    UserDO findByName(String name);
}
