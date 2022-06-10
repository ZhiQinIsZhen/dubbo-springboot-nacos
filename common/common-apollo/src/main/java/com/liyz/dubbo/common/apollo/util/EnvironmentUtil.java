package com.liyz.dubbo.common.apollo.util;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.config.ConfigPropertySource;
import com.ctrip.framework.apollo.spring.config.ConfigPropertySourceFactory;
import com.ctrip.framework.apollo.spring.util.SpringInjector;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:17
 */
@Slf4j
@UtilityClass
public class EnvironmentUtil {

    private final ConfigPropertySourceFactory configPropertySourceFactory = SpringInjector
            .getInstance(ConfigPropertySourceFactory.class);

    public boolean isApolloEnabled(ConfigurableEnvironment environment) {
        String key = environment.getProperty("apollo.bootstrap.enabled");
        return key == null || "true".equalsIgnoreCase(key);
    }

    /**
     * 添加Apollo配置
     *
     * @param apolloNamespace Apollo公共配置namespace名
     */
    public void addLast(ConfigurableEnvironment environment, String apolloNamespace) {
        if (!isApolloEnabled(environment)) {
            return;
        }
        // 获取environment中的apollo启动配置（在apollo.bootstrap.namespaces中指定的配置）
        PropertySource<?> apolloConfig = environment.getPropertySources().get("ApolloBootstrapPropertySources");
        if (!(apolloConfig instanceof CompositePropertySource)) {
            return;
        }
        Config config = ConfigService.getConfig(apolloNamespace);
        ConfigPropertySource configPropertySource = configPropertySourceFactory.getConfigPropertySource(apolloNamespace, config);
        // 把config加入到bootstrap配置（优先级高于应用的静态application.properties配置）
        ((CompositePropertySource) apolloConfig).addPropertySource(configPropertySource);
    }

    public void addLast(ConfigurableEnvironment environment, String configName, Map<String, Object> map) {
        MapPropertySource ps = new MapPropertySource(configName, map);
        environment.getPropertySources().addLast(ps);
    }

    public void addLast(ConfigurableEnvironment environment, Resource resource) {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            environment.getPropertySources().addLast(new PropertiesPropertySource(resource.toString(), properties));
        } catch (IOException e) {
            log.error("fail to load resource " + resource, e);
        }
    }

    public void bind(ConfigurableEnvironment environment, Object bean, String... prefixList) {
        if (bean == null || prefixList.length == 0) {
            return;
        }
        MutablePropertySources propertySources = environment.getPropertySources();
        Binder binder = new Binder(ConfigurationPropertySources.from(propertySources),
                new PropertySourcesPlaceholdersResolver(propertySources),
                ApplicationConversionService.getSharedInstance());
        ResolvableType type = ResolvableType.forClass(bean.getClass());
        Bindable<?> target = Bindable.of(type).withExistingValue(bean);
        for (String prefix : prefixList) {
            binder.bind(prefix, target, (BindHandler) new ThrowErrorsBindHandler(BindHandler.DEFAULT));
        }
    }
}
