package com.liyz.dubbo.common.oss.cofnig;

import com.liyz.dubbo.common.oss.core.OSSFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 13:30
 */
@Configuration
public class OSSAutoConfig {

    @Bean
    OSSFactoryBean ossBucket() {
        return new OSSFactoryBean();
    }
}
