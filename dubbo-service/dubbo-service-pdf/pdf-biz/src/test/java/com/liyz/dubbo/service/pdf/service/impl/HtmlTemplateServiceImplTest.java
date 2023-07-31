package com.liyz.dubbo.service.pdf.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyz.dubbo.service.pdf.service.Html2PdfService;
import com.liyz.dubbo.service.pdf.service.HtmlTemplateService;
import com.liyz.dubbo.service.pdf.test.data.TestBO;
import com.liyz.dubbo.service.pdf.test.directory.TestDirectoryBO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:16
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HtmlTemplateServiceImplTest {

    @Resource
    private HtmlTemplateService htmlTemplateService;
    @Resource
    private Html2PdfService html2PdfService;

    @Test
    @SneakyThrows
    public void test() {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
        TestBO testBO = objectMapper.readValue(new ClassPathResource("templates/RapPdfVO.json").getInputStream(), TestBO.class);
        final Context context = new Context();
        context.setVariable("ctx", testBO);
        context.setVariable("toc", new TestDirectoryBO());
        String html = htmlTemplateService.processResource("main_project_city_investment", context);
        log.info("html : {}", html);
    }

    @Test
    @SneakyThrows
    public void test2() {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
        TestBO testBO = objectMapper.readValue(new ClassPathResource("templates/RapPdfVO.json").getInputStream(), TestBO.class);
        final Context context = new Context();
        context.setVariable("ctx", testBO);
        context.setVariable("toc", new TestDirectoryBO());
        String html = htmlTemplateService.processResource("main_project_city_investment", context);
        html2PdfService.html2Pdf(html, context);
    }
}