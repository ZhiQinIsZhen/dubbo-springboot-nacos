package com.liyz.dubbo.service.pdf.test.directory.item;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/28 10:19
 */
@Data
public class IndicatorStructureTableVO extends IndicatorStructureResponseVO implements Serializable {


    // 财报类别 1：年报；2：中报 ；3：一季度；4:三季度
    public static Function<Integer, String> financeTypeConvert = type -> {
        if (null == type) {
            return "未知";
        }
        return 1 == type ? "年报" : 2 == type ? "中报" : 3 == type ? "一季度" : "三季度";
    };


    /**
     *
     */
    private List<FinanceObjectVOIndicator<FinanceDataResponseVO>> lsFinanceTables;
    /**
     *
     */
    private List<FinanceObjectVOIndicator<TableVO<OperationYearDetailResponseVO>>> lsOperationTables;
    /**
     *
     */
    private List<FinanceObjectVOIndicator<TableVO<RepaymentYearDetailResponseVO>>> lsRepaymentTables;
    /**
     *
     */
    private List<FinanceObjectVOIndicator<String>> lsDescribeTables;
    /**
     *
     */
    private List<FinanceObjectVOIndicator<TableVO<GrowthYearDetailResponseVO>>> lsGrowthTables;


    public void putTable(IndicatorStructureResponseVO in) {

        if (in == null) {
            return;
        }
        BeanUtils.copyProperties(in, this);

        initTables();
    }


    /**
     * @param vo
     * @param yearDetailList
     * @param <D>
     * @return
     */
    private <D extends YearDetailBaseResponseVO> FinanceObjectVOIndicator<TableVO<D>> toYearDetailData(IndicatorDataResponseVO vo, List<D> yearDetailList) {
        List<D> list = yearDetailList;
        List<String> titleList = new ArrayList<>();
        List<D> dataList = new ArrayList<>();
        // 最多4列处理
        for (int i = 0, len = list.size(); i < 4; i++) {
            if (i < len) {
                D o = list.get(i);
                titleList.add(o.getYear() + financeTypeConvert.apply(o.getType()));
                dataList.add(o);
            } else {
                //
                titleList.add("--");
                dataList.add(null);
            }
        }

        TableVO<D> tableVO = new TableVO(titleList, dataList);
        HashMap<String, Integer> riskTypes = vo.getRiskTypes();
        if (!CollectionUtils.isEmpty(riskTypes)) {
            for (Map.Entry<String, Integer> entry : riskTypes.entrySet()) {
                tableVO.putProperty(entry.getKey(), entry.getValue());
            }
        }
        Integer riskQuan = vo.getRiskQuan();
        if (Objects.nonNull(riskQuan)) {
            tableVO.putProperty("RISKQUAN", riskQuan);
        }
        //
        FinanceObjectVOIndicator<TableVO<D>> financeTableVO = new FinanceObjectVOIndicator<>(vo.getType(), vo.getName(), tableVO);

        return financeTableVO;
    }

    /**
     *
     */
    private void initTables() {
        initlsFinanceTables();
        initlsOperationTables();
        initlsRepaymentTables();
        initlsDescribeTables();
        initlsGrowthTables();

    }

    /**
     *
     */
    private void initlsGrowthTables() {
        List<GrowthDataResponseVO> ls = this.getLsGrowthData();
        List<FinanceObjectVOIndicator<TableVO<GrowthYearDetailResponseVO>>> lss = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ls)) {
            ls.forEach(p -> lss.add(toYearDetailData(p, p.getYearDetailList())));
        }
        this.lsGrowthTables = lss;
    }

    /**
     *
     */
    private void initlsRepaymentTables() {
        List<RepaymentDataResponseVO> ls = this.getLsRepaymentData();
        List<FinanceObjectVOIndicator<TableVO<RepaymentYearDetailResponseVO>>> lss = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ls)) {
            ls.forEach(p -> lss.add(toYearDetailData(p, p.getYearDetailList())));
        }
        this.lsRepaymentTables = lss;
    }

    /**
     *
     */
    private void initlsOperationTables() {
        List<OperationDataResponseVO> ls = this.getLsOperationData();
        List<FinanceObjectVOIndicator<TableVO<OperationYearDetailResponseVO>>> lss = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ls)) {
            ls.forEach(p -> lss.add(toYearDetailData(p, p.getYearDetailList())));
        }
        this.lsOperationTables = lss;
    }

    /**
     *
     */
    private void initlsDescribeTables() {

        List<DescribeDataResponseVO> ls = this.getLsDescribeData();
        List<FinanceObjectVOIndicator<String>> lss = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ls)) {
            ls.forEach(p -> lss.add(new FinanceObjectVOIndicator(p.getType(), p.getName(), p.getDescribeInfo())));
        }
        this.lsDescribeTables = lss;
    }

    /**
     *
     */
    private void initlsFinanceTables() {
        List<FinanceDataResponseVO> ls = this.getLsFinanceData();
        List<FinanceObjectVOIndicator<FinanceDataResponseVO>> lss = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(ls)) {
            ls.forEach(p -> lss.add(new FinanceObjectVOIndicator(p.getType(), p.getName(), p)));
        }
        this.lsFinanceTables = lss;
    }
}
