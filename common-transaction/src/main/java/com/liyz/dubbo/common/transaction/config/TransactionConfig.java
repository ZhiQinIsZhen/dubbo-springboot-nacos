package com.liyz.dubbo.common.transaction.config;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 注释:seata 配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 15:16
 */
@Configuration
public class TransactionConfig {

    @Value("${spring.application.name}")
    private String applicationId;
    @Value("${seata.tx-service-group}")
    private String txServiceGroup;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner(applicationId, txServiceGroup);
    }
}
