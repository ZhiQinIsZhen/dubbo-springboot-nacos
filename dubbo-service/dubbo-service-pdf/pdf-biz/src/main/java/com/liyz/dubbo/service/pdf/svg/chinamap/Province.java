package com.liyz.dubbo.service.pdf.svg.chinamap;

import com.liyz.dubbo.service.pdf.svg.chinamap.setting.ProvincePathSetting;
import com.liyz.dubbo.service.pdf.svg.chinamap.setting.ProvinceTextSetting;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 省
 */
@Setter
@Getter
public class Province implements Serializable {

    /**
     * 省名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    private Double val;

    private ProvincePathSetting path;

    private ProvinceTextSetting text;

    public Province(String name, String code, Double val) {

        this.name = name;
        this.code = code;
        this.val = val;
    }

}
