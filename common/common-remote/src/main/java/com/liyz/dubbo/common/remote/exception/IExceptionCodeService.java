package com.liyz.dubbo.common.remote.exception;

/**
 * 注释:该工程自定义异常底层接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 13:44
 */
public interface IExceptionCodeService {

    /**
     * 获取异常code
     *
     * @return
     */
    String getCode();

    /**
     * 获取异常信息
     *
     * @return
     */
    String getMessage();
}
