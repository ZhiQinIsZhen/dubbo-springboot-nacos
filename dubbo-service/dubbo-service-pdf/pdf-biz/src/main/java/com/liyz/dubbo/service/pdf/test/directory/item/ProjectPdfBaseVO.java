package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * description: TODO  项目基类
 * author: huanglb
 * date 2023/2/20 15:44
 */
@Data
@Slf4j
public abstract class ProjectPdfBaseVO implements Serializable {


    /**
     * processId==businessId
     */
    protected String processId;
    /**
     * processId==businessId
     */
    protected String businessId;

    protected String token;

    protected String reportType;
    /**
     * 租户id
     */
    protected String tenantId;

    /**
     *
     */
    protected String applicant;

    /**
     *
     */
    protected String pdfName;
    /**
     *
     */
    protected String fuwuDomain;

    /**
     * 文件服务器
     */
    protected String fsUrl;


    public abstract String getCorpName();

    public abstract String getReportNo();

    public abstract String getOriginateCorpName();

    public abstract String getOriginateCorpLogo();

    public abstract String getGenDate();

    public abstract String getApplyDate();
}
