package com.liyz.dubbo.service.message.service;

import com.liyz.dubbo.common.dao.abs.AbstractService;
import com.liyz.dubbo.service.message.model.MsgTemplateDO;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 9:37
 */
@Service
public class MsgTemplateService extends AbstractService<MsgTemplateDO> {

    @Override
    public MsgTemplateDO getOne(MsgTemplateDO model) {
        return super.getOne(model);
    }
}
