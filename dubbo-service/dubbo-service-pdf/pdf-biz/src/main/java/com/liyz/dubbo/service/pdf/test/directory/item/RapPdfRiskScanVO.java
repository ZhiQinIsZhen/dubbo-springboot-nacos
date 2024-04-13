package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/29 16:43
 */
@Data
public class RapPdfRiskScanVO extends RaProjectRiskScanObjectResponseVO implements Serializable {


    public void putRiskScan(RaProjectRiskScanObjectResponseVO in) {

        if (in == null) {
            return;
        }
        BeanUtils.copyProperties(in, this);
    }

    /**
     * @param riskType
     * @return
     */
    public RaProjectRiskScanResponseVO withTypeRisk(String riskType) {

        List<RaProjectRiskScanResponseVO> ls = this.getRiskScans();
        if (CollectionUtils.isEmpty(ls)) {
            return null;
        }

        RaProjectRiskScanResponseVO responseVo = ls.stream().filter(p -> riskType.equals(p.getSummary().getRiskType())).findFirst().orElse(null);
        return responseVo;
    }
}
