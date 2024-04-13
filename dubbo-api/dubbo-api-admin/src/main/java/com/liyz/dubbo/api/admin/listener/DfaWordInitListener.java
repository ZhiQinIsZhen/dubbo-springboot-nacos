package com.liyz.dubbo.api.admin.listener;

import cn.hutool.dfa.SensitiveUtil;
import com.google.common.collect.Lists;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Desc:dfa算法词初始化
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/9/14 10:17
 */
@Service
public class DfaWordInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //这里可以做一些调用别的服务来初始化数据等等
        //现在由于是测试，我们就写死几个词
        SensitiveUtil.init(Lists.newArrayList("大", "大土豆", "土豆", "刚出锅", "出锅"));
    }
}
