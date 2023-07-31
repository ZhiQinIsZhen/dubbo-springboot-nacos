package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:欠税公告
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 17:41
 */
@Getter
@Setter
public class RaProjectMrOwnTaxResponseVO implements Serializable {
    private static final long serialVersionUID = 8242809944158904598L;

    /**
     * 关联公司名称
     */
    @ApiModelProperty("关联公司名称")
    private String relationCompanyName;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private String publishDate;

    /**
     * 纳税人识别号
     */
    @ApiModelProperty("纳税人识别号")
    private String taxIdNumber;

    /**
     * 欠税税种
     */
    @ApiModelProperty("欠税税种")
    private String taxCategory;

    /**
     * 当前新发生欠税余额
     */
    @ApiModelProperty("当前新发生欠税余额")
    private String newOwnTaxBalance;

    /**
     * 欠税余额
     */
    @ApiModelProperty("欠税余额")
    private String ownTaxBalance;

    /**
     * 税务机关
     */
    @ApiModelProperty("税务机关")
    private String department;
}
