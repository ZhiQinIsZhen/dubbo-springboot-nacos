package com.liyz.dubbo.service.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyz.dubbo.common.base.request.annotation.Logs;
import com.liyz.dubbo.service.test.model.UserInfoDO;
import com.liyz.dubbo.service.test.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/30 14:09
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    UserInfoServiceImpl userInfoService;

    @Logs
    @GetMapping("/page")
    public Page<UserInfoDO> info(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize) {
        return userInfoService.page(pageNum, pageSize);
    }
}
