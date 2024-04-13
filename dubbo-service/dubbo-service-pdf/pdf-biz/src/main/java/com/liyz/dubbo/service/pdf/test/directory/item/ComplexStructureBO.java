package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:11
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplexStructureBO implements Serializable {
    private static final long serialVersionUID = -5012431253125605589L;

    private List<ComprehensiveAnalysisBO> complexList;

    private List<ComplexItem> complexItems;

    private String focus;

    public void putComplexStructure(List<ComprehensiveAnalysisBO> complexList) {

        this.complexList = complexList;

        putComplexItemsAndFocus(complexList);
    }

    private void putComplexItemsAndFocus(List<ComprehensiveAnalysisBO> complexList) {

        List<ComplexItem> complexItems = new ArrayList<>();

        for (ComprehensiveAnalysisBO vo : complexList) {
            Integer type = vo.getType();
            String content = vo.getContent();
            String keyword = vo.getKeyword();
            if (Integer.valueOf(9).equals(type)) {
                this.focus = content;
                continue;
            }
            boolean show = true;
            String typeName = correspondingTypeName(type);
            if (Integer.valueOf(1).equals(type)) {
                show = false;
            }
            List<String> keywords = new ArrayList<>();
            if (Objects.nonNull(keyword) && keyword.length() > 0) {
                keywords = Arrays.asList(keyword.split(","));
            }
            ComplexItem complexItem = new ComplexItem(show, type, typeName, content, keywords);
            complexItems.add(complexItem);
        }

        this.complexItems = complexItems;
    }

    /**
     * @param type
     * @return
     */
    private String correspondingTypeName(Integer type) {
        if (Integer.valueOf(0).equals(type)) {
            return "舆情情况";
        }
        if (Integer.valueOf(1).equals(type)) {
            return "历史类型";
        }
        if (Integer.valueOf(2).equals(type)) {
            return "基本情况";
        }
        if (Integer.valueOf(3).equals(type)) {
            return "经营情况";
        }
        if (Integer.valueOf(4).equals(type)) {
            return "财务情况";
        }
        if (Integer.valueOf(5).equals(type)) {
            return "诉讼情况";
        }
        if (Integer.valueOf(6).equals(type)) {
            return "项目情况";
        }
        if (Integer.valueOf(7).equals(type)) {
            return "其他情况";
        }
        if (Integer.valueOf(8).equals(type)) {
            return "总结";
        }
        if (Integer.valueOf(9).equals(type)) {
            return "重点关注";
        }
        return "";
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ComplexItem implements Serializable {

        private boolean show;

        private Integer type;

        private String typeName;

        private String content;

        private List<String> keywords;
    }
}
