package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/3 11:13
 */
public class FinalBeneficiary {

    /**
     * 最终受益人
     */
    @Data
    public static class FinalBeneficiaryPerson implements Serializable {
        /**
         * 最终受益人名称
         */
        private String name;

        /**
         * 最终受益人持股比例
         */
        private String percent;
        /**
         * 收益路径图
         */
        private List<BeneficiaryPerson> pathMap;

        public void setDataByRaProjectHumanHoldingResponse(RaProjectHumanHoldingResponseVO in) {
            if (in == null) {
                return;
            }

            this.setName(in.getName());
            this.setPercent(in.getPercent());
            List<List<RaProjectHumanHoldingLineEntryResponseVO>> lineItems = in.getLineItems();
            if (null != lineItems && lineItems.size() > 0) {
                ArrayList<BeneficiaryPerson> pathMap = new ArrayList<>();
                this.setPathMap(pathMap);
                // 循环二维数组
                for (List<RaProjectHumanHoldingLineEntryResponseVO> human : lineItems) {
                    if (human == null || human.isEmpty() || human.size() < 3) {
                        continue;
                    }
                    int size = human.size();
                    /* a&1 = 0 偶数,   a&1 = 1 奇数 */
                    // 按现有的数据结构设计，二维里的一维数组的长度必须保证是奇数
                    int len = (size & 1) == 0 ? (size - 1) : size;
                    BeneficiaryPerson bp = new BeneficiaryPerson();
                    // 第一个元素必须是名称
                    bp.setName(human.get(0).getValue());
                    ArrayList<BeneficiaryItem> items = new ArrayList<>();
                    bp.setItems(items);
                    for (int i = 1; i < len; i += 2) {
                        RaProjectHumanHoldingLineEntryResponseVO current = human.get(i);
                        RaProjectHumanHoldingLineEntryResponseVO next = human.get(i + 1);
                        BeneficiaryItem item = new BeneficiaryItem();
                        item.setCtrl(current.getValue());
                        item.setCtrlIsPercent(Objects.nonNull(current.getValue()) ? current.getValue().contains("%") : Boolean.FALSE);
                        item.setCorpName(next.getValue());
                        items.add(item);
                    }
                    pathMap.add(bp);
                }
            }

        }
    }

    /**
     *
     */
    @Data
    public static class BeneficiaryPerson implements Serializable {
        /**
         * 受益人名称
         */
        private String name;
        /**
         * 受益路径
         */
        private List<BeneficiaryItem> items;
    }

    @Data
    public static class BeneficiaryItem implements Serializable {

        /**
         * 控制权，可能是一个控股比率，也可能是一个文本（法定代表人、股东等）
         */
        private String ctrl;
        /**
         * 控制权是否是一个比率
         */
        private Boolean ctrlIsPercent;
        /**
         * 企业名称
         */
        private String corpName;
    }
}
