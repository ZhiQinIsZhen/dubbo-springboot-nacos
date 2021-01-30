package com.liyz.dubbo.common.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/29 16:01
 */
@Slf4j
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
