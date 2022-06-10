package com.liyz.dubbo.common.apollo.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.property.AutoUpdateConfigChangeListener;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.*;

/**
 * 注释:配置变化监听器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:14
 */
@Slf4j
public class JasyptAutoUpdateConfigChangeListener extends AutoUpdateConfigChangeListener {

    private ConfigurableEnvironment environment;

    JasyptAutoUpdateConfigChangeListener(Environment environment, ConfigurableListableBeanFactory beanFactory) {
        super(environment, beanFactory);
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void onChange(ConfigChangeEvent changeEvent) {
        log.info("Refreshing cached encryptable property sources");
        PropertySources propertySources = environment.getPropertySources();
        propertySources.forEach(this::refreshPropertySource);
        super.onChange(changeEvent);
    }

    @SuppressWarnings("rawtypes")
    private void refreshPropertySource(PropertySource<?> propertySource) {
        if (propertySource instanceof CompositePropertySource) {
            CompositePropertySource cps = (CompositePropertySource) propertySource;
            cps.getPropertySources().forEach(this::refreshPropertySource);
        } else if (propertySource instanceof EncryptablePropertySource) {
            EncryptablePropertySource eps = (EncryptablePropertySource) propertySource;
            eps.refresh();
        }
    }
}
