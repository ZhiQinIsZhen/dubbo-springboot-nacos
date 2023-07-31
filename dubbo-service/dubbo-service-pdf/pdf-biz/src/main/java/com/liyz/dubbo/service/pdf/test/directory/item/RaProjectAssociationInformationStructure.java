package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/3 11:10
 */
@Data
@NoArgsConstructor
public class RaProjectAssociationInformationStructure implements Serializable {
    /**
     * 最终受益人
     */
    private FinalBeneficiary.FinalBeneficiaryPerson finalBeneficiaryPerson;


    /**
     * 关联公司信息
     */
    private List<RaProjectCorporationBaseResponseVO> relationCompanyList;


    public RaProjectAssociationInformationStructure(RaProjectHumanHoldingResponseVO responseVO, List<RaProjectCorporationBaseResponseVO> relationCompanyList) {

        if (null == responseVO && CollectionUtils.isEmpty(relationCompanyList)) {
            return;
        }
        if (responseVO != null) {
            FinalBeneficiary.FinalBeneficiaryPerson finalBeneficiaryPerson = new FinalBeneficiary.FinalBeneficiaryPerson();
            finalBeneficiaryPerson.setDataByRaProjectHumanHoldingResponse(responseVO);
            this.finalBeneficiaryPerson = finalBeneficiaryPerson;
        }
        if (!CollectionUtils.isEmpty(relationCompanyList)) {
            this.relationCompanyList = relationCompanyList;
        }
    }
}
