package com.liyz.dubbo.service.search.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.search.bo.JudgementResultBO;
import com.liyz.dubbo.service.search.bo.JudgementResultPageQueryBO;
import com.liyz.dubbo.service.search.model.JudgementResultDO;
import com.liyz.dubbo.service.search.remote.RemoteJudgementResultService;
import com.liyz.dubbo.service.search.service.JudgementResultService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 注释:法诉
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/16 15:28
 */
@DubboService(version = "1.0.0")
public class RemoteJudgementResultServiceImpl implements RemoteJudgementResultService {

    @Autowired
    JudgementResultService judgementResultService;

    @Override
    public int save(JudgementResultBO judgementResultBO) {
        return judgementResultService.save(CommonConverterUtil.beanCopy(judgementResultBO, JudgementResultDO.class));
    }

    @Override
    public int save(List<JudgementResultBO> list) {
        return judgementResultService.save(CommonConverterUtil.ListTransform(list, JudgementResultDO.class));
    }

    @Override
    public void delete(String id) {
        judgementResultService.delete(id);
    }

    @Override
    public void delete(List<String> ids) {
        judgementResultService.delete(ids);
    }

    @Override
    public Page<JudgementResultBO> search(PageBaseBO pageBaseBO) {
        Page<JudgementResultDO> doPage = judgementResultService.search(pageBaseBO);
        return CommonConverterUtil.PageTransform(doPage, JudgementResultBO.class);
    }

    @Override
    public Page<JudgementResultBO> search(JudgementResultPageQueryBO queryBO) {
        Page<JudgementResultDO> doPage = judgementResultService.search(queryBO);
        return CommonConverterUtil.PageTransform(doPage, JudgementResultBO.class);
    }
}
