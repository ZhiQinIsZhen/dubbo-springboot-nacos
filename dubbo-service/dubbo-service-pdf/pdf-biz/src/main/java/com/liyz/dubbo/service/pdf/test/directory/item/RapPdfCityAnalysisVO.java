package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/7 10:38
 */
@Slf4j
@Data
public class RapPdfCityAnalysisVO implements Serializable {

    /**
     * 区域信息
     */
    private RaProjectRegionStructure regionStructure;


    /**
     * "政府补助"
     */
    private GovGrantsTableVO tableGovGrants;

    /**
     * "区域利差"
     */
    private InterestMarginsTableVO tableTnterestMargins;


    /**
     * "区域久期"
     */
    private DurationsTableVO tableDurations;
}
