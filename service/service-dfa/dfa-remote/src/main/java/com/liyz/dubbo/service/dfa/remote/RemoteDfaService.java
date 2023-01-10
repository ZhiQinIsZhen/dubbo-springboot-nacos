package com.liyz.dubbo.service.dfa.remote;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 10:56
 */
public interface RemoteDfaService {

    /**
     * 敏感词脱敏
     *
     * @param word
     * @return
     */
    String sensitiveWord(String word);
}
