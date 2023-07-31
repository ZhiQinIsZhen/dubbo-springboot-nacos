package com.liyz.dubbo.service.pdf.test.directory.item;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/25 15:20
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 财务简析
 */
@Slf4j
@Data
public class ThreeRedLines implements Serializable {


    /**
     * 三道红线情况
     */
    /**
     * 剔除预收款后的资产负债率
     */
    private BigDecimal ebtRatioAfterEliminateDepositReceived;
    private String ebtRatioAfterEliminateDepositReceivedLevel;
    /**
     * 净负债率
     */
    private BigDecimal netDebtRatio;
    private String netDebtRatioLevel;

    /**
     * 现金短债比
     */
    private BigDecimal cashFlowsCoverageRatio;
    private String cashFlowsCoverageRatioLevel;

    /**
     * 档位(1:红档/2:绿档/3:黄档/4:橙档)
     */
    private String gear;
    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deadline;
    /**
     * 关键财务指标
     */
    /**
     * 财务情况说明
     */
    private String financialInfo;

    /**
     * 各种能力指标
     */
    private IndicatorStructureTableVO indicator;

    /**
     * 默认数据
     */
    public ThreeRedLines() {
        ebtRatioAfterEliminateDepositReceivedLevel = erLevel(null);
        netDebtRatioLevel = netDebtRatioLevel(null);
        cashFlowsCoverageRatioLevel = cashFlowsCoverageRatioLevel(null);
    }

    private String erLevel(BigDecimal num) {
        if (num == null) {
            return "empty";
        } else {
            BigDecimal qualified = new BigDecimal("70");
            int compare = num.compareTo(qualified);
            return compare == 0 ? "zero" : (compare > 0 ? "bad3" : "good3");
        }
    }

    private String netDebtRatioLevel(BigDecimal num) {
        if (num == null) {
            return "empty";
        } else {
            BigDecimal qualified = new BigDecimal("100");
            int compare = num.compareTo(qualified);
            return compare == 0 ? "zero" : (compare > 0 ? "bad3" : "good3");
        }
    }

    private String cashFlowsCoverageRatioLevel(BigDecimal num) {
        if (num == null) {
            return "empty";
        } else {
            int compare = num.compareTo(BigDecimal.ONE);
            return compare == 0 ? "zero" : (compare > 0 ? "good3" : "bad3");
        }
    }

    public void putIndicator(IndicatorStructureResponseVO indicator) {
        if (Objects.isNull(indicator)) {
            return;
        }
        IndicatorStructureTableVO indicatorStructureTableVO = new IndicatorStructureTableVO();
        indicatorStructureTableVO.putTable(indicator);
        this.indicator = indicatorStructureTableVO;
    }
}