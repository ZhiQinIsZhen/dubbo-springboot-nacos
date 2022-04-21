package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.config.AESEncryptDecryptConfig;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 注释:加解密脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 13:29
 */
@Component
public class EncryptDecryptDesensitizeService extends AbstractDesensitizeService{

    @Autowired
    private AESEncryptDecryptConfig aesEncryptDecryptConfig;

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (Objects.isNull(annotation)) {
                value = aesEncryptDecryptConfig.encrypt(value);
            } else {
                value = aesEncryptDecryptConfig.decrypt(value);
            }
        }
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.ENCRYPT_DECRYPT;
    }
}
