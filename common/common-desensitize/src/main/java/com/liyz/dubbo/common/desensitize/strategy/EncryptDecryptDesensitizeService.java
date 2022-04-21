package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.springframework.stereotype.Component;

/**
 * 注释:加解密脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 13:29
 */
@Component
public class EncryptDecryptDesensitizeService extends AbstractDesensitizeService{

    @Override
    public String desensitize(String value, Desensitization annotation) {
        return null;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.ENCRYPT_DECRYPT;
    }
}
