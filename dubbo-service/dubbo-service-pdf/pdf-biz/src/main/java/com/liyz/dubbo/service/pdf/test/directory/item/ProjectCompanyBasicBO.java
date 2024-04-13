package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:18
 */
@Getter
@Setter
public class ProjectCompanyBasicBO implements Serializable {
    private static final long serialVersionUID = 502824503807840381L;

    /**
     * 项目方名称不能为空
     */
    private String companyName;

    /**
     * 所属集团
     */
    private String affiliatedGroup;

    /**
     * 社会统一信用代码
     */
    private String creditCode;

    /**
     * 注册资本
     */
    private String regCapital;

    /**
     * 企业类型
     */
    private String type;

    /**
     * 成立日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date estiblishTime;

    /**
     * 法人
     */
    private String legalPersonName;

    /**
     * 行业
     */
    private String industry;

    /**
     * 人员规模
     */
    private String staffNumRange;

    /**
     * 参保人数
     */
    private Integer socialStaffNum;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 公司简称
     */
    private String abbreviation;
}
