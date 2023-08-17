package com.liyz.dubbo.service.search.bo.company;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:38
 */
@Getter
@Setter
public class CompanyBO implements Serializable {
    private static final long serialVersionUID = -1431102148095190647L;

    private String id;

    private String companyId;

    private String companyName;

    private String creditCode;
}
