package com.liyz.dubbo.common.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc: used for test
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 14:44
 */
@Getter
@Setter
public class TestUtil implements Comparable<Integer> {

    private String name;

    private Integer age;

    @Override
    public int compareTo(Integer o) {
        return o.compareTo(age);
    }
}
