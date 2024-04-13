package com.liyz.dubbo.service.search.impl.bid;

import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.service.search.bo.bid.BiddingBO;
import com.liyz.dubbo.service.search.model.BiddingDO;
import com.liyz.dubbo.service.search.repository.BiddingRepository;
import com.liyz.dubbo.service.search.service.bid.BiddingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/12/1 10:05
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BiddingServiceImplTest {

    @Resource
    private BiddingService biddingService;
    @Resource
    private BiddingRepository repository;

    @Test
    public void testSearch() {
        String id = "1f99c1ee9c69c8ff37111feb71c9e9df";
        BiddingBO biddingBO = biddingService.getById(id);
        log.info("value : {}", JsonMapperUtil.toJSONString(biddingBO));
    }

    @Test
    public void testSearch1() {
        String id = "1f99c1ee9c69c8ff37111feb71c9e9df";
        BiddingDO biddingDO = repository.findById(id).orElse(null);
        if (Objects.nonNull(biddingDO)) {
            biddingDO.setBidWin("嘉斯中科（北京）科技有限公司,北京麦迪克斯创新科技有限公司");
            if (!CollectionUtils.isEmpty(biddingDO.getParty())) {
                ListIterator<BiddingDO.Party> iterator = biddingDO.getParty().listIterator();
                while (iterator.hasNext()) {
                    BiddingDO.Party party = iterator.next();
                    if ("嘉斯中科（北京）科技有限公司".equals(party.getPartyName())) {
                        party.setPartyType("中标方");
                    } else if ("01包嘉斯中科（北京）科技有限公司".equals(party.getPartyName())) {
                        iterator.remove();
                    }
                }
            }
//            biddingDO = repository.save(biddingDO);
        }

        log.info("value : {}", JsonMapperUtil.toJSONString(biddingDO));
    }
}