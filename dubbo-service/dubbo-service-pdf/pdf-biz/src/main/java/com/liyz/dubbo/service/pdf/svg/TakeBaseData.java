package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * description: TODO
 * author: huanglb
 * date 2023/1/13 16:09
 */

@Data
public class TakeBaseData {

    protected List<String> titleList = Lists.newArrayList();

    public BigDecimal maxBigDecimalValue(List<BigDecimal> bigDecimals) {

        if (CollectionUtils.isEmpty(bigDecimals)) {
            bigDecimals = Lists.newArrayList();
        }
        return bigDecimals.stream().filter(p -> Objects.nonNull(p)).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public BigDecimal minBigDecimalValue(List<BigDecimal> bigDecimals) {

        if (CollectionUtils.isEmpty(bigDecimals)) {
            bigDecimals = Lists.newArrayList();
        }
        return bigDecimals.stream().filter(p -> Objects.nonNull(p)).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }


    public Integer maxIntegerValue(List<Integer> integers) {

        if (CollectionUtils.isEmpty(integers)) {
            integers = Lists.newArrayList();
        }
        return integers.stream().filter(p -> Objects.nonNull(p)).max(Integer::compareTo).orElse(Integer.valueOf(0));
    }

    public Integer minIntegerValue(List<Integer> integers) {

        if (CollectionUtils.isEmpty(integers)) {
            integers = Lists.newArrayList();
        }
        return integers.stream().filter(p -> Objects.nonNull(p)).min(Integer::compareTo).orElse(Integer.valueOf(0));
    }


    /**
     * 计算中位数
     *
     * @param ls
     * @return
     */
    public double medianValue(List<Number> ls) {
        double j = 0;

        List<Double> total = Lists.newArrayList();
        ls.forEach(p -> total.add(p.doubleValue()));
        //集合排序
        Collections.sort(total);
        int size = total.size();
        if (size % 2 == 1) {
            j = total.get((size - 1) / 2);
        } else {
            //加0.0是为了把int转成double类型，否则除以2会算错
            j = (total.get(size / 2 - 1) + total.get(size / 2) + 0.0) / 2;
        }
        return j;
    }
}
