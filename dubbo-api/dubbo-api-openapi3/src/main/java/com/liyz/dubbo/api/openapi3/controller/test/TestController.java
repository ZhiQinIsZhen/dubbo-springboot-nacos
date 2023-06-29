package com.liyz.dubbo.api.openapi3.controller.test;

import com.liyz.dubbo.api.openapi3.dto.TestDTO;
import com.liyz.dubbo.api.openapi3.result.Result;
import com.liyz.dubbo.api.openapi3.util.BeanUtil;
import com.liyz.dubbo.api.openapi3.vo.TestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/29 9:51
 */
@Tag(name = "接口测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "你好", method = "接口:你好", description = "测试接口你好")
    @GetMapping("/hello")
    public Result<TestVO> hello(@Validated(TestDTO.Hello.class) @ParameterObject TestDTO testDTO) {
        return Result.success(BeanUtil.copyProperties(testDTO, TestVO::new));
    }
}
