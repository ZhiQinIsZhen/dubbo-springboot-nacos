package com.liyz.dubbo.common.dao.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * 注释:Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 18:05
 */
public interface Service<T> {

    /**
     * 持久化
     *
     * @param model model实体
     */
    int save(T model);

    /**
     * 批量持久化
     *
     * @param models model实体列表
     */
    int save(List<T> models);

    /**
     * 根据主鍵刪除
     *
     * @param id 主键
     */
    int removeById(Object id);

    /**
     * 更新
     *
     * @param model model实体
     */
    int updateById(T model);

    /**
     * 根据主键查找
     *
     * @param id 主键
     * @return
     */
    T getById(Object id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     *
     * @param fieldName 成员变量名称
     * @param value     值
     * @return
     * @throws TooManyResultsException
     */
    T getBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 获取一个
     *
     * @param model
     * @return
     */
    T getOne(T model);

    /**
     * 通过多个ID查找
     *
     * @param ids eg：ids -> “1,2,3,4”
     * @return
     */
    List<T> listByIds(String ids);

    /**
     * 根据条件查找
     *
     * @param condition 自定义条件
     * @return
     */
    List<T> listByCondition(Condition condition);

    /**
     * 根据条件查询
     *
     * @param model
     * @return
     */
    List<T> list(T model);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> listAll();
}
