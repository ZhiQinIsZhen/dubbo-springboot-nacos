package com.liyz.dubbo.service.pdf.pdf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ChenHao
 * @Date 2022/7/11 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeaderParams {
    /**
     * 公司名称
     */
    private String corpName;
    /**
     * 公司logo路径
     */
    private String corpLogo;
    /**
     * 报告编号
     */
    private String reportNo;
    /**
     * 报告名称
     */
    private String reportName;
    /**
     * 页眉背景图片路径
     */
    private String bkImgClassPath;
    /**
     * 开始有页眉的起始页
     */
    private Integer startPageNumber = 1;

}
