package com.liyz.dubbo.common.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 注释:自动增加以及修改参数以及对应值
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 16:19
 */
@Configuration
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName("create_time", now, metaObject);
        setFieldValByName("update_time", now, metaObject);
        setFieldValByName("isDeleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("update_time", new Date(), metaObject);
    }
}
