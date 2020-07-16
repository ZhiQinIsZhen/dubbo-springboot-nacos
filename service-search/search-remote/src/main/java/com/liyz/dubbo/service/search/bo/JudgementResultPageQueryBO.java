package com.liyz.dubbo.service.search.bo;

import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import lombok.Data;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/16 10:09
 */
@Data
public class JudgementResultPageQueryBO extends PageBaseBO {
    private static final long serialVersionUID = -3549952111209773867L;

    private String keyWord;
}
