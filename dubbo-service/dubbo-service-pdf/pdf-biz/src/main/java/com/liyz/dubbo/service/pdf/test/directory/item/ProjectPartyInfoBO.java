package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:17
 */
@Getter
@Setter
public class ProjectPartyInfoBO implements Serializable {
    private static final long serialVersionUID = -7158379553140313622L;

    /**
     * 项目方基本信息
     */
    private ProjectCompanyBasicBO basic;

    /**
     * 企业股权结构
     */
    private CorpShareholdingStructureBO shareholdingStructure;

    /**
     * 项目方关联信息
     */
    private RaProjectAssociationInformationStructure associationInformationStructure;

    /**
     * 参保信息
     */
    private RaProjectSocialStaffInfoStructure socialStaffInfoStructure;
}
