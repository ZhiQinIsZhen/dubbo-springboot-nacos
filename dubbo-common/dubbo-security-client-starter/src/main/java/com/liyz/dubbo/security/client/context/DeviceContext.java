package com.liyz.dubbo.security.client.context;

import com.liyz.dubbo.service.auth.enums.Device;
import lombok.experimental.UtilityClass;
import org.springframework.mobile.device.LiteDeviceResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 14:52
 */
@UtilityClass
public class DeviceContext {

    /**
     * 获取设备类型
     *
     * @param request http request
     * @return 设备类型
     */
    public static Device getDevice(HttpServletRequest request) {
        return new LiteDeviceResolver().resolveDevice(request).isMobile() ? Device.MOBILE : Device.WEB;
    }
}
