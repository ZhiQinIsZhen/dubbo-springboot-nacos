package com.liyz.dubbo.common.dao.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/8 14:14
 */
public interface ILyzService<T> extends IService<T> {

    /**
     * 通过主键真实删除
     * 注：如果配置了逻辑删除
     *
     * @param id
     * @return
     */
    default int deleteById(Serializable id) {
        return ((LyzBaseMapper) this.getBaseMapper()).deleteRealById(id);
    }

    /**
     * 通过条件真实删除
     * 注：如果配置了逻辑删除
     *
     * @param queryWrapper
     * @return
     */
    default int deleteReal(Wrapper<T> queryWrapper) {
        return ((LyzBaseMapper) this.getBaseMapper()).deleteReal(queryWrapper);
    }
}
