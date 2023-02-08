package com.liyz.dubbo.common.dao.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/8 14:16
 */
public interface LyzBaseMapper<T> extends BaseMapper<T> {

    /**
     * 通过主键真实删除
     * 注：如果配置了逻辑删除
     *
     * @param id
     * @return
     */
    int deleteRealById(Serializable id);

    /**
     * 通过条件真实删除
     * 注：如果配置了逻辑删除
     *
     * @param queryWrapper
     * @return
     */
    int deleteReal(@Param("ew") Wrapper<T> queryWrapper);
}
