package com.liyz.dubbo.service.pdf.svg.chinamap;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Maps;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.service.pdf.exception.RemotePdfServiceException;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.LayerSetting;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.ProvincePathSetting;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.ProvinceTextSetting;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class China {

    private String title;

    private List<LayerSetting> layerSettingList;

    private Map<String, Province> provinceMap = Maps.newHashMap();

    /**
     * 省份单独配置
     */
    private Map<String, CustomerProvinceSetting> customerProvinceSettingMap = Maps.newHashMap();
    ;

    public China(String title,
                 List<LayerSetting> layerSettingList,
                 Map<String, Double> provinceDataMap) {
        this.init(title, layerSettingList, provinceDataMap, null);
    }

    public China(String title,
                 List<LayerSetting> layerSettingList,
                 Map<String, Double> provinceDataMap,
                 Map<String, CustomerProvinceSetting> customerProvinceSettingMap) {
        this.init(title, layerSettingList, provinceDataMap, customerProvinceSettingMap);
    }

    private void init(String title,
                      List<LayerSetting> layerSettingList,
                      Map<String, Double> provinceDataMap,
                      Map<String, CustomerProvinceSetting> customerProvinceSettingMap) {
        if (CollectionUtil.isEmpty(layerSettingList)) {
            throw new RemotePdfServiceException(CommonExceptionCodeEnum.PARAMS_VALIDATED);
        }
        if (!CollectionUtil.isEmpty(customerProvinceSettingMap)) {
            this.customerProvinceSettingMap = customerProvinceSettingMap;
        }
        this.title = title;
        // 排序
        this.layerSettingList = layerSettingList.stream().sorted((e1, e2) -> {
            return e2.getSort() - e1.getSort();
        }).collect(Collectors.toList());
        // 配置 数据对应层级
        this.dataProcess(layerSettingList, provinceDataMap);

    }

    /**
     * 省份数据填充
     *
     * @param layerSettingList
     * @param provinceDataMap
     */
    private void dataProcess(List<LayerSetting> layerSettingList, Map<String, Double> provinceDataMap) {
        // 传入的省份初始化
        provinceDataMap.keySet().forEach(k -> {
            ProvinceEnum provinceEnum = ProvinceEnum.getByName(k);
            if (provinceEnum != null) {
                this.provinceMap.put(provinceEnum.getName(),
                        new Province(provinceEnum.getName(), provinceEnum.getCode(), provinceDataMap.get(k))
                );
            }
        });
        // 剩余未传入的省份初始化
        Arrays.stream(ProvinceEnum.values()).forEach(p -> {
            if (!this.provinceMap.containsKey(p.getName())) {
                this.provinceMap.put(p.getName(),
                        new Province(p.getName(), p.getCode(), 0d)
                );
            }
        });
        // 对象属性补齐
        this.provinceMap.keySet().forEach(k -> {
            Province p = provinceMap.get(k);
            if (customerProvinceSettingMap.containsKey(p.getName())) {
                p.setPath(customerProvinceSettingMap.get(p.getName()).getPath());
                p.setText(customerProvinceSettingMap.get(p.getName()).getText());
            } else {
                layerSettingList.forEach(l -> {
                    if (RangeUtils.inNumRange(p.getVal(), l.getRangExpression())) {
                        p.setPath(l.getPath());
                        p.setText(l.getText());
                        return;
                    }
                });
            }
        });
    }

    @Data
    public static class CustomerProvinceSetting {

        private ProvinceEnum provinceEnum;
        /**
         * 南海诸岛配置
         */
        private ProvincePathSetting path;

        /**
         * 南海诸岛配置
         */
        private ProvinceTextSetting text;

    }


// 测试
//    public static void main(String[] args) {
//        List<LayerSetting> layerSettingList = new ArrayList<>();
//        layerSettingList.add(
//                new LayerSetting(0, "0~100", "(,100]", new ProvincePathSetting("#111111","#111111")));
//        layerSettingList.add(
//                new LayerSetting(0, "100~200", "(100,200]", new ProvincePathSetting("#222222", "#222222")));
//        layerSettingList.add(
//                new LayerSetting(0, "200~300", "(200,]", new ProvincePathSetting("#3333333", "#333333")));
//        Map<String, Double> provinceDataMap = new HashMap<>();
//        provinceDataMap.put("浙江", 200d);
//        provinceDataMap.put("上海", 800d);
//        new China(layerSettingList, provinceDataMap);
//    }
}
