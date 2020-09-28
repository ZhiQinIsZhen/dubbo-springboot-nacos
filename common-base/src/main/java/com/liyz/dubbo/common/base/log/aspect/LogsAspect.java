package com.liyz.dubbo.common.base.log.aspect;

import com.google.common.collect.Sets;
import com.liyz.dubbo.common.base.constant.CommonConstant;
import com.liyz.dubbo.common.base.log.annotation.LogIgnore;
import com.liyz.dubbo.common.base.log.annotation.Logs;
import com.liyz.dubbo.common.base.util.HttpRequestUtil;
import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/4 20:38
 */
@Slf4j
@ConditionalOnExpression("${spring.logs:false}")
@Aspect
@Configuration
@Order(2)
public class LogsAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.liyz.dubbo.common.base.log.annotation.Logs)")
    public void aspect() {}

    /**
     * 环绕切面
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logs logs = method.getAnnotation(Logs.class);
        String methodName = StringUtils.isBlank(logs.method())
                ? joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName() : logs.method();
        Class clazz = joinPoint.getTarget().getClass();
        int type = clazz.isAnnotationPresent(RestController.class) ? 0 :
                clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(DubboService.class) ? 1 : -1;
        String ip = type == 0
                ? HttpRequestUtil.getIpAddress(((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest())
                : null;
        String logId = RpcContext.getContext().getAttachment(CommonConstant.DUBBO_LOG_ID);
        if (StringUtils.isBlank(logId)) {
            logId = UUID.randomUUID().toString().replaceAll("-", "");
            RpcContext.getContext().setAttachment(CommonConstant.DUBBO_LOG_ID, logId);
        }
        if (type >= 0 && logs.before()) {
            paramsLog(joinPoint, methodName, logId);
        }
        Object obj = joinPoint.proceed();
        if (type >= 0 && logs.after()) {
            log.info("logId : {}, method : {} ; response result : {}", logId, methodName, JsonMapperUtil.toJSONString(obj));
        }
        return obj;
    }

    /**
     * 记录请求参数
     *
     * @param joinPoint
     * @param methodName
     * @param logId
     */
    private void paramsLog(JoinPoint joinPoint, String methodName, String logId) {
        if (joinPoint.getSignature() instanceof MethodSignature) {
            Set<Integer> ignoreSet = Sets.newTreeSet();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            //得到方法的参数名
            String[] parameterNames = methodSignature.getParameterNames();
            //得到当前方法的参数值
            Object[] args = joinPoint.getArgs();
            //得到方法中的每个参数的注解列表
            Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
            for (int i = 0, j = parameterAnnotations.length; i < j; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                for (Annotation annotation : annotations) {
                    if (annotation instanceof LogIgnore) {
                        ignoreSet.add(i);
                    }
                }
            }
            int argsSize = Objects.isNull(args) ? 0 : args.length;
            if (parameterNames != null && parameterNames.length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (int i = 0, j = parameterNames.length; i < j; i++) {
                    if (ignoreSet.contains(i)) {
                        continue;
                    }
                    sb.append(parameterNames[i]).append("=");
                    if (i < argsSize) {
                        if (Objects.nonNull(args[i])) {
                            sb.append(JsonMapperUtil.toJSONString(args[i]));
                        } else {
                            sb.append("null");
                        }
                    } else {
                        sb.append("null");
                    }
                    if (i + 1 < j) {
                        sb.append("; ");
                    }
                }
                sb.append("]");
                log.info("logId : {}, method : {}, params : {}", logId, methodName, sb.toString());
            }
        }
    }

    /**
     * 异常切面
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logs logs = method.getAnnotation(Logs.class);
        String methodName = StringUtils.isBlank(logs.method())
                ? joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName() : logs.method();
        String logId = RpcContext.getContext().getAttachment(CommonConstant.DUBBO_LOG_ID);
        if (StringUtils.isBlank(logId)) {
            logId = UUID.randomUUID().toString().replaceAll("-", "");
            RpcContext.getContext().setAttachment(CommonConstant.DUBBO_LOG_ID, logId);
        }
        if (logs.exception()) {
            log.error("logId : {}, method : {} ; exception type : {} ; exception message : {}", logId, methodName,
                    ex.getClass().getSimpleName(), ex.getMessage());
        }
    }
}
