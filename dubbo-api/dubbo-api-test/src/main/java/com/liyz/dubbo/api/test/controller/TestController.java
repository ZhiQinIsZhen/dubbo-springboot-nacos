package com.liyz.dubbo.api.test.controller;

import com.liyz.dubbo.api.test.vo.TestVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/5/17 15:27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/init")
    public TestVO init() {
        TestVO testVO = new TestVO();
        testVO.setName("liyz");
        testVO.setId(1787312316911792129L);
        return testVO;
    }

    @GetMapping("/init1")
    public TestVO init1() {
        TestVO testVO = new TestVO();
        testVO.setName("liyz1");
        return testVO;
    }
}
