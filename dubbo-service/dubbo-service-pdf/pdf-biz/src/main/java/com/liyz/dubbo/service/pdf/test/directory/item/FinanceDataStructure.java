package com.liyz.dubbo.service.pdf.test.directory.item;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/25 15:07
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 财务数据
 */
@Data
@Slf4j
@NoArgsConstructor
public class FinanceDataStructure implements Serializable {


    private RaProjectFinancialExtResponseVO financialExt;

    //——————————————————————————————————————————————————————————————————————————————
    private List<RaProjectTycFinancialResponseVO> tycFinancial;

    private List<RaProjectExcelHkFinancialResponseVO> excelHkFinancial;

    private List<RaProjectExcelFinancialResponseVO> excelFinancial;

    private List<RaProjectWdFinancialResponseVO> wdFinancial;

    private List<RaProjectWdHkFinancialResponseVO> wdHkFinancial;

    //——————————————————————————————————————————————————————————————————————————————
    private List<RaProjectTycProfitResponseVO> tycProfit;

    private List<RaProjectExcelHkProfitResponseVO> excelHkProfit;

    private List<RaProjectExcelProfitResponseVO> excelProfit;

    private List<RaProjectWdProfitResponseVO> wdProfit;

    private List<RaProjectWdHkProfitResponseVO> wdHkProfit;
    //——————————————————————————————————————————————————————————————————————————————
    private List<RaProjectTycCashFlowResponseVO> tycCashFlow;

    private List<RaProjectExcelHkCashFlowResponseVO> excelHkCashFlow;

    private List<RaProjectExcelCashFlowResponseVO> excelCashFlow;

    private List<RaProjectWdCashFlowResponseVO> wdCashFlow;

    private List<RaProjectWdHkCashFlowResponseVO> wdHkCashFlow;


    /**
     * table
     */
    //——————————————————————————————————————————————————————————————————————————————
    private TableVO<RaProjectTycFinancialResponseVO> tycFinancialTableVO;

    private TableVO<RaProjectExcelHkFinancialResponseVO> excelHkFinancialTableVO;

    private TableVO<RaProjectExcelFinancialResponseVO> excelFinancialTableVO;

    private TableVO<RaProjectWdFinancialResponseVO> wdFinancialTableVO;

    private TableVO<RaProjectWdHkFinancialResponseVO> wdHkFinancialTableVO;
    //——————————————————————————————————————————————————————————————————————————————
    private TableVO<RaProjectTycProfitResponseVO> tycProfitTableVO;

    private TableVO<RaProjectExcelHkProfitResponseVO> excelHkProfitTableVO;

    private TableVO<RaProjectExcelProfitResponseVO> excelProfitTableVO;

    private TableVO<RaProjectWdProfitResponseVO> wdProfitTableVO;

    private TableVO<RaProjectWdHkProfitResponseVO> wdHkProfitTableVO;
    //——————————————————————————————————————————————————————————————————————————————

    private TableVO<RaProjectTycCashFlowResponseVO> tycCashFlowTableVO;

    private TableVO<RaProjectExcelHkCashFlowResponseVO> excelHkCashFlowTableVO;

    private TableVO<RaProjectExcelCashFlowResponseVO> excelCashFlowTableVO;

    private TableVO<RaProjectWdHkCashFlowResponseVO> wdHkCashFlowTableVO;

    private TableVO<RaProjectWdCashFlowResponseVO> wdCashFlowTableVO;
    //——————————————————————————————————————————————————————————————————————————————

    public FinanceDataStructure(Object in) {
        if (in == null) {
            return;
        }
        BeanUtils.copyProperties(in, this);

        this.initStructure();
    }

    /**
     * 资产负债表
     *
     * @return
     */
    @JsonIgnore
    public Object getFinancialSheets() {
        Object table = null;
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版
        if (financialExt == null) {
            return table;
        }
        Integer dataType = financialExt.getIsThirdFinancial();
        if (Objects.isNull(dataType)) {
            return table;
        }
        switch (dataType) {
            case 0:
                table = excelHkFinancialTableVO;
                break;
            case 1:
                table = tycFinancialTableVO;
                break;
            case 2:
                table = excelFinancialTableVO;
                break;
            case 3:
                table = wdHkFinancialTableVO;
                break;
            case 4:
                table = wdFinancialTableVO;
                break;
        }
        return table;
    }

