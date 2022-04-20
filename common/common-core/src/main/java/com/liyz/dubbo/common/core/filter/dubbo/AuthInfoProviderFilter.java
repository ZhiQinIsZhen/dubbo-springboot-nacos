package com.liyz.dubbo.common.core.filter.dubbo;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.constant.CommonConstant;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.core.util.JsonMapperUtil;
import org.apache.dubbo.rpc.*;

import java.util.Objects;

/**
 * 注释:认证信息过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 21:12
 */
public class AuthInfoProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean needRemove = false;
        AuthUser authUser = AuthContext.getAuthUser();
        if (Objects.isNull(authUser) && RpcContext.getContext().getAttachments().containsKey(CommonConstant.AUTH_INFO)) {
            authUser = JsonMapperUtil.readValue(RpcContext.getContext().getAttachment(CommonConstant.AUTH_INFO), AuthUser.class);
            if (Objects.nonNull(authUser)) {
                AuthContext.setAuthUser(authUser);
                needRemove = true;
            }
        }
        Result result = invoker.invoke(invocation);
        if (needRemove) {
            AuthContext.remove();;
        }
        return result;
    }
}
