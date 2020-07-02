package com.liyz.dubbo.common.base.filter.dubbo;

import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * 注释: dubbo exception filter
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/29 14:09
 */
@Activate(group = {CommonConstants.PROVIDER})
public class RemoteServiceExceptionFilter implements Filter, Filter.Listener {

    private Logger logger = LoggerFactory.getLogger(RemoteServiceExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = appResponse.getException();
                if (!(exception instanceof RuntimeException) && exception instanceof Exception) {
                    return;
                }

                try {
                    Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                    Class<?>[] exceptionClassses = method.getExceptionTypes();
                    Class[] var7 = exceptionClassses;
                    int var8 = exceptionClassses.length;

                    for(int var9 = 0; var9 < var8; ++var9) {
                        Class<?> exceptionClass = var7[var9];
                        if (exception.getClass().equals(exceptionClass)) {
                            return;
                        }
                    }
                } catch (NoSuchMethodException var11) {
                    return;
                }

                /**
                 * dubbo远程调用异常直接返回
                 */
                if (exception instanceof RemoteServiceException) {
                    return;
                }

                this.logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);
                String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                if (serviceFile != null && exceptionFile != null && !serviceFile.equals(exceptionFile)) {
                    String className = exception.getClass().getName();
                    if (!className.startsWith("java.") && !className.startsWith("javax.")) {
                        if (exception instanceof RpcException) {
                            return;
                        }

                        appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
                        return;
                    }

                    return;
                }

                return;
            } catch (Throwable var12) {
                this.logger.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + var12.getClass().getName() + ": " + var12.getMessage(), var12);
            }
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
        this.logger.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
