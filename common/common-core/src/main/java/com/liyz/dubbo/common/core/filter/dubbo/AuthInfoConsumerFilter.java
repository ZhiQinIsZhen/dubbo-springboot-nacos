package com.liyz.dubbo.common.core.filter.dubbo;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.constant.CommonConstant;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.core.util.JsonMapperUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.Objects;

/**
 * 注释:认证信息过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 21:07
 */
@Activate(group = {CommonConstants.CONSUMER})
public class AuthInfoConsumerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        AuthUser authUser = AuthContext.getAuthUser();
        if (Objects.nonNull(authUser)) {
            RpcContext.getContext().setAttachment(CommonConstant.AUTH_INFO, JsonMapperUtil.toJSONString(authUser));
        }
        Result result = invoker.invoke(invocation);
        return result;
    }
}
