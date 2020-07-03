package com.liyz.dubbo.common.base.constant;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/10 18:23
 */
public interface CommonConstant {

    //邮箱正则表达式
    String EMAILREG = "^([a-zA-Z0-9_\\-\\.\\+]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    //手机正则表达式
    String PHONEREG = "^1(3|4|5|7|8|9)\\d{9}$";

    String DEFAULT_SPLIT = "_";
}
