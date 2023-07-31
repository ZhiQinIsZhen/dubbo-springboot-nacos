package com.liyz.dubbo.service.pdf.test.directory.item;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.service.util.BeanUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/3 11:00
 */
@Data
@NoArgsConstructor
public class RaProjectSocialStaffInfoStructure extends RaProjectSocialStaffInfoResponseVO implements Serializable {


    //社保人数
    private TableVO<RaProjectSocialStaffCountResponseVO> countListTable;

    private List<List<RaProjectSocialStaffCountResponseVO>> ls;

    public RaProjectSocialStaffInfoStructure(RaProjectSocialStaffInfoResponseVO socialStaffInfo) {

        if (Objects.isNull(socialStaffInfo)) {
            return;
        }
        this.put(socialStaffInfo);
    }

//    /**
//     * 生成的svg图片
//     */
//    private String svg;
//
//    private String svgAll;
//    /**
//     * 图片上传的key
//     */
//    private String svgFileKey;

    public void put(RaProjectSocialStaffInfoResponseVO in) {

        if (in == null) {
            return;
        }
        BeanUtil.copyProperties(in, this.getClass());

        inItTables();
    }

    private void inItTables() {

        if (!CollectionUtils.isEmpty(super.getCountList())) {
            countListTable = new TableVO<>();
            countListTable.setDataList(super.getCountList());
            splitLs();
        }
    }

    private void splitLs() {
        List<List<RaProjectSocialStaffCountResponseVO>> ls = Lists.newArrayList();
        List<RaProjectSocialStaffCountResponseVO> integers = super.getCountList();

        int sublistSize = 7;
        for (int i = 0; i < integers.size(); i += sublistSize) {
            List<RaProjectSocialStaffCountResponseVO> sublist = integers.subList(
                    i, Math.min(i + sublistSize, integers.size())
            );

            ls.add(sublist);
        }
        this.setLs(ls);
    }

}
