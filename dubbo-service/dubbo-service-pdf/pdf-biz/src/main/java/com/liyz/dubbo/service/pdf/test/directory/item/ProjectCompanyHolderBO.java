package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:21
 */
@Getter
@Setter
public class ProjectCompanyHolderBO implements Serializable {
    private static final long serialVersionUID = 5304523752509196962L;

    /**
     * 股东名称
     */
    private String name;

    /**
     * 认缴金额
     */
    private String amomon;

    /**
     * 认缴时间
     */
    private String time;

    /**
     * 所占比例
     */
    private String percent;
}
