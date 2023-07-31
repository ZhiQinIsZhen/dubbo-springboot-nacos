package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tangtang
 * @description：
 * @date Created in 2022/5/26 9:44
 * @version: 1.0.0
 */
@Data
public class RaProjectNewsNoticeResponseVO implements Serializable {
    private static final long serialVersionUID = -4600388688396402221L;

    //新闻舆情
    @ApiModelProperty("新闻舆情")
    private List<SaProjectGroupTable<RaProjectNewsResponseVO>> raProjectNewsResponses;


    //重点公告
    @ApiModelProperty("重点公告")
    private List<SaProjectGroupTable<RaProjectNoticeResponseVO>> raProjectNoticeResponses;

    //行业趋势
    @ApiModelProperty("行业趋势")
    private List<RaProjectIndustryTrendsResponseVO> raProjectIndustryTrendsResponses;

}
