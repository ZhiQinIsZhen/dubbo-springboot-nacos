package com.liyz.dubbo.service.sharding.service;

import com.liyz.dubbo.service.sharding.dao.UserMapper;
import com.liyz.dubbo.service.sharding.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:50
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Long addUser(UserDO user) {
        return userMapper.addUser(user);
    }

    public List<UserDO> list() {
        return userMapper.list();
    }

    public UserDO findById(Long id) {
        return userMapper.findById(id);
    }

    public UserDO findByName(String name) {
        return userMapper.findByName(name);
    }
}
