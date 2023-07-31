package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * description: TODO 项目信息
 * author: huanglb
 * date 2022/11/3 10:15
 */
@Data
public class RapPdfProjectInformationVO implements Serializable {

    /**
     * 项目情况
     */
    private RaProjectDetailResponseVO projectDetail;

    /**
     * 周边配套
     */
    private RaProjectSupportResponseVO projectSupport;

    /**
     * 区域信息
     */
    private RaProjectRegionStructure regionStructure;

    /**
     * @param projectDetail
     */
    public void putProjectDetail(RaProjectDetailResponseVO projectDetail) {
        if (Objects.isNull(projectDetail)) {
            return;
        }
        Boolean skip = projectDetail.getEstateSkip();
        if (Boolean.TRUE.equals(skip)) {
            return;
        }
        this.setProjectDetail(projectDetail);
    }

    /**
     * @param projectSupport
     */
    public void putProjectSupport(RaProjectSupportResponseVO projectSupport) {

        if (Objects.isNull(projectSupport)) {
            return;
        }
        Boolean skip = projectSupport.getSupportSkip();
        if (Boolean.TRUE.equals(skip)) {
            return;
        }
        this.setProjectSupport(projectSupport);

    }

}
