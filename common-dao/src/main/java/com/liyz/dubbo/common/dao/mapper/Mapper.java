package com.liyz.dubbo.common.dao.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 注释:定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 18:06
 */
public interface Mapper<T> extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {
}
