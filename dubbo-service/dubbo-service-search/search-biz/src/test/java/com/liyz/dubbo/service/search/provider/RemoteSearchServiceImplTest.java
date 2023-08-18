package com.liyz.dubbo.service.search.provider;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.service.search.bo.SearchBO;
import com.liyz.dubbo.service.search.bo.SearchPageBO;
import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import com.liyz.dubbo.service.search.constant.SearchType;
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
        SearchBO searchBO = new SearchPageBO();
        searchBO.setId("d6189b072c60ca0d668e05c006c45db6");
        CompanyBO companyBO = remoteSearchService.search(SearchType.COMPANY, searchBO);
        log.info("value : {}", JsonMapperUtil.toJSONString(companyBO));
    }

    @Test
    public void testSearchList() {
        SearchPageBO searchBO = new SearchPageBO();
        searchBO.setCompanyName("阿里巴巴");
        List<CompanyBO> list = remoteSearchService.searchList(SearchType.COMPANY, searchBO);
        log.info("value : {}", JsonMapperUtil.toJSONString(list));
    }

    @Test
    public void testSearchPage() {
        SearchPageBO searchBO = new SearchPageBO();
        searchBO.setCompanyName("阿里巴巴");
        RemotePage<CompanyBO> remotePage = remoteSearchService.searchPage(SearchType.COMPANY, searchBO);
        log.info("value : {}", JsonMapperUtil.toJSONString(remotePage));
    }
}