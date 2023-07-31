package com.liyz.dubbo.service.pdf.svg.chinamap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
public enum ProvinceEnum {
    // 23个省
    qh("青海", "qh"),
    sc("四川", "sc"),
    hlj("黑龙江", "hlj"),
    gs("甘肃", "gs"),
    yn("云南", "yn"),
    hun("湖南", "hun"),
    sx("陕西", "sx"),
    gd("广东", "gd"),
    jl("吉林", "jl"),
    heb("河北", "heb"),
    hub("湖北", "hub"),
    gz("贵州", "gz"),
    sd("山东", "sd"),
    shanx("山西", "shanx"),
    hen("河南", "hen"),
    ln("辽宁", "ln"),
    ah("安徽", "ah"),
    fj("福建", "fj"),
    zj("浙江", "zj"),
    js("江苏", "js"),
    jx("江西", "jx"),
    hn("海南", "hn"),
    tw("台湾", "tw"),
    // 5个自治区
    nmg("内蒙古", "nmg"),
    gx("广西", "gx"),
    xz("西藏", "xz"),
    xj("新疆", "xj"),
    nx("宁夏", "nx"),
    // 4个直辖市
    bj("北京", "bj"),
    tj("天津", "tj"),
    sh("上海", "sh"),
    cq("重庆", "cq"),
    // 2个 特别行政区
    xg("香港", "xg"),
    am("澳门", "am"),
    nhzd("南海诸岛", "nhzd");

    private String name;

    private String code;

    public static ProvinceEnum getByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        ProvinceEnum[] list = ProvinceEnum.values();
        for (ProvinceEnum item : list) {
            if (name.indexOf(item.getName()) != -1) {
                return item;
            }
        }
        return null;
    }

}
