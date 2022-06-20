package com.liyz.dubbo.service.bigdata.provider;

import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.service.bigdata.bo.CompanyBasicBO;
import com.liyz.dubbo.service.bigdata.bo.PageBaseBO;
import com.liyz.dubbo.service.bigdata.remote.RemoteCompanyBasicService;
import com.liyz.dubbo.service.bigdata.service.ICompanyBasicService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/20 10:51
 */
@DubboService
public class RemoteCompanyBasicServiceImpl implements RemoteCompanyBasicService {

    @Resource
    private ICompanyBasicService companyBasicService;

    @Override
    public void deleteById(String id) {
        companyBasicService.delete(id);
    }

    @Override
    public Page<CompanyBasicBO> page(PageBaseBO pageBaseBO) {
        return CommonCloneUtil.pageToPage(companyBasicService.page(pageBaseBO), CompanyBasicBO.class);
    }
}
