package com.liyz.dubbo.api.admin.vo.algorithm;

import lombok.*;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/9/28 16:16
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeastActiveVO implements Serializable {
    private static final long serialVersionUID = 1572004060423572679L;

    private String value;

    private int weight;

    private AtomicInteger active = new AtomicInteger(0);

    public void setActive(int active) {
        this.active.set(active);
    }

    public int getActive() {
        return active.get();
    }

    public void increase() {
        active.incrementAndGet();
    }
}
