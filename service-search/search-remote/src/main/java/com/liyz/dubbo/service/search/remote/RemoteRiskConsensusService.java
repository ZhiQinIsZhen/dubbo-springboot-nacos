package com.liyz.dubbo.service.search.remote;

import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.search.bo.RiskConsensusBO;
import com.liyz.dubbo.service.search.bo.RiskConsensusPageQueryBO;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Map;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/16 15:18
 */
public interface RemoteRiskConsensusService {

    int save(RiskConsensusBO riskConsensusBO);

    int save(List<RiskConsensusBO> list);

    void delete(Long id);

    void delete(List<Long> ids);

    PageImpl<RiskConsensusBO> search(PageBaseBO pageBaseBO);

    PageImpl<RiskConsensusBO> search(RiskConsensusPageQueryBO queryBO);

    PageImpl<RiskConsensusBO> searchForHighlight(RiskConsensusPageQueryBO queryBO);

    Map<String,Object> aggregateForSentimentType(RiskConsensusPageQueryBO queryBO);
}
