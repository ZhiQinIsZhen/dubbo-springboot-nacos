package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/23 19:15
 */
@Getter
@Setter
public class RaProjectJudicialRiskResponseVO implements Serializable {
    private static final long serialVersionUID = -3233888952544871445L;

    @ApiModelProperty(value = "法律诉讼列表")
    private List<SaProjectGroupTable<LawSuitResponseVO>> lawSuitResponseList;

    @ApiModelProperty(value = "开庭公告列表")
    private List<SaProjectGroupTable<KtannouncementResponseVO>> ktannouncementResponseList;

    @ApiModelProperty(value = "被执行人列表")
    private List<SaProjectGroupTable<ZhixinginfoResponseVO>> zhixinginfoResponseList;

    @ApiModelProperty(value = "失信人列表")
    private List<SaProjectGroupTable<DishonestResponseVO>> dishonestResponseList;

    @ApiModelProperty(value = "限制消费列表")
    private List<SaProjectGroupTable<ConsumptionRestrictionResponseVO>> consumptionRestrictionResponseList;

    @ApiModelProperty(value = "司法记录（老流程数据）")
    @Deprecated
    private RaProjectJudicialRecordResponseVO judicialRecord;
}
