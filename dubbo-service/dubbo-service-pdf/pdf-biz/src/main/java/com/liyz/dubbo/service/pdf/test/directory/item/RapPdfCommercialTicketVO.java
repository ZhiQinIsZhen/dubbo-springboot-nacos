package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 商票情况
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/30 17:37
 */
@Data
public class RapPdfCommercialTicketVO implements Serializable {

    private String companyName;

    private List<String> labels;


    /**
     * 商票情况-市场交易年化利率
     */
    private Overview overView;

    /**
     * 兑付统计
     */
    private List<SaProjectGroupTable<RaProjectCommercialTicketResponseVO>> censusList;

    @Data
    public static class Overview implements Serializable {
        /**
         * 市场交易年利率low
         */
        private String interestRateLow;

        /**
         * 市场交易年利率high
         */
        private String interestRateHigh;

        /**
         * 风险等级
         */
        private String riskLevel;
        /**
         * 商票违约情况（富文本）
         */
        private String richText;

        public Overview put(RaProjectCommercialTicketOverviewResponseVO in) {
            if (in == null) {
                return this;
            }
            this.interestRateLow = Optional.ofNullable(in.getInterestRateLow())
                    .map(BigDecimal::stripTrailingZeros)
                    .map(BigDecimal::toPlainString)
                    .orElse(null);
            this.interestRateHigh = Optional.ofNullable(in.getInterestRateHigh())
                    .map(BigDecimal::stripTrailingZeros)
                    .map(BigDecimal::toPlainString)
                    .orElse(null);
            this.riskLevel = in.getRiskLevel();
            this.richText = in.getBillDiscountInfo();
            return this;
        }
    }
}
