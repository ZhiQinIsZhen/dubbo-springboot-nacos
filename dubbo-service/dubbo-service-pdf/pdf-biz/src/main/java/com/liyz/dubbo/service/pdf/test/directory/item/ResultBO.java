package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:09
 */
@Getter
@Setter
public class ResultBO implements Serializable {
    private static final long serialVersionUID = -6424206833528369163L;

    /**
     * 报告编号
     */
    private String reportNo;
    /**
     * 项目方公司名称
     */
    private String corpName;
    /**
     * 项目方公司社会统一信用代码
     */
    private String socialCode;
    /**
     * 发起评估企业logo，从天眼查查询获得
     */
    private String originateCorpLogo;
    /**
     * 发起评估企业名称
     */
    private String originateCorpName;

    /**
     * 申请日期
     */
    private String applyDate;
    /**
     * 报告生成日期
     */
    private String genDate;
    /**
     * 决策结果,A B C D E
     */
    private String result;

    private ComplexStructureBO complexStructure;
}
