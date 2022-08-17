package com.liyz.dubbo.security.server.service.abs;

import com.liyz.dubbo.security.encrypt.enums.Algorithm;
import com.liyz.dubbo.security.encrypt.exception.RemoteSecurityServiceException;
import com.liyz.dubbo.security.encrypt.exception.SecurityExceptionCodeEnum;
import com.liyz.dubbo.security.server.service.AlgorithmService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 15:36
 */
public abstract class AbstractAlgorithmService implements AlgorithmService {

    public AbstractAlgorithmService() {
        AlgorithmService service = map.put(getAlgorithm(), this);
        if (Objects.nonNull(service)) {
            throw new RemoteSecurityServiceException(SecurityExceptionCodeEnum.ALGORITHM_SAME_ERROR);
        }
    }

    private static ConcurrentHashMap<Algorithm, AlgorithmService> map = new ConcurrentHashMap<>();

    public static AlgorithmService getByAlgorithm(Algorithm algorithm) {
        return map.get(algorithm);
    }

    @Override
    public String encrypt(String content, String... keys) {
        if (StringUtils.isBlank(content) || arrayIsEmpty(keys)) {
            return content;
        }
        try {
            return doEncrypt(content, keys);
        } catch (Exception e) {
            throw new RemoteSecurityServiceException(SecurityExceptionCodeEnum.ENCRYPT_ERROR);
        }
    }

    @Override
    public String decrypt(String content, String... keys) {
        if (StringUtils.isBlank(content) || arrayIsEmpty(keys)) {
            return content;
        }
        try {
            return doDecrypt(content, keys);
        } catch (Exception e) {
            throw new RemoteSecurityServiceException(SecurityExceptionCodeEnum.DECRYPT_ERROR);
        }
    }

    @Override
    public List<String> createKeys(int keySize) {
        try {
            return doCreateKeys(keySize);
        } catch (Exception e) {
            throw new RemoteSecurityServiceException(SecurityExceptionCodeEnum.CREATE_KEY_ERROR);
        }
    }

    protected abstract List<String> doCreateKeys(int keySize) throws Exception;

    private boolean arrayIsEmpty(String... keys) {
        if (Objects.isNull(keys) || keys.length == 0) {
            return true;
        }
        return false;
    }

    protected abstract Algorithm getAlgorithm();

    protected abstract String doEncrypt(String content, String... keys) throws Exception;

    protected abstract String doDecrypt(String content, String... keys) throws Exception;
}
