package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/9 10:42
 */

@Deprecated
@Slf4j
@Data
public class GovGrantsTableVO extends TableVO<RaProjectBigDataGovGrantsResponseVO> implements Serializable {

    private RaProjectGovGrantsResponseVO govGrants;

    public void put(RaProjectGovGrantsResponseVO in) {

        if (in == null) {
            return;
        }
        this.govGrants = in;

        initTableVO();
    }

    /**
     *
     */
    private void initTableVO() {
        super.setDataList(govGrants.getItems());
    }

}

