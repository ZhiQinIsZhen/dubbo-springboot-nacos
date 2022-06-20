package com.liyz.dubbo.service.bigdata.bo;

import com.liyz.dubbo.common.remote.page.PageBO;
import lombok.Getter;
import lombok.Setter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/17 16:02
 */
@Getter
@Setter
public class PageBaseBO extends PageBO {
    private static final long serialVersionUID = 6459133862564378717L;

    private String keyword;
}
