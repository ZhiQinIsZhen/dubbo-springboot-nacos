package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:22
 */
@Getter
@Setter
public class ProjectShareHolderBO implements Serializable {
    private static final long serialVersionUID = -6066973406994180694L;

    /**
     * 公司名
     */
    private String name;

    /**
     * 类型  1 ⼗⼤股东 2 ⼗⼤流通股东
     */
    private Integer type;

    /**
     * 持股数量
     */
    private String holdingNum;

    /**
     * 持股变化 （万股）
     */
    private String compareChange;

    /**
     * 占股本比例（%）
     */
    private String proportion;

    /**
     * 实际增减持（%）
     */
    private String actual;
}
