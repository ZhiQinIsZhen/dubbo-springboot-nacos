package com.liyz.dubbo.common.socket.util;

import com.liyz.dubbo.common.base.util.SpringContextUtil;
import com.liyz.dubbo.common.socket.service.biz.BizService;
import com.liyz.dubbo.common.socket.service.oper.OperaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释:OperaDataUtil
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:49
 */
@Slf4j
@Component
public class OperaDataUtil {

    private static Map<String, OperaService> holder = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, OperaService> beanMaps = SpringContextUtil.getBeansOfType(OperaService.class);
        if (!CollectionUtils.isEmpty(beanMaps)) {
            for (OperaService operaService : beanMaps.values()) {
                holder.put(operaService.getOperaTypeService().getOperaType(), operaService);
            }
        }
        log.info("OperaService bean list , size : {}", CollectionUtils.isEmpty(holder) ? 0 : holder.size());
    }

    public static OperaService getService(String operaType) {
        if (CollectionUtils.isEmpty(holder)) {
            return null;
        }
        return holder.get(operaType);
    }
}
