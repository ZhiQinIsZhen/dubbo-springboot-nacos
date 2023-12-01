package com.liyz.dubbo.service.auth.provider;

import cn.hutool.jwt.JWT;
import com.liyz.dubbo.common.util.RandomUtil;
import com.liyz.dubbo.common.util.TestUtil;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/30 16:04
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestUtil.class})
public class RemoteJwtParseServiceImplTest {

    @Test
    public void test() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiMTU5ODg2NTQ3MzAiLCJhdWQiOlsiZHViYm8tYXBpLWFkbWluIl0sImV4cCI6MTcwMTY3OTg5OSwibmJmIjoxNzAxMDc1MDk5LCJkZXZpY2UiOjJ9.O4Xc0PGlIw-uB3Gn3AUP0_QZHGrCrCGMFWe7LiBhr7hluKTY10z0Y5FB_T-Wm2lfTDErjDNpan4x8q3L7F-BDA";
        log.info("{}", JWT.of(token).getPayloads().toString());
        String str = Jwts.parser().build().parse(token).toString();
        log.info("{}", str);
    }
}