package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/9 11:01
 */
@Deprecated
@Slf4j
@Data
public class DurationsTableVO extends TableVO<RaProjectRegionDurationResponseVO> implements Serializable {

    /**
     * 公司授信额度
     */
    private List<RaProjectRegionDurationResponseVO> ls;


    public void put(List<RaProjectRegionDurationResponseVO> ls) {
        if (!CollectionUtils.isEmpty(ls)) {
            this.ls = ls;
            super.setDataList(ls);
        }
    }
}
