package com.liyz.dubbo.common.desensitize.service.impl;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.desensitize.service.DesensitizeService;
import com.liyz.dubbo.common.desensitize.util.AESEncryptDecryptUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 注释:加解密脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 13:29
 */
public class EncryptDecryptDesensitizeServiceImpl implements DesensitizeService {

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (Objects.isNull(annotation)) {
                value = AESEncryptDecryptUtil.encrypt(value);
            } else {
                value = AESEncryptDecryptUtil.decrypt(value);
            }
        }
        return value;
    }

    @Override
    public DesensitizationType getType() {
        return DesensitizationType.ENCRYPT_DECRYPT;
    }
}
