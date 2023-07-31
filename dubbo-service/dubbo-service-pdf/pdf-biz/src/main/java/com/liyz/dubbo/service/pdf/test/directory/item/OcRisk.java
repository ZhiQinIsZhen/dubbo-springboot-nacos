package com.liyz.dubbo.service.pdf.test.directory.item;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/25 15:06
 */

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 经营风险
 */
@Deprecated
@Data
public class OcRisk implements Serializable {
    /**
     * 欠税公告
     */
    private List<SaProjectGroupTable<RaProjectMrOwnTaxResponseVO>> mrOwnTaxes;

    /**
     * 税收违法
     */
    private List<SaProjectGroupTable<RaProjectTaxContraventionResponseVO>> taxContraventions;

    /**
     * 简易注销
     */
    private List<SaProjectGroupTable<RaProjectBriefCancelResponseVO>> briefCancels;

    /**
     * 司法拍卖
     */
    private List<SaProjectGroupTable<RaProjectJudicialSaleResponseVO>> judicialSales;

    /**
     * 动产抵押
     */
    private List<SaProjectGroupTable<RaProjectMortgageResponseVO>> mortgages;

    /**
     * 土地抵押
     */
    private List<SaProjectGroupTable<RaProjectLandMortgageResponseVO>> landMortgages;

    /**
     * 股权出资
     */
    private List<SaProjectGroupTable<RaProjectEquityResponseVO>> equities;

    /**
     * 股东质押明细
     */
    private List<SaProjectGroupTable<RaProjectHolderPledgeResponseVO>> holderPledges;

    public void put(RaProjectCompanyOperationInfoResponseVO in) {
        if (in == null) {
            return;
        }
        BeanUtils.copyProperties(in, this);

    }
}
