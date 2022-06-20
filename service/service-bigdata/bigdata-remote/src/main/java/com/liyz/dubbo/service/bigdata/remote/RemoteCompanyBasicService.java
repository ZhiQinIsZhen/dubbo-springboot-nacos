package com.liyz.dubbo.service.bigdata.remote;

import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.service.bigdata.bo.CompanyBasicBO;
import com.liyz.dubbo.service.bigdata.bo.PageBaseBO;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/20 10:48
 */
public interface RemoteCompanyBasicService {

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     *
     * @param pageBaseBO
     * @return
     */
    Page<CompanyBasicBO> page(PageBaseBO pageBaseBO);
}
