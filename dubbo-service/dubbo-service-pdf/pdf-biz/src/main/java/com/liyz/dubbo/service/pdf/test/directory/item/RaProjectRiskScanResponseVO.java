package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 14:17
 */
@Getter
@Setter
public class RaProjectRiskScanResponseVO implements Serializable  {


    @ApiModelProperty("合计")
    private RaProjectRiskSummaryResponseVO summary;

    @ApiModelProperty("明细")
    private List<RaProjectRiskDetailResponseVO> details;

    @Data
    public static class RaProjectRiskSummaryResponseVO  implements Serializable{
        /**
         * 风险类型:BUSINESS_RISK经营风险;TICKET_RISK;商票风险BOND_RISK;债券风险LEGAL_RISK;司法风险;FEELINGS_RISK舆情风险;
         */
        @ApiModelProperty("风险类型:BUSINESS_RISK经营风险;TICKET_RISK;商票风险BOND_RISK;债券风险LEGAL_RISK;司法风险;FEELINGS_RISK舆情风险;")
        private String riskType;

        @ApiModelProperty("风险类型:BUSINESS_RISK经营风险;TICKET_RISK;商票风险BOND_RISK;债券风险LEGAL_RISK;司法风险;FEELINGS_RISK舆情风险;")
        private String riskTypeName;

        /**
         * 风险数量
         */
        @ApiModelProperty("风险数量")
        private Integer riskQuan;
    }

    @Data
    public static class RaProjectRiskDetailResponseVO  implements Serializable{

        /**
         * 风险类型:BUSINESS_RISK经营风险;TICKET_RISK;商票风险BOND_RISK;债券风险LEGAL_RISK;司法风险;FEELINGS_RISK舆情风险;
         */
        @ApiModelProperty("风险类型:BUSINESS_RISK经营风险;TICKET_RISK;商票风险BOND_RISK;债券风险LEGAL_RISK;司法风险;FEELINGS_RISK舆情风险;")
        private String riskType;

        @ApiModelProperty("风险类型:BUSINESS_RISK经营风险;TICKET_RISK;商票风险BOND_RISK;债券风险LEGAL_RISK;司法风险;FEELINGS_RISK舆情风险;")
        private String riskTypeName;
        /**
         * 风险类型_指标:REPAYMENT偿债能力;GROWTH成长能力;RED_LINE红线;RISK_LEVEL风险等级;BOND_BREAK商票违约;COUPON_RATE票面利率;DEBENTURE_BREAK债券违约;BE_EXECUTED被执行;BREAK_FAITH失信;LIMITED_CONSUMPTION限制消费;NEGATIVE_FEELINGS负面舆情;
         */
        @ApiModelProperty("风险类型_指标:REPAYMENT偿债能力;GROWTH成长能力;RED_LINE红线;RISK_LEVEL风险等级;BOND_BREAK商票违约;COUPON_RATE票面利率;DEBENTURE_BREAK债券违约;BE_EXECUTED被执行;BREAK_FAITH失信;LIMITED_CONSUMPTION限制消费;NEGATIVE_FEELINGS负面舆情;")
        private String riskTypeTarget;

        @ApiModelProperty("风险类型_指标:REPAYMENT偿债能力;GROWTH成长能力;RED_LINE红线;RISK_LEVEL风险等级;BOND_BREAK商票违约;COUPON_RATE票面利率;DEBENTURE_BREAK债券违约;BE_EXECUTED被执行;BREAK_FAITH失信;LIMITED_CONSUMPTION限制消费;NEGATIVE_FEELINGS负面舆情;")
        private String riskTypeTargetName;
        /**
         * 风险数量
         */
        @ApiModelProperty("风险数量")
        private Integer riskQuan;
    }
}
