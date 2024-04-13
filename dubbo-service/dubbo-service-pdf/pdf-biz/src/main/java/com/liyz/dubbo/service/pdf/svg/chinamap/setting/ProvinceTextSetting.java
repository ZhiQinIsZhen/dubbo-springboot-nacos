package com.liyz.dubbo.service.pdf.svg.chinamap.setting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceTextSetting implements Serializable {

    private String fillColor = "#4F4F69";
    private String fontSize = "13";
    private String fontWeight = "normal";
}
