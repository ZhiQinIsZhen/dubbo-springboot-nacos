package com.liyz.dubbo.common.apollo;

import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.liyz.dubbo.common.apollo.util.EnvironmentUtil;
import com.liyz.dubbo.common.apollo.util.ProductModeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:19
 */
@Slf4j
public class BeforeApollo implements EnvironmentPostProcessor, PropertySourcesConstants, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ProductModeUtil.environment = environment;

        // 改变apollo默认行为：默认开启，除非手动指定false
        if (!EnvironmentUtil.isApolloEnabled(environment)) {
            log.info("Apollo not enabled");
            return;
        }
        System.setProperty(APOLLO_BOOTSTRAP_ENABLED, "true");
        // 日志系统中需要用到app.id等变量需要在eager加载
        System.setProperty(APOLLO_BOOTSTRAP_EAGER_LOAD_ENABLED, "true");
        LinkedHashSet<String> namespaces = new LinkedHashSet<>(Arrays.asList("application", "common.env"));
        String other = environment.getProperty(APOLLO_BOOTSTRAP_NAMESPACES);
        if (other != null) {
            namespaces.addAll(Arrays.asList(other.split("\\s*,\\s*")));
        }
        System.setProperty(APOLLO_BOOTSTRAP_NAMESPACES, String.join(",", namespaces));
    }

    @Override
    public int getOrder() {
        // apollo优先级为0，这里设置-1优先于apollo
        return -1;
    }
}
