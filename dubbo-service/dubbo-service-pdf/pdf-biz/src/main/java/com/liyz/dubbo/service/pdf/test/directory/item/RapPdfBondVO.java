package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 债券及信用评级
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/5/30 17:37
 */
@Data
public class RapPdfBondVO implements Serializable {


    private String companyName;


    private List<String> labels;

    private RaProjectBondResponseVO bondInfo;

    /**
     * 1信息描述 债券信息描述？
     */
    private String contentInfo;
    /**
     * 债券信息-债券信息detail
     */
    private List<RaProjectBondDetailResponseVO> bondDetailList;
    /**
     * 2违约描述
     */
    private String contentDefault;

    /**
     * 债券信息-违约信息
     */
    private List<RaProjectBondDefaultResponseVO> defaultList;

    /**
     * 债券信息-信用评级
     */
    private List<RaProjectCreditRatingResponseVO> creditRatingList;
}
