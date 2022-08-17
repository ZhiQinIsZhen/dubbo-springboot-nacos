package com.liyz.dubbo.security.server.provider;

import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.security.encrypt.remote.RemoteUserAlgorithmService;
import com.liyz.dubbo.security.encrypt.vo.UserAlgorithmVO;
import com.liyz.dubbo.security.server.model.UserAlgorithmDO;
import com.liyz.dubbo.security.server.service.IUserAlgorithmService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/17 11:22
 */
@DubboService
public class RemoteUserAlgorithmServiceImpl implements RemoteUserAlgorithmService {

    @Resource
    private IUserAlgorithmService userAlgorithmService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(UserAlgorithmVO userAlgorithmVO) {
        UserAlgorithmDO userAlgorithmDO = CommonCloneUtil.objectClone(userAlgorithmVO, UserAlgorithmDO.class);
        userAlgorithmService.save(userAlgorithmDO);
        return Boolean.TRUE;
    }
}
