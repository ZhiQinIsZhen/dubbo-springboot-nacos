package com.liyz.dubbo.service.pdf;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/11 9:42
 */
@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = {"com.liyz.dubbo.service.*.dao"})
public class PdfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfServiceApplication.class, args);
    }
}
