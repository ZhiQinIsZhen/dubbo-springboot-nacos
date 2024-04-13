package com.liyz.dubbo.service.search.bo.company;

import com.liyz.dubbo.service.search.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:38
 */
@Getter
@Setter
public class CompanyBO extends BaseBO {
    private static final long serialVersionUID = -1431102148095190647L;

    private String companyId;

    private String regNumber;

    private String companyName;

    private String creditCode;

    private String address;

    private Date establishmentTime;
}
