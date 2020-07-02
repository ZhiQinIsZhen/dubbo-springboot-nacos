package com.liyz.dubbo.common.dao.abs;

import com.liyz.dubbo.common.dao.exception.DaoServiceException;
import com.liyz.dubbo.common.dao.mapper.Mapper;
import com.liyz.dubbo.common.dao.service.Service;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 注释:基于通用MyBatis Mapper插件的Service接口的实现
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 18:04
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    /**
     * 构造方法
     */
    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * insert
     *
     * @param model model实体
     * @return
     */
    @Override
    public int save(T model) {
        return mapper.insertSelective(model);
    }

    /**
     * insert
     *
     * @param models model实体列表
     * @return
     */
    @Override
    public int save(List<T> models) {
        return mapper.insertList(models);
    }

    /**
     * delete
     *
     * @param id 主键
     * @return
     */
    @Override
    public int removeById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * update
     *
     * @param model model实体
     * @return
     */
    @Override
    public int updateById(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    /**
     * select
     *
     * @param id 主键
     * @return
     */
    @Override
    public T getById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * select
     *
     * @param fieldName 成员变量名称
     * @param value     值
     * @return
     * @throws TooManyResultsException
     */
    @Override
    public T getBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            List<T> list = mapper.select(model);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            }
            return list.get(0);
        } catch (ReflectiveOperationException e) {
            throw new DaoServiceException(e.getMessage(), e);
        }
    }

    /**
     * select
     *
     * @param model
     * @return
     */
    @Override
    public T getOne(T model) {
        List<T> list = mapper.select(model);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * select
     *
     * @param ids eg：ids -> “1,2,3,4”
     * @return
     */
    @Override
    public List<T> listByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    /**
     * select
     *
     * @param condition 自定义条件
     * @return
     */
    @Override
    public List<T> listByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    /**
     * select
     *
     * @param model
     * @return
     */
    @Override
    public List<T> list(T model) {
        return mapper.select(model);
    }

    /**
     * select
     *
     * @return
     */
    @Override
    public List<T> listAll() {
        return mapper.selectAll();
    }
}
