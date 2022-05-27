package com.liyz.dubbo.common.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liyz.dubbo.common.excel.service.ExcelReadRowService;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 注释:通用读列监听器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/27 9:41
 */
@Slf4j
public class CommonReadRowListener<T extends ExcelReadRowService> extends AnalysisEventListener<Map<Integer, String>> {

    @Getter
    private List<T> list = Lists.newArrayList();

    private Map<Integer, T> tMap = Maps.newHashMap();

    private Class<T> clazz;

    public CommonReadRowListener(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SneakyThrows
    @Override
    public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
        log.info("row:{}, data:{}", analysisContext.readRowHolder().getRowIndex(), JsonMapperUtil.toJSONString(map));
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() != 0) {
                if (!tMap.containsKey(entry.getKey())) {
                    T o = clazz.newInstance();
                    tMap.put(entry.getKey(), o);
                    list.add(o);
                }
                ExcelReadRowService readRowService = tMap.get(entry.getKey());
                readRowService.invoke(map.get(0), entry.getValue());
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("excel 解析完成");
    }
}
