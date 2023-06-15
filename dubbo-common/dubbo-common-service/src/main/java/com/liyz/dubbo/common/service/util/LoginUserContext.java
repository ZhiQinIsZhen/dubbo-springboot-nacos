package com.liyz.dubbo.common.service.util;

import cn.hutool.core.util.NumberUtil;
import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.rpc.RpcContext;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/13 19:57
 */
@UtilityClass
public class LoginUserContext {

    private static final InheritableThreadLocal<Long> innerContext = new InheritableThreadLocal<>();

    public static Long getLoginId() {
        String LoginId = RpcContext.getServerAttachment().getAttachment(CommonServiceConstant.ATTACHMENT_LOGIN_USER);
        if (StringUtils.isBlank(LoginId) || !NumberUtil.isLong(LoginId)) {
            return CommonServiceConstant.DEFAULT_SYSTEM_USER_ID;
        }
        return Long.valueOf(LoginId);
    }
}
