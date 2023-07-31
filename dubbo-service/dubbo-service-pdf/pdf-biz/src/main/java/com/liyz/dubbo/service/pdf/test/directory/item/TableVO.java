package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用的表格视图对象
 * titleList和dataList的下标必须一一对应，size一致
 *
 * @Description
 * @Author ChenHao
 * @Date 2022/6/9 12:28
 */
@Data
public class TableVO<T> {
    /**
     * 表格标题
     */
    private List<String> titleList;
    /**
     * 数据
     */
    private List<T> dataList;
    /**
     * 表格扩展属性，用于添加额外的描述
     */
    private Map<String, Object> properties = new HashMap<>();

    /**
     * 表格的svg(如果存在)
     */
    private String svg;

    public TableVO() {
    }

    public TableVO(List<String> titleList, List<T> dataList) {
        this.titleList = titleList;
        this.dataList = dataList;
    }

    public void putProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    public boolean noData() {
        return null == dataList || dataList.isEmpty();
    }
}
