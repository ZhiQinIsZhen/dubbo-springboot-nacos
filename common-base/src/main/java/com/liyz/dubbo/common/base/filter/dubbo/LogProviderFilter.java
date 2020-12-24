package com.liyz.dubbo.common.base.filter.dubbo;

import com.liyz.dubbo.common.base.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * 注释:日志调用过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/9/28 16:20
 */
@Slf4j
@Activate(group = {CommonConstants.PROVIDER})
public class LogProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String logId = RpcContext.getContext().getAttachment(CommonConstant.DUBBO_LOG_ID);
        Result result = invoker.invoke(invocation);
        if (StringUtils.isNotBlank(logId)) {
            result.getAttachments().put(CommonConstant.DUBBO_LOG_ID, logId);
        }
        return result;
    }
}