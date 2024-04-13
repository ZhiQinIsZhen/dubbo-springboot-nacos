package com.liyz.dubbo.service.pdf.svg.chinamap;

import com.google.common.collect.Maps;
import com.liyz.dubbo.service.pdf.svg.SvgChart;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.LayerSetting;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.ProvincePathSetting;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.ProvinceTextSetting;
import com.liyz.dubbo.service.pdf.utils.ThymeleafUtils;
import lombok.Data;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 创建中国地图
 */
@Data
public class ChinaMapChart implements SvgChart<China> {

    /**
     * 模板引擎
     */
    private SpringTemplateEngine thymeleafEngine;

    private String templateUrl = null;

    /**
     * 获取项目报告
     *
     * @param thymeleafEngine
     * @param provinceDataMap
     */
    public static String geChinaMap(SpringTemplateEngine thymeleafEngine, Map<String, Double> provinceDataMap) {
        // 配置数据分层规则
        List<LayerSetting> layerSettingList = new ArrayList<>();
        layerSettingList.add(
                new LayerSetting(0, "0~20", "(,20]", new ProvincePathSetting("#FFFFFF", "#F0F5FF")));
        layerSettingList.add(
                new LayerSetting(2, "20~40", "(20,40]", new ProvincePathSetting("#FFFFFF", "#CDDCFE")));
        layerSettingList.add(
                new LayerSetting(3, "40~60", "(40,60]", new ProvincePathSetting("#FFFFFF", "#A0B6FB")));
        layerSettingList.add(
                new LayerSetting(4, "60~80", "(60,80]", new ProvincePathSetting("#FFFFFF", "#6884F5")));
        layerSettingList.add(
                new LayerSetting(5, "80~100", "(80,100]", new ProvincePathSetting("#FFFFFF", "#2970F6")));
        layerSettingList.add(
                new LayerSetting(6, "100~120", "(100,120]", new ProvincePathSetting("#FFFFFF", "#1761ED")));
        layerSettingList.add(
                new LayerSetting(7, ">120", "(120,]", new ProvincePathSetting("#FFFFFF", "#155CE3")));

        // 对南海诸岛 单独配置
        Map<String, China.CustomerProvinceSetting> customerProvinceSettingMap = Maps.newHashMap();
        China.CustomerProvinceSetting customerProvinceSetting = new China.CustomerProvinceSetting();
        customerProvinceSetting.setPath(new ProvincePathSetting("#F0F5FF", "#1E90FF"));
        customerProvinceSetting.setText(new ProvinceTextSetting());
        customerProvinceSettingMap.put(ProvinceEnum.nhzd.getName(), customerProvinceSetting);
        China china = new China("中国地图", layerSettingList, provinceDataMap, customerProvinceSettingMap);

        ChinaMapChart chart = new ChinaMapChart();
        chart.setTemplateUrl("chinamap");
        chart.setThymeleafEngine(thymeleafEngine);
        String html = chart.createSvg(china);
        return html;
    }

    public static void main(String[] args) {

        Map<String, Double> provinceDataMap = new HashMap<>();
        provinceDataMap.put("浙江", 80d);
        provinceDataMap.put("上海", 40d);
        provinceDataMap.put("新疆", 100d);

        String html = geChinaMap(ThymeleafUtils.newSpringTemplateEngine(), provinceDataMap);

        System.out.println(html);
    }

    @Override
    public String createSvg(China data) {
        final Context context = new Context();
        context.setVariable("china", data);
        String html = thymeleafEngine.process(templateUrl, context);
        // 移除多余标签
        // <html xmlns:th="http://www.thymeleaf.org">
        int biginIndex = html.indexOf(">", 1) + 1;
        // </html>
        int endIndex = html.indexOf("</html>");
        return html.substring(biginIndex, endIndex);
    }
}
