package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * description: TODO 授信及担保
 * author: huanglb
 * date 2022/9/21 13:22
 */
@Slf4j
@Data
public class CreditGuarantee implements Serializable {

    /**
     * 融资及担保信息描述
     */
    private RaProjectFinancingGuaranteeResponseVO financingGuarantee;


    /**
     * 授信
     */
    private CompanyCreditLines companyCreditLines;


    private GuaranteeOverviews guaranteeOverviews;


    /**
     * 银行借款担保
     */
    private List<RaProjectGuaranteeDataResponseVO> bankGuaranteeDatas;

    /**
     * 债券担保
     */
    private List<RaProjectBondGuaranteeDataResponseVO> bondGuaranteeDatas;
    /**
     * 融资租赁担保
     */
    private List<RaProjectGuaranteeDataResponseVO> leaseGuaranteeDatas;
    /**
     * 信托融资担保
     */
    private List<RaProjectGuaranteeDataResponseVO> trustGuaranteeDatas;


    /**
     * @param in
     */
    public void putCreditGuarantee(RaProjectCompanyOperationInfoResponseVO in) {


        if (Objects.isNull(in)) {
            return;
        }
        this.setFinancingGuarantee(in.getFinancingGuarantee());

        CompanyCreditLines companyCreditLines = new CompanyCreditLines();
        companyCreditLines.putCompanyCreditLines(in.getCompanyCreditLines());

        this.setCompanyCreditLines(companyCreditLines);
        GuaranteeOverviews guaranteeOverviews = new GuaranteeOverviews();
        guaranteeOverviews.putGuaranteeOverviews(in.getGuaranteeOverviews());
        this.setGuaranteeOverviews(guaranteeOverviews);

        this.setBankGuaranteeDatas(in.getBankGuaranteeDatas());
        this.setBondGuaranteeDatas(in.getBondGuaranteeDatas());
        this.setLeaseGuaranteeDatas(in.getLeaseGuaranteeDatas());
        this.setTrustGuaranteeDatas(in.getTrustGuaranteeDatas());

    }

    @Data
    public static class CompanyCreditLines extends TableVO<RaProjectCompanyCreditLineResponseVO> implements Serializable {

        /**
         * 公司授信额度
         */
        private List<RaProjectCompanyCreditLineResponseVO> ls;

        /**
         * @param companyCreditLines
         */
        public void putCompanyCreditLines(List<RaProjectCompanyCreditLineResponseVO> companyCreditLines) {
            if (!CollectionUtils.isEmpty(companyCreditLines)) {
                ls = companyCreditLines;
                super.setDataList(companyCreditLines);
            }
        }
    }

    /**
     *
     */
    @Data
    public static class GuaranteeOverviews extends TableVO<RaProjectGuaranteeOverviewResponseVO> implements Serializable {
        /**
         * 对外担保概览
         */
        private List<RaProjectGuaranteeOverviewResponseVO> ls;

        /**
         * @param guaranteeOverviews
         */
        public void putGuaranteeOverviews(List<RaProjectGuaranteeOverviewResponseVO> guaranteeOverviews) {
            if (Objects.nonNull(guaranteeOverviews)) {
                ls = guaranteeOverviews;
                super.setDataList(guaranteeOverviews);
            }
        }
    }
}
