package com.liyz.dubbo.service.search.impl.bid;

import com.liyz.dubbo.service.search.bo.bid.BiddingBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.query.company.CompanySearchQuery;
import com.liyz.dubbo.service.search.service.abs.AbstractNewSearchServiceImpl;
import com.liyz.dubbo.service.search.service.bid.BiddingService;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/12/1 10:01
 */
@Service
public class BiddingServiceImpl extends AbstractNewSearchServiceImpl<BiddingBO, CompanySearchQuery>
        implements BiddingService {

    @Override
    protected SearchType getSearchType() {
        return SearchType.BIDDING;
    }
}
