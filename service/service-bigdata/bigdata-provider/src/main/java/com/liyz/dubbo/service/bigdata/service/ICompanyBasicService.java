package com.liyz.dubbo.service.bigdata.service;

import com.liyz.dubbo.service.bigdata.bo.PageBaseBO;
import com.liyz.dubbo.service.bigdata.model.CompanyBasicDO;
import org.springframework.data.domain.Page;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/17 15:57
 */
public interface ICompanyBasicService {

    /**
     * 分页查询
     *
     * @param pageBaseBO
     * @return
     */
    Page<CompanyBasicDO> page(PageBaseBO pageBaseBO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id);
}