    /**
     * 利润表
     *
     * @return
     */
    @JsonIgnore
    public Object getProfitSheets() {
        Object table = null;
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版
        if (financialExt == null) {
            return table;
        }
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版
        Integer dataType = financialExt.getIsThirdProfit();
        if (Objects.isNull(dataType)) {
            return table;
        }
        switch (dataType) {
            case 0:
                table = excelHkProfitTableVO;
                break;
            case 1:
                table = tycProfitTableVO;
                break;
            case 2:
                table = excelProfitTableVO;
                break;
            case 3:
                table = wdHkProfitTableVO;
                break;
            case 4:
                table = wdProfitTableVO;
                break;
        }
        return table;
    }

    /**
     * 现金流量表
     *
     * @return
     */
    @JsonIgnore
    public Object getCashFlowSheets() {
        Object table = null;
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版
        if (financialExt == null) {
            return table;
        }
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版
        Integer dataType = financialExt.getIsThirdCashFlow();
        if (Objects.isNull(dataType)) {
            return table;
        }
        switch (dataType) {
            case 0:
                table = excelHkCashFlowTableVO;
                break;
            case 1:
                table = tycCashFlowTableVO;
                break;
            case 2:
                table = excelCashFlowTableVO;
                break;
            case 3:
                table = wdHkCashFlowTableVO;
                break;
            case 4:
                table = wdCashFlowTableVO;
                break;
        }
        return table;
    }


    /**
     *
     */
    private void initStructure() {
        initFinancial();
        initProfit();
        initCashFlow();
    }

    private void initCashFlow() {
        if (financialExt == null) {
            return;
        }
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版"
        Integer dataType = financialExt.getIsThirdCashFlow();
        tycCashFlowTableVO = toTableVO(dataType, tycCashFlow, "showYear");
        excelHkCashFlowTableVO = toTableVO(dataType, excelHkCashFlow, "year");
        excelCashFlowTableVO = toTableVO(dataType, excelCashFlow, "year");
        wdHkCashFlowTableVO = toTableVO(dataType, wdHkCashFlow, "showYear");
        wdCashFlowTableVO = toTableVO(dataType, wdCashFlow, "showYear");
    }

    private void initProfit() {
        if (financialExt == null) {
            return;
        }
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版"
        Integer dataType = financialExt.getIsThirdProfit();
        tycProfitTableVO = toTableVO(dataType, tycProfit, "showYear");
        excelHkProfitTableVO = toTableVO(dataType, excelHkProfit, "year");
        excelProfitTableVO = toTableVO(dataType, excelProfit, "year");
        wdHkProfitTableVO = toTableVO(dataType, wdHkProfit, "showYear");
        wdProfitTableVO = toTableVO(dataType, wdProfit, "showYear");

    }

    private void initFinancial() {
        if (financialExt == null) {
            return;
        }
        //0：港股；1：三方；2：A股；3：万德港股；4：万德国版"
        Integer dataType = financialExt.getIsThirdFinancial();
        tycFinancialTableVO = toTableVO(dataType, tycFinancial, "showYear");
        excelHkFinancialTableVO = toTableVO(dataType, excelHkFinancial, "year");
        excelFinancialTableVO = toTableVO(dataType, excelFinancial, "year");
        wdHkFinancialTableVO = toTableVO(dataType, wdHkFinancial, "showYear");
        wdFinancialTableVO = toTableVO(dataType, wdFinancial, "showYear");

    }


    @SneakyThrows
    public <T> TableVO<T> toTableVO(Integer dataType,
                                    List<T> list, String field) {
        TableVO<T> table = new TableVO<>();
        if (null == list || list.isEmpty()) {
            return table;
        }
        List<String> titleList = new ArrayList<>();
        List<T> dataList = new ArrayList<>();
        table.putProperty("dataType", dataType);
        table.setTitleList(titleList);
        table.setDataList(dataList);
        for (int i = 0, len = list.size(); i < 4; i++) {
            if (i < len) {
                T obj = list.get(i);
                Field declaredField = obj.getClass().getDeclaredField(field);
                declaredField.setAccessible(Boolean.TRUE);
                String title = (String) declaredField.get(obj);
                dataList.add(obj);
                titleList.add(title);
            } else {
                titleList.add("--");
                dataList.add(null);
            }
        }
        return table;
    }
}
