package com.liyz.dubbo.service.dfa.core;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 11:11
 */
public interface DfaAlgorithmService {

    /**
     * dfa算法过滤
     *
     * @param sourceWord
     * @return
     */
    String dfa(String sourceWord);
}
