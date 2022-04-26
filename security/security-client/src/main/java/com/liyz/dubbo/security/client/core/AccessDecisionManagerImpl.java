package com.liyz.dubbo.security.client.core;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;

import java.util.List;

/**
 * 注释:一票通过类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/26 10:42
 */
public class AccessDecisionManagerImpl extends AffirmativeBased {

    public AccessDecisionManagerImpl(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }
}
