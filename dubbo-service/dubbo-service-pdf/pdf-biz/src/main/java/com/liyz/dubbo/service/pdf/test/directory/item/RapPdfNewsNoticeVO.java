package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新闻舆情
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/31 13:27
 */
@Data
public class RapPdfNewsNoticeVO implements Serializable {

    /**
     * 新闻舆情
     */
    private List<SaProjectGroupTable<RaProjectNewsResponseVO>> newsList;

    /**
     * 重点公告
     */
    private List<SaProjectGroupTable<RaProjectNoticeResponseVO>> noticeList;

    /**
     * 行业趋势
     */
    private List<RaProjectIndustryTrendsResponseVO> industryTrendsList;

}
