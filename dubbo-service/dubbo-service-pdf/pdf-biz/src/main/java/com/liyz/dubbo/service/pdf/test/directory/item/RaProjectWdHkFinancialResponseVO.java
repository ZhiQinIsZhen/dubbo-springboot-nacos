package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/22 17:35
 */
@Getter
@Setter
public class RaProjectWdHkFinancialResponseVO implements Serializable {
    private static final long serialVersionUID = 99535427287988185L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;

    /**
     * 现金及现金等价物
     */
    @ApiModelProperty("现金及现金等价物")
    private String wgsdCce;

    /**
     * 交易性金融资产
     */
    @ApiModelProperty("交易性金融资产")
    private String wgsdInvestTrading;

    /**
     * 其他短期投资
     */
    @ApiModelProperty("其他短期投资")
    private String wgsdInvestStOth;

    /**
     * 应收款项合计
     */
    @ApiModelProperty("应收款项合计")
    private String wgsdReceivTot;

    /**
     * 应收账款及票据
     */
    @ApiModelProperty("应收账款及票据")
    private String wgsdReceivNet;

    /**
     * 其他应收款
     */
    @ApiModelProperty("其他应收款")
    private String wgsdReceivStOth;

    /**
     * 存货
     */
    @ApiModelProperty("存货")
    private String wgsdInventories;

    /**
     * 其他流动资产
     */
    @ApiModelProperty("其他流动资产")
    private String wgsdAssetsCurrOth;

    /**
     * 流动资产合计
     */
    @ApiModelProperty("流动资产合计")
    private String wgsdAssetsCurr;

    /**
     * 固定资产净值
     */
    @ApiModelProperty("固定资产净值")
    private String wgsdPpeNet;

    /**
     * 权益性投资
     */
    @ApiModelProperty("权益性投资")
    private String wgsdInvestEq;

    /**
     * 持有至到期投资
     */
    @ApiModelProperty("持有至到期投资")
    private String wgsdInvestHtm;

    /**
     * 可供出售投资
     */
    @ApiModelProperty("可供出售投资")
    private String wgsdInvestAfs;

    /**
     * 其他长期投资
     */
    @ApiModelProperty("其他长期投资")
    private String wgsdInvestLtOth;

    /**
     * 商誉及无形资产
     */
    @ApiModelProperty("商誉及无形资产")
    private String wgsdGwIntang;

    /**
     * 土地使用权
     */
    @ApiModelProperty("土地使用权")
    private String wgsdLandUseRights;

    /**
     * 其他非流动资产
     */
    @ApiModelProperty("其他非流动资产")
    private String wgsdAssetsLtOth;

    /**
     * 非流动资产合计
     */
    @ApiModelProperty("非流动资产合计")
    private String wgsdAssetsLt;

    /**
     * 总资产
     */
    @ApiModelProperty("总资产")
    private String wgsdAssets;

    /**
     * 应付账款及票据
     */
    @ApiModelProperty("应付账款及票据")
    private String wgsdPayAcct;

    /**
     * 应交税金
     */
    @ApiModelProperty("应交税金")
    private String wgsdPayTax;

    /**
     * 交易性金融负债
     */
    @ApiModelProperty("交易性金融负债")
    private String wgsdLiabsTrading;

    /**
     * 短期借贷及长期借贷当期到期部分
     */
    @ApiModelProperty("短期借贷及长期借贷当期到期部分")
    private String wgsdDebtSt;

    /**
     * 其他流动负债
     */
    @ApiModelProperty("其他流动负债")
    private String wgsdLiabsCurrOth;

    /**
     * 流动负债合计
     */
    @ApiModelProperty("流动负债合计")
    private String wgsdLiabsCurr;

    /**
     * 长期借贷
     */
    @ApiModelProperty("长期借贷")
    private String wgsdDebtLt;

    /**
     * 其他非流动负债
     */
    @ApiModelProperty("其他非流动负债")
    private String wgsdLiabsLtOth;

    /**
     * 非流动负债合计
     */
    @ApiModelProperty("非流动负债合计")
    private String wgsdLiabsLt;

    /**
     * 总负债
     */
    @ApiModelProperty("总负债")
    private String wgsdLiabs;

    /**
     * 优先股
     */
    @ApiModelProperty("优先股")
    private String wgsdPfdStk;

    /**
     * 普通股股本
     */
    @ApiModelProperty("普通股股本")
    private String wgsdComEqPar;

    /**
     * 储备
     */
    @ApiModelProperty("储备")
    private String wgsdRsv;

    /**
     * 库存股
     */
    @ApiModelProperty("库存股")
    private String wgsdTreasStk;

    /**
     * 其他综合性收益
     */
    @ApiModelProperty("其他综合性收益")
    private String wgsdComEqForExch;

    /**
     * 普通股权益总额
     */
    @ApiModelProperty("普通股权益总额")
    private String wgsdComEqPaholder;

    /**
     * 归属母公司股东权益
     */
    @ApiModelProperty("归属母公司股东权益")
    private String wgsdComEq;

    /**
     * 少数股东权益
     */
    @ApiModelProperty("少数股东权益")
    private String wgsdMinInt;

    /**
     * 股东权益合计
     */
    @ApiModelProperty("股东权益合计")
    private String wgsdStkhldrsEq;

    /**
     * 总负债及总权益
     */
    @ApiModelProperty("总负债及总权益")
    private String wgsdLiabsStkhldrsEq;

    @ApiModelProperty("定期报告实际披露日期")
    private String stmIssuingdate;

    @ApiModelProperty("报告起始日期")
    private String stmRptS;

    @ApiModelProperty("报告截止日期")
    private String stmRptE;
}
