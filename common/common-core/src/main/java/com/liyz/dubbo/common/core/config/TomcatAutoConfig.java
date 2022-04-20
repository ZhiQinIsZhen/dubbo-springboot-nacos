package com.liyz.dubbo.common.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 注释:tomcat文件传输配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 17:18
 */
@ComponentScan(basePackages = {"com.liyz.dubbo.common.core"})
@Configuration
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
public class TomcatAutoConfig {

    @Value("${spring.multipart.maxFileSize:10}")
    private String MaxFileSize;
    @Value("${spring.multipart.maxRequestSize:10}")
    private String MaxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(Long.valueOf(MaxFileSize))); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(Long.valueOf(MaxRequestSize)));
        return factory.createMultipartConfig();
    }
}
