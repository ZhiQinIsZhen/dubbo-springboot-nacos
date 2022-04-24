package com.liyz.dubbo.security.client.context;

import com.liyz.dubbo.common.core.constant.CommonConstant;
import com.liyz.dubbo.common.core.util.HttpRequestUtil;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.remote.RemoteGrantedAuthorityCoreService;
import com.liyz.dubbo.security.core.remote.RemoteJwtAuthCoreService;
import com.liyz.dubbo.security.core.user.AuthUserDetails;
import com.liyz.dubbo.security.core.user.ClaimDetail;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 注释:jwt holder
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:37
 */
@Configuration
public class JwtContextHolder implements ApplicationContextAware, InitializingBean {

    private static ApplicationContext applicationContext;
    private static RemoteJwtAuthCoreService remoteJwtAuthCoreService;
    private static RemoteGrantedAuthorityCoreService remoteGrantedAuthorityCoreService;

    public static RemoteJwtAuthCoreService getJwtAuthService() {
        return remoteJwtAuthCoreService;
    }

    public static RemoteGrantedAuthorityCoreService getGrantedAuthorityService() {
        return remoteGrantedAuthorityCoreService;
    }

    public static String getJWT(Date lastLoginTime, SecurityEnum.AudienceType audienceType) {
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        LiteDeviceResolver resolver = new LiteDeviceResolver();
        Device device = resolver.resolveDevice(httpServletRequest);
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClaimDetail claimDetail = new ClaimDetail();
        claimDetail.setDevice(device.isMobile() ? CommonConstant.DEVICE_MOBILE : CommonConstant.DEVICE_WEB);
        claimDetail.setUsername(authUserDetails.getLoginName());
        claimDetail.setUserId(authUserDetails.getId());
        claimDetail.setCreation(lastLoginTime);
        claimDetail.setAudience(audienceType.getCode());
        return getJwtAuthService().getJWT(claimDetail);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        remoteJwtAuthCoreService = applicationContext.getBean("remoteJwtAuthCoreService", RemoteJwtAuthCoreService.class);
        remoteGrantedAuthorityCoreService = applicationContext.getBean("remoteGrantedAuthorityCoreService", RemoteGrantedAuthorityCoreService.class);
    }
}
