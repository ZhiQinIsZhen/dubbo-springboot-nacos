package com.liyz.dubbo.service.search.properties.bid;

import com.liyz.dubbo.service.search.properties.BaseProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/11 14:21
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "company.bidding")
public class BiddingProperties extends BaseProperties {
}
