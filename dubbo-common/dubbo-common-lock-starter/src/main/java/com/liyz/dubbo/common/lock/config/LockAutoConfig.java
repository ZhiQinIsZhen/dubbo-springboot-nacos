package com.liyz.dubbo.common.lock.config;

import com.liyz.dubbo.common.lock.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/10/12 14:35
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({RedissonProperties.class, RedisProperties.class})
public class LockAutoConfig implements InitializingBean {

    private final RedissonProperties redissonProperties;
    private final RedisProperties redisProperties;

    @Autowired
    private ApplicationContext ctx;
    @Autowired(required = false)
    private List<RedissonAutoConfigurationCustomizer> redissonAutoConfigurationCustomizers;

    public LockAutoConfig(RedissonProperties redissonProperties, RedisProperties redisProperties) {
        this.redissonProperties = redissonProperties;
        this.redisProperties = redisProperties;
    }

    /**
     * redissonClient bean create
     * @see RedissonAutoConfiguration
     *
     * @param redissonProperties
     * @param redisProperties
     * @return
     */
    private RedissonClient redissonClientLock(RedissonProperties redissonProperties, RedisProperties redisProperties) {
        int database = 10;

        Integer timeout = null;
        if (redisProperties.getTimeout() != null) {
            timeout = (int)redisProperties.getTimeout().toMillis();
        }
        Integer connectTimeout = null;
        if (redisProperties.getConnectTimeout() != null) {
            connectTimeout = (int)redisProperties.getConnectTimeout().toMillis();
        }

        Config config;
        if (redissonProperties.getConfig() != null) {
            try {
                config = Config.fromYAML(redissonProperties.getConfig());
            } catch (IOException var20) {
                try {
                    config = Config.fromJSON(redissonProperties.getConfig());
                } catch (IOException var19) {
                    var19.addSuppressed(var20);
                    throw new IllegalArgumentException("Can't parse config", var19);
                }
            }
        }  else if (redissonProperties.getFile() != null) {
            try {
                InputStream is = this.getConfigStream(redissonProperties.getFile());
                config = Config.fromYAML(is);
            } catch (IOException var18) {
                try {
                    InputStream is = this.getConfigStream(redissonProperties.getFile());
                    config = Config.fromJSON(is);
                } catch (IOException var17) {
                    var17.addSuppressed(var18);
                    throw new IllegalArgumentException("Can't parse config", var17);
                }
            }
        } else if (redisProperties.getSentinel() != null) {
            config = new Config();
            SentinelServersConfig c = config
                    .useSentinelServers()
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .addSentinelAddress(this.convert(redisProperties.getSentinel().getNodes()))
                    .setDatabase(database)
                    .setUsername(redisProperties.getUsername())
                    .setPassword(redisProperties.getPassword())
                    .setClientName(redisProperties.getClientName());
            if (connectTimeout != null) {
                c.setConnectTimeout(connectTimeout);
            }

            if (connectTimeout != null && timeout != null) {
                c.setTimeout(timeout);
            }
        } else {
            if (redisProperties.getClientName() != null) {
                String[] nodes = this.convert(redisProperties.getCluster().getNodes());
                config = new Config();
                ClusterServersConfig c = config
                        .useClusterServers()
                        .addNodeAddress(nodes)
                        .setUsername(redisProperties.getUsername())
                        .setPassword(redisProperties.getPassword())
                        .setClientName(redisProperties.getClientName());
                if (connectTimeout != null) {
                    c.setConnectTimeout(connectTimeout);
                }
            } else {
                config = new Config();
                String prefix = redisProperties.isSsl() ? "rediss://" : "redis://";
                SingleServerConfig c = config
                        .useSingleServer()
                        .setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                        .setDatabase(database)
                        .setUsername(redisProperties.getUsername())
                        .setPassword(redisProperties.getPassword())
                        .setClientName(redisProperties.getClientName());
                if (connectTimeout != null) {
                    c.setConnectTimeout(connectTimeout);
                }

                if (connectTimeout != null && timeout != null) {
                    c.setTimeout(timeout);
                }
            }
        }

        if (this.redissonAutoConfigurationCustomizers != null) {
            Iterator var27 = this.redissonAutoConfigurationCustomizers.iterator();

            while(var27.hasNext()) {
                RedissonAutoConfigurationCustomizer customizer = (RedissonAutoConfigurationCustomizer)var27.next();
                customizer.customize(config);
            }
        }

        return Redisson.create(config);
    }

    private String[] convert(List<String> nodesObject) {
        List<String> nodes = new ArrayList(nodesObject.size());
        Iterator var3 = nodesObject.iterator();

        while(true) {
            while(var3.hasNext()) {
                String node = (String)var3.next();
                if (!node.startsWith("redis://") && !node.startsWith("rediss://")) {
                    nodes.add("redis://" + node);
                } else {
                    nodes.add(node);
                }
            }

            return nodes.toArray(new String[0]);
        }
    }

    private InputStream getConfigStream(String file) throws IOException {
        Resource resource = this.ctx.getResource(file);
        return resource.getInputStream();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RedisLockUtil.setRedissonClient(redissonClientLock(redissonProperties, redisProperties));
    }
}
