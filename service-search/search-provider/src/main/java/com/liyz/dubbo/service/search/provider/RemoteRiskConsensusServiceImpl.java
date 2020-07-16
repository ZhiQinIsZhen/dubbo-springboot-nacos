package com.liyz.dubbo.service.search.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.base.util.PageImplUtil;
import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.search.bo.RiskConsensusBO;
import com.liyz.dubbo.service.search.bo.RiskConsensusPageQueryBO;
import com.liyz.dubbo.service.search.model.RiskConsensusDO;
import com.liyz.dubbo.service.search.remote.RemoteRiskConsensusService;
import com.liyz.dubbo.service.search.service.RiskConsensusService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Map;

/**
 * 注释:舆情
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/16 15:27
 */
@DubboService(version = "1.0.0")
public class RemoteRiskConsensusServiceImpl implements RemoteRiskConsensusService {

    @Autowired
    RiskConsensusService riskConsensusService;

    @Override
    public int save(RiskConsensusBO riskConsensusBO) {
        return riskConsensusService.save(CommonConverterUtil.beanCopy(riskConsensusBO, RiskConsensusDO.class));
    }

    @Override
    public int save(List<RiskConsensusBO> list) {
        return riskConsensusService.save(CommonConverterUtil.ListTransform(list, RiskConsensusDO.class));
    }

    @Override
    public void delete(Long id) {
        riskConsensusService.delete(id);
    }

    @Override
    public void delete(List<Long> ids) {
        riskConsensusService.delete(ids);
    }

    @Override
    public PageImpl<RiskConsensusBO> search(PageBaseBO pageBaseBO) {
        Page<RiskConsensusDO> doPage = riskConsensusService.search(pageBaseBO);
        PageImpl<RiskConsensusDO> implDoPage = PageImplUtil.toPageImpl(doPage);
        return CommonConverterUtil.PageTransform(implDoPage, RiskConsensusBO.class);
    }

    @Override
    public PageImpl<RiskConsensusBO> search(RiskConsensusPageQueryBO queryBO) {
        Page<RiskConsensusDO> doPage = riskConsensusService.search(queryBO);
        PageImpl<RiskConsensusDO> implDoPage = PageImplUtil.toPageImpl(doPage);
        return CommonConverterUtil.PageTransform(implDoPage, RiskConsensusBO.class);
    }

    @Override
    public PageImpl<RiskConsensusBO> searchForHighlight(RiskConsensusPageQueryBO queryBO) {
        Page<RiskConsensusDO> doPage = riskConsensusService.searchForHighlight(queryBO);
        PageImpl<RiskConsensusDO> implDoPage = PageImplUtil.toPageImpl(doPage);
        return CommonConverterUtil.PageTransform(implDoPage, RiskConsensusBO.class);
    }

    @Override
    public Map<String, Object> aggregateForSentimentType(RiskConsensusPageQueryBO queryBO) {
        return riskConsensusService.aggregateForSentimentType(queryBO);
    }
}
