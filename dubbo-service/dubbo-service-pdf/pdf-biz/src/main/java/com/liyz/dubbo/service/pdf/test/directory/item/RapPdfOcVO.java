package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 经营情况
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/30 17:36
 */
@Slf4j
@Data
public class RapPdfOcVO implements Serializable {


    /**
     * 经营情况包含3大块，财务简析+财务数据+经营风险
     */
    private String companyName;

    private List<String> labels;

    /**
     * 三道红线
     */
    private ThreeRedLines threeRedLines;

    /**
     * 财务数据
     */
    private FinanceDataStructure financeData;
    /**
     * 经营风险
     */
    private OcRisk ocRisk;


    /**
     * 授信及担保
     */
    private CreditGuarantee creditGuarantee;

    /**
     * 土储分析
     */
    private SoilStorageAnalysisStructure soilStorageAnalysis;

}
