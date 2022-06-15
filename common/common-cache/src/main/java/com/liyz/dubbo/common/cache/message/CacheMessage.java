package com.liyz.dubbo.common.cache.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/14 16:48
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheMessage implements Serializable {
    private static final long serialVersionUID = -3531845882851648997L;

    /**
     * 缓存名称
     */
    private String cacheName;

    /**
     * 缓存key
     */
    private Object key;
}
