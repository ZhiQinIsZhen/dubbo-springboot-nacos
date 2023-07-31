package com.liyz.dubbo.service.pdf.test.directory;

import com.liyz.dubbo.service.pdf.constant.RapTocConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:56
 */
@Getter
@Setter
public class TestDirectoryBO extends DirectoryBO{

    /**
     * 评估结果
     */
    protected Dire riskCheck;

    /**
     * 评估结果
     */
    protected Dire result;
    /**
     * 项目方信息
     */
    protected Dire projectPartyInfo;

    /**
     * 项目信息
     */
    protected Dire projectInfo;

    /**
     * 经营状况
     */
    protected Dire oc;
    /**
     * 商票情况
     */
    protected Dire commercialTicket;
    /**
     * 债券及信用评级信息
     */
    protected Dire bond;
    /**
     * 法律风险
     */
    protected Dire legalRisk;
    /**
     * 关联风险
     */
    protected Dire associatedRisk;
    /**
     * 城投分析
     */
    protected Dire cityAnalysis;
    /**
     * 新闻舆情
     */
    protected Dire newsNotice;

    public TestDirectoryBO() {


        result = root.initChildren("评估结果摘要", RapTocConstant.TOC_RESULT);

        riskCheck = result.createNext("风险核查", RapTocConstant.TOC_RISK_CHECK);

        projectPartyInfo = riskCheck.createNext("客户信息", RapTocConstant.TOC_PROJECT_PARTY_INFO);

        projectInfo = projectPartyInfo.createNext("项目信息", RapTocConstant.TOC_PROJECT_INFO);

        oc = projectInfo.createNext("经营情况", RapTocConstant.TOC_OC);

        commercialTicket = oc.createNext("商票情况", RapTocConstant.TOC_COMMERCIAL_TICKET);

        bond = commercialTicket.createNext("债券及信用评级", RapTocConstant.TOC_BOND);

        legalRisk = bond.createNext("司法风险", RapTocConstant.TOC_LEGAL_RISK);

        associatedRisk = legalRisk.createNext("关联风险", RapTocConstant.TOC_ASSOCIATED_RISK);

        cityAnalysis = associatedRisk.createNext("城投分析", RapTocConstant.TOC_CITY_INVESTMENT_ANALYSIS);

        newsNotice = cityAnalysis.createNext("舆情信息", RapTocConstant.TOC_NEWS_NOTICE);

        // 创建二级目录和三级目录
        result.initChildren("项目风险等级", RapTocConstant.TOC_PROJECT_RISK_TYPE)
                .createNext("评估意见", RapTocConstant.TOC_APPROVAL_COMMENTS)
                .createNext("重点关注", RapTocConstant.TOC_FOCUS);

        projectPartyInfo.initChildren("基本信息", RapTocConstant.TOC_PROJECT_BASIC)
                .createNext("股权结构", RapTocConstant.TOC_SHAREHOLDER)
                .initChildren("工商登记", RapTocConstant.TOC_BUSINES_REGISTRATION)
                .createNext("最新公示", RapTocConstant.TOC_LATETOC_ANNOUNCEMENT)
                .getParent()
                .createNext("关联信息", RapTocConstant.TOC_REDUNDANCY_INFORMATION)
                .initChildren("关联公司及自然人", RapTocConstant.TOC_COMPANIES_PERSONS)
                .createNext("实际控制人", RapTocConstant.TOC_HUMANHOLDING)
                .getParent()
                .createNext("参保人数", RapTocConstant.TOC_SOCIALSTAFFINFO);

        projectInfo.initChildren("基本信息", RapTocConstant.TOC_PROJECT_BASIC_INFO)
                .createNext("周边配套", RapTocConstant.TOC_PROJECT_SURROUTOC_FACILITY);

        oc.initChildren("财务简析", RapTocConstant.TOC_FINANCE_BRIEF)
                .initChildren("三条红线情况", RapTocConstant.TOC_THREE_RED_LINES)
                .createNext("关键财务指标", RapTocConstant.TOC_INDICATOR)
                .getParent()
                .createNext("财务数据", RapTocConstant.TOC_FINANCE_DATA)
                .initChildren("资产负债表", RapTocConstant.TOC_ASSETS_LIABILITIES)
                .createNext("利润表", RapTocConstant.TOC_PROFIT)
                .createNext("现金流量表", RapTocConstant.TOC_CASH_FLOW)
                .getParent()
                .createNext("经营风险", RapTocConstant.TOC_RISK)
                .initChildren("欠税公告", RapTocConstant.TOC_OWN_TAXES)
                .createNext("税收违法", RapTocConstant.TOC_TAX_CONTRAVENTIONS)
                .createNext("简易注销", RapTocConstant.TOC_BRIEF_CANCEL)
                .createNext("司法拍卖", RapTocConstant.TOC_JUDICIAL_SALES)
                .createNext("动产抵押", RapTocConstant.TOC_MORTGAGES)
                .createNext("土地质押", RapTocConstant.TOC_LATOC_MORTGAGES)
                .createNext("股权出质", RapTocConstant.TOC_EQUITIES)
                .createNext("质押明细", RapTocConstant.TOC_HOLDER_PLEDGES)
                .getParent()
                .createNext("授信及担保", RapTocConstant.TOC_CREDIT_GUARANTEE)
                .initChildren("授信余额", RapTocConstant.TOC_CREDIT_GUARANTEE_CREDIT)
                .createNext("对外担保", RapTocConstant.TOC_CREDIT_GUARANTEE_GUARANTEE)
                .getParent()
                .createNext("土储分析", RapTocConstant.TOC_SOIL_STORAGE_ANALYSIS)
                .initChildren("拿地趋势", RapTocConstant.TOC_LAND_ACQUISITION_TENDENCY_A)
                .createNext("土储分布", RapTocConstant.TOC_SOIL_STORAGE_DISTRIBUTION);

        commercialTicket.initChildren("商票情况", RapTocConstant.TOC_OVERVIEW)
                .createNext("商票违约情况", RapTocConstant.TOC_VIOLATE)
                .createNext("承兑统计", RapTocConstant.TOC_CENSUS);

        bond.initChildren("债券信息", RapTocConstant.TOC_BOTOC_DETAIL)
                .createNext("债券违约", RapTocConstant.TOC_BOTOC_DEFAULT)
                .createNext("信用评级", RapTocConstant.TOC_BOTOC_CREDIT_RATING);

        legalRisk.initChildren("法律诉讼", RapTocConstant.TOC_LAW_SUIT)
                .createNext("开庭公告", RapTocConstant.TOC_KTANNOUNCEMENT)
                .createNext("被执行人", RapTocConstant.TOC_ZHIXINGINFO)
                .createNext("失信", RapTocConstant.TOC_DISHONEST)
                .createNext("限制消费", RapTocConstant.TOC_CONSUMPTION_RESTRICTION);

        associatedRisk.initChildren("股东限制消费", RapTocConstant.TOC_NR_CONSUMPTION_RESTRICTION)
                .createNext("股东失信", RapTocConstant.TOC_NR_DISHONEST)
                .createNext("股东被执行", RapTocConstant.TOC_NR_ZHIXINGINFO);

        cityAnalysis.initChildren("区域信息", RapTocConstant.TOC_REGIONAL_INFORMATION_ANALYSIS)
                .initChildren("区域经济", RapTocConstant.TOC_REGIONAL_ECONOMICS_ANALYSIS)
                .createNext("区域债务", RapTocConstant.TOC_REGIONAL_ILIABILITIES_ANALYSIS)
                .createNext("区域财政", RapTocConstant.TOC_REGIONAL_FINANCE_ANALYSIS)
                .getParent()
                .createNext("财政补助", RapTocConstant.TOC_REGIONAL_GOVERNMENT_SUPPLIES)
                .createNext("地域利差", RapTocConstant.TOC_REGIONAL_GEOGRAPHICAL_SPREAD)
                .createNext("地域久期", RapTocConstant.TOC_REGIONAL_REGIONAL_DURATION);

        newsNotice.initChildren("新闻舆情", RapTocConstant.TOC_NEWS)
                .createNext("重要公告", RapTocConstant.TOC_NOTICE)
                .createNext("行业趋势", RapTocConstant.TOC_INDUSTRY_TRENDS);
    }
}
