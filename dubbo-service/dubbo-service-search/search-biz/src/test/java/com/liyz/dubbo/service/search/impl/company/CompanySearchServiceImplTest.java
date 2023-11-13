package com.liyz.dubbo.service.search.impl.company;

import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import com.liyz.dubbo.service.search.query.company.CompanySearchQuery;
import com.liyz.dubbo.service.search.service.company.CompanySearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/13 17:22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanySearchServiceImplTest {

    @Resource
    private CompanySearchService companySearchService;

    @Test
    public void testSearch() {
        CompanySearchQuery query = new CompanySearchQuery();
        query.setId("d6189b072c60ca0d668e05c006c45db6");
        CompanyBO companyBO = companySearchService.search(query);
        log.info("value : {}", JsonMapperUtil.toJSONString(companyBO));
    }
}