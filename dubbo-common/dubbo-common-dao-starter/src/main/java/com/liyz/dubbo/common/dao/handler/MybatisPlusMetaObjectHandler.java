package com.liyz.dubbo.common.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.liyz.dubbo.common.dao.constant.CommonDaoConstant;
import com.liyz.dubbo.common.service.util.LoginUserContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * Desc:Meta data auto fill
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 16:11
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName(CommonDaoConstant.DEFAULT_CREATE_TIME, now, metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_UPDATE_TIME, now, metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_CREATE_USER, LoginUserContext.getLoginId(), metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_UPDATE_USER, LoginUserContext.getLoginId(), metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_DELETED, CommonDaoConstant.DEFAULT_DELETED_VALUE, metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_VERSION, CommonDaoConstant.DEFAULT_VERSION_VALUE, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(CommonDaoConstant.DEFAULT_UPDATE_TIME, new Date(), metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_UPDATE_USER, LoginUserContext.getLoginId(), metaObject);
    }
}
