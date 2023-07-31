package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/3 11:02
 */
@Data
@NoArgsConstructor
public class RegionIndicator extends TableVO<BigDecimal> implements Serializable {

    /**
     * GDP(亿元)   GDP增速(%) 人均GDP(元) 人口(万人)
     * 地方政府债务余额(亿元)  财政自给率(%)  债务率(宽口径)(%)
     */
    private Integer type;
    /**
     * 关键财务指标名称
     */
    private String name;

    private String[] yLabels;

    private String[] lineRowKeys;


    public RegionIndicator(Integer type, List<String> titleList, List<BigDecimal> datas) {
        super(titleList, datas);
        this.type = type;
        this.setOtherValue();
    }

    /**
     * 设置其它数据
     */
    public void setOtherValue() {
        setOtherValueType(1, "GDP(亿元)", new String[]{"GDP:亿元"}, new String[]{"GDP(亿元)"});
        setOtherValueType(2, "GDP增速(%)", new String[]{"GDP增速:%"}, new String[]{"GDP增速(%)"});
        setOtherValueType(3, "人均GDP(元)", new String[]{"人均GDP:元"}, new String[]{"人均GDP(元)"});
        setOtherValueType(4, "人口(万人)", new String[]{"人口:万人"}, new String[]{"人口(万人)"});
        setOtherValueType(5, "固定资产投资(亿元)", new String[]{"固定资产投资:亿元"}, new String[]{"固定资产投资(亿元)"});

        setOtherValueType(6, "地方政府债务余额(亿元)", new String[]{"地方政府债务余额:亿元"}, new String[]{"地方政府债务余额(亿元)"});
        setOtherValueType(7, "财政自给率(%)", new String[]{"财政自给率:%"}, new String[]{"财政自给率(%)"});
        setOtherValueType(8, "债务率(宽口径)(%)", new String[]{"债务率(宽口径):%"}, new String[]{"债务率(宽口径)(%)"});

        setOtherValueType(9, "一般公共预算收入(亿元)", new String[]{"一般公共预算收入:亿元"}, new String[]{"一般公共预算收入(亿元)"});
        setOtherValueType(10, "政府性基金收入(亿元)", new String[]{"政府性基金收入:亿元"}, new String[]{"政府性基金收入(亿元)"});
        setOtherValueType(11, "一般公共预算支出(亿元)", new String[]{"一般公共预算支出:亿元"}, new String[]{"一般公共预算支出(亿元)"});

    }

    /**
     * @param paType
     * @param name
     * @param yLabels
     * @param lineRowKeys
     */
    private void setOtherValueType(int paType, String name, String[] yLabels, String[] lineRowKeys) {
        if (Integer.valueOf(paType).equals(this.type)) {
            this.name = name;
            this.yLabels = yLabels;
            this.lineRowKeys = lineRowKeys;
        }
    }
}