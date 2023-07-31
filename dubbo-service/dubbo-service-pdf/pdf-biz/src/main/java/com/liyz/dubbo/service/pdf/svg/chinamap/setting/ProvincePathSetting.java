package com.liyz.dubbo.service.pdf.svg.chinamap.setting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 省
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvincePathSetting implements Serializable {

    private String strokeColor = "#FFFFFF";
    private String fillColor = "#F0F5FF";

}
