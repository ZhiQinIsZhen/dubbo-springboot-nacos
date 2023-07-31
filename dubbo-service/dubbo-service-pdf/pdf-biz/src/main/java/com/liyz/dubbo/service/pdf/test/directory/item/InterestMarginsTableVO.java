package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/9 11:00
 */
@Deprecated
@Slf4j
@Data
public class InterestMarginsTableVO extends TableVO<RaProjectRegionInterestMarginResponseVO> implements Serializable {

    /**
     * 公司授信额度
     */
    private List<RaProjectRegionInterestMarginResponseVO> ls;


    public void put(List<RaProjectRegionInterestMarginResponseVO> ls) {
        if (!CollectionUtils.isEmpty(ls)) {
            this.ls = ls;
            super.setDataList(ls);
        }
    }
}
