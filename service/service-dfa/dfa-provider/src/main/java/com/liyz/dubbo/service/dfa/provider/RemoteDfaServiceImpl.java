package com.liyz.dubbo.service.dfa.provider;

import com.liyz.dubbo.service.dfa.core.DfaAlgorithmService;
import com.liyz.dubbo.service.dfa.remote.RemoteDfaService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 10:58
 */
@DubboService
public class RemoteDfaServiceImpl implements RemoteDfaService {

    @Resource
    private DfaAlgorithmService dfaAlgorithmService;

    @Override
    public String sensitiveWord(String word) {
        return dfaAlgorithmService.dfa(word);
    }
}
