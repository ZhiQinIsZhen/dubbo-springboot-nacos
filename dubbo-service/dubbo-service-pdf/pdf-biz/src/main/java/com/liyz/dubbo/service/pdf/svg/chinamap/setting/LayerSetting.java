package com.liyz.dubbo.service.pdf.svg.chinamap.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 地图数据分层配置
 */
@Data
public class LayerSetting implements Serializable {

    /**
     * 排序字段
     */
    private int sort;

    /**
     * 层级描述
     * 例如：0~20
     */
    private String desc;

    /**
     * 判断区间表达式
     */
    private String rangExpression;

    /**
     * 层级对应的色值
     * svg分层之后对应省份的显示颜色
     */
    private ProvincePathSetting path;

    private ProvinceTextSetting text = new ProvinceTextSetting();

    public LayerSetting(int sort, String desc, String rangExpression, ProvincePathSetting path) {
        this.sort = sort;
        this.desc = desc;
        this.rangExpression = rangExpression;
        this.path = path;
    }
}
