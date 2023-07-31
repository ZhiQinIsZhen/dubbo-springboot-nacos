package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 法律风险
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/30 17:38
 */
@Data
public class RapPdfLegalRiskVO implements Serializable {
    /**
     * 法律诉讼列表
     */
    private List<SaProjectGroupTable<LawSuitResponseVO>> lawSuitList;

    /**
     * 开庭公告列表
     */
    private List<SaProjectGroupTable<KtannouncementResponseVO>> ktannouncementList;

    /**
     * 被执行人列表
     */
    private List<SaProjectGroupTable<ZhixinginfoResponseVO>> zhixinginfoList;

    /**
     * 失信人列表
     */
    private List<SaProjectGroupTable<DishonestResponseVO>> dishonestList;

    /**
     * 限制消费
     */
    private List<SaProjectGroupTable<ConsumptionRestrictionResponseVO>> consumptionRestrictionList;

    /**
     * 司法记录（老流程数据）
     */
    private RaProjectJudicialRecordResponseVO judicialRecord;


}
