package com.liyz.dubbo.service.search.provider;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.query.company.CompanySearchQuery;
import com.liyz.dubbo.service.search.remote.RemoteSearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoteSearchServiceImplTest {

    @Resource
    private RemoteSearchService remoteSearchService;

    @Test
    public void testSearch() {
        CompanySearchQuery query = new CompanySearchQuery();
        query.setId("d6189b072c60ca0d668e05c006c45db6");
        CompanyBO companyBO = remoteSearchService.search(SearchType.COMPANY, query);
        log.info("value : {}", JsonMapperUtil.toJSONString(companyBO));
    }

    @Test
    public void testSearchList() {
        CompanySearchQuery query = new CompanySearchQuery();
        query.setCompanyName("杭州阿里巴巴");
        List<CompanyBO> list = remoteSearchService.searchList(SearchType.COMPANY, query);
        log.info("value : {}", JsonMapperUtil.toJSONPrettyString(list));
    }

    @Test
    public void testSearchList1() {
        CompanySearchQuery query = new CompanySearchQuery();
        query.setCompanyName("杭州阿里巴巴");
        query.setSlop(0);
        List<CompanyBO> list = remoteSearchService.searchList(SearchType.COMPANY, query);
        log.info("value : {}", JsonMapperUtil.toJSONPrettyString(list));
    }

    @Test
    public void testSearchList2() {
        CompanySearchQuery query = new CompanySearchQuery();
        query.setCompanyName("阿里巴巴杭州");
        query.setSlop(1);
        List<CompanyBO> list = remoteSearchService.searchList(SearchType.COMPANY, query);
        log.info("value : {}", JsonMapperUtil.toJSONPrettyString(list));
    }

    @Test
    public void testSearchPage() {
        CompanySearchQuery query = new CompanySearchQuery();
        query.setCompanyName("杭州商商查网络科技有限公司");
        RemotePage<CompanyBO> remotePage = remoteSearchService.searchPage(SearchType.COMPANY, query);
        log.info("value : {}", JsonMapperUtil.toJSONString(remotePage));
    }
}