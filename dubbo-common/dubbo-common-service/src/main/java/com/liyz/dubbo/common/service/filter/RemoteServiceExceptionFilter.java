package com.liyz.dubbo.common.service.filter;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Desc:Dubbo exception filter
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 17:49
 */
@Slf4j
@Activate(group = {CommonConstants.PROVIDER})
public class RemoteServiceExceptionFilter implements Filter, Filter.Listener {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            Throwable exception = appResponse.getException();
            //业务异常直接返回
            if (exception instanceof RemoteServiceException) {
                return;
            }
            if (!(exception instanceof RuntimeException) && exception instanceof Exception) {
                return;
            }
            try {
                Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                Arrays.stream(method.getExceptionTypes()).forEach(exceptionClass -> {
                    if (exception.getClass().equals(exceptionClass)) {
                        return;
                    }
                });
            } catch (NoSuchMethodException noSuchMethodException) {
                return;
            }

            String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
            String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
            if (Objects.nonNull(serviceFile) && Objects.nonNull(exceptionFile) && !serviceFile.equals(exceptionFile)) {
                String className = exception.getClass().getName();
                if (!className.startsWith(CommonServiceConstant.JDK_CLASS_PREFIX) && !className.startsWith(CommonServiceConstant.JDK_CLASS_X_PREFIX)) {
                    if (exception instanceof RpcException) {
                        return;
                    }
                    appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
                }
            }
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {}
}
