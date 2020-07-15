package com.liyz.dubbo.service.file.config;

import com.liyz.dubbo.common.base.util.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;

/**
 * 注释:文件id配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/24 14:44
 */
@Slf4j
@Configuration
public class FileSnowflakeConfig extends SnowflakeIdUtil {

    @Value("${data.center.id}")
    private long dataCenterId;

    @PostConstruct
    public void init() {
        setDatacenterId(dataCenterId);
    }

    public FileSnowflakeConfig() throws UnknownHostException {
        super();
    }

    public long getId() {
        return getNextId();
    }
}
