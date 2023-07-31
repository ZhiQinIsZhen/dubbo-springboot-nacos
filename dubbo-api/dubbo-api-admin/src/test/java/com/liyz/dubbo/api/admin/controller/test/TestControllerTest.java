package com.liyz.dubbo.api.admin.controller.test;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyz.dubbo.api.admin.vo.test.TestVO;
import com.liyz.dubbo.common.util.TestUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/11 15:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestUtil.class})
public class TestControllerTest {

    @SneakyThrows
    @Test
    public void test() {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
        objectMapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);
        TestVO testVO = new TestVO();
        testVO.setName("2222");
        log.info("data : {}", objectMapper.writeValueAsString(testVO));
    }

    @Test
    public void test3() {
        Long time = Long.parseLong("1505232000000");
        Date date = DateUtil.date(time).toJdkDate();
        log.info("date : {}", date);
        date =  new Date(time);
        log.info("date : {}", date);
    }
}