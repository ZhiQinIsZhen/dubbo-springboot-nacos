package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/27 15:31
 */
@Getter
@Setter
public class RaProjectFinancialExtResponseVO implements Serializable {
    private static final long serialVersionUID = -3685811598750948895L;


    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签(多个英文,分隔) 0:项目公司,1:实际控股公司,2:其他关联公司")
    private String companyLabel;

    @ApiModelProperty("是否从三方获取的财务数据 0：港股；1：三方；2：A股；3：万德港股；4：万德国版")
    private Integer isThirdFinancial;

    @ApiModelProperty("财务数据excel file key")
    private String financialFileKey;

    @ApiModelProperty("是否从三方获取的利润数据 0：港股；1：三方；2：A股；3：万德港股；4：万德国版")
    private Integer isThirdProfit;

    @ApiModelProperty("利润数据excel file key")
    private String profitFileKey;

    @ApiModelProperty("是否从三方获取的现金流量数据 0：港股；1：三方；2：A股；3：万德港股；4：万德国版")
    private Integer isThirdCashFlow;

    @ApiModelProperty("现金流量数据excel file key")
    private String cashFlowFileKey;

}
