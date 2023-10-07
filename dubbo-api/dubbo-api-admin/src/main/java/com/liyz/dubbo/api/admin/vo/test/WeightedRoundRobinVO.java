package com.liyz.dubbo.api.admin.vo.test;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/9/27 18:09
 */
@Getter
@Setter
public class WeightedRoundRobinVO implements Serializable {
    private static final long serialVersionUID = 5206392873380218472L;

    private int weight;
    private AtomicLong current = new AtomicLong(0L);
    private String value;

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        this.current.set(0L);
    }

    public long increaseCurrent() {
        return this.current.addAndGet(this.weight);
    }

    public void sel(int total) {
        this.current.addAndGet(-total);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
