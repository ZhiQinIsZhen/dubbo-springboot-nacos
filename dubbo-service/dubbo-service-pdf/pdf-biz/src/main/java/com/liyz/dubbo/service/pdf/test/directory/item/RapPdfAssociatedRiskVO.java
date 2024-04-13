package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 关联风险
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/30 17:40
 */
@Data
public class RapPdfAssociatedRiskVO implements Serializable {

    /**
     * 关联自然人风险
     */
    private NaturalRisk naturalRisk;


    /**
     * 风险，包含限制消费+失信+被执行人列表
     */
    @Data
    public static class Risk implements Serializable {
        /**
         * 限制消费
         */
        private List<ConsumptionRestrictionResponseVO> consumptionRestrictionList;
        /**
         * 失信
         */
        private List<DishonestResponseVO> dishonestList;
        /**
         * 被执行人列表
         */
        private List<ZhixinginfoResponseVO> zhixinginfoList;

    }

    /**
     * 关联自然人风险
     */
    @Data
    public static class NaturalRisk extends Risk implements Serializable {
//        /**
//         * 股权出质风险
//         */
//        private List<RaProjectEquityResponseVO> equitiesList;
    }

    /**
     * 关联公司风险
     */
    @Data
    public static class CorpRisk extends Risk implements Serializable {

//        /**
//         * 欠税公告
//         */
//        private List<RaProjectMrOwnTaxResponseVO> mrOwnTaxesList;
//
//        /**
//         * 税收违法
//         */
//        private List<RaProjectTaxContraventionResponseVO> taxContraventionsList;
//
//        /**
//         * 司法拍卖
//         */
//        private List<RaProjectJudicialSaleResponseVO> judicialSalesList;
//
//        /**
//         * 动产抵押
//         */
//        private List<RaProjectMortgageResponseVO> mortgagesList;
//
//        /**
//         * 土地抵押
//         */
//        private List<RaProjectLandMortgageResponseVO> landMortgagesList;
//
//        /**
//         * 股权出资
//         */
//        private List<RaProjectEquityResponseVO> equities;
//
//        /**
//         * 关联公司-商票违约情况（富文本）
//         */
//        private String  richText;

    }
}
