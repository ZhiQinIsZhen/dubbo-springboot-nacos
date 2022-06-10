package com.liyz.dubbo.common.oss.core;

import com.liyz.dubbo.common.apollo.util.EnvironmentUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 13:32
 */
public class OSSEnvLoader implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // OSS配置加载
        EnvironmentUtil.addLast(environment, "common.oss");
    }
}
