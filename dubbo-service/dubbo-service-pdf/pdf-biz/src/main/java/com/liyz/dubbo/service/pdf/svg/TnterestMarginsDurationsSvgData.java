package com.liyz.dubbo.service.pdf.svg;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2023/2/1 11:29
 */
@Getter
@ToString
public class TnterestMarginsDurationsSvgData extends TakeBaseData {

    private List<BigDecimal> datas = Lists.newArrayList();

    public static TnterestMarginsDurationsSvgData mock() {

        TnterestMarginsDurationsSvgData data = new TnterestMarginsDurationsSvgData();
        //生成10个随机数据

        int size = 50;
        for (int i = 1; i <= size; i++) {
            double value = RandomUtils.nextDouble(0.0001D, 1.0000D) * 100;

            BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                int rs = RandomUtils.nextInt(2, 5);
//                if (i%rs==0){
//                    integerValue3=null;
//                }
//                if (i==size){
//                    integerValue3=size;
//                }
            data.addBucket(bigDecimalValue, String.format("第%s季度", i));

        }
        return data;
    }

    /**
     * @param bigDecimalValue
     * @param format
     */
    public void addBucket(BigDecimal bigDecimalValue, String format) {
        datas.add(bigDecimalValue);
        titleList.add(format);
    }


    public BigDecimal maxValue() {
        List<BigDecimal> ls = Lists.newArrayList();
        ls.addAll(datas);
        return maxBigDecimalValue(ls);
    }


    public BigDecimal minValue() {
        List<BigDecimal> ls = Lists.newArrayList();
        ls.addAll(datas);
        return minBigDecimalValue(ls);
    }

}
