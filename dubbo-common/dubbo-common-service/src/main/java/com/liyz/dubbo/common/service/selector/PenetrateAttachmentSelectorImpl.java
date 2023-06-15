package com.liyz.dubbo.common.service.selector;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.PenetrateAttachmentSelector;

import java.util.Map;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/13 19:39
 */
@Slf4j
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class PenetrateAttachmentSelectorImpl implements PenetrateAttachmentSelector {

    @Override
    public Map<String, Object> select() {
        log.info("inter PenetrateAttachmentSelectorImpl#select");
        return null;
    }
}
