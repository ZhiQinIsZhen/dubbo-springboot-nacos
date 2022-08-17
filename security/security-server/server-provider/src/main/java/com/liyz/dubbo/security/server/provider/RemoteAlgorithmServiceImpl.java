package com.liyz.dubbo.security.server.provider;

import com.google.common.collect.Lists;
import com.liyz.dubbo.security.encrypt.enums.Algorithm;
import com.liyz.dubbo.security.encrypt.remote.RemoteAlgorithmService;
import com.liyz.dubbo.security.server.model.UserAlgorithmDO;
import com.liyz.dubbo.security.server.service.IUserAlgorithmService;
import com.liyz.dubbo.security.server.service.abs.AbstractAlgorithmService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 16:27
 */
@DubboService
public class RemoteAlgorithmServiceImpl implements RemoteAlgorithmService {

    @Resource
    private IUserAlgorithmService userAlgorithmService;

    @Override
    public String encrypt(String content, Long userId) {
        UserAlgorithmDO userAlgorithmDO = userAlgorithmService.getByUserId(userId);
        if (Objects.isNull(userAlgorithmDO)) {
            return content;
        }
        Algorithm algorithm = Algorithm.getByCode(userAlgorithmDO.getAlgorithm());
        if (Objects.isNull(algorithm)) {
            return content;
        }
        List<String> keyList = userAlgorithmDO.getType() == 0
                ? Lists.newArrayList(userAlgorithmDO.getAlgorithmKey(), userAlgorithmDO.getIv())
                : Lists.newArrayList(userAlgorithmDO.getPublicKey());
        String[] keys = new String[keyList.size()];
        keys = keyList.toArray(keys);
        return AbstractAlgorithmService.getByAlgorithm(algorithm).encrypt(content, keys);
    }

    @Override
    public String decrypt(String content, Long userId) {
        UserAlgorithmDO userAlgorithmDO = userAlgorithmService.getByUserId(userId);
        if (Objects.isNull(userAlgorithmDO)) {
            return content;
        }
        Algorithm algorithm = Algorithm.getByCode(userAlgorithmDO.getAlgorithm());
        if (Objects.isNull(algorithm)) {
            return content;
        }
        List<String> keyList = userAlgorithmDO.getType() == 0
                ? Lists.newArrayList(userAlgorithmDO.getAlgorithmKey(), userAlgorithmDO.getIv())
                : Lists.newArrayList(userAlgorithmDO.getPrivateKey());
        String[] keys = new String[keyList.size()];
        keys = keyList.toArray(keys);
        return AbstractAlgorithmService.getByAlgorithm(algorithm).decrypt(content, keys);
    }

    @Override
    public List<String> createKeys(int keySize) {
        return AbstractAlgorithmService.getByAlgorithm(Algorithm.RSA).createKeys(keySize);
    }
}
