package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 企业股权结构
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CorpShareholdingStructureBO implements Serializable {
    private static final long serialVersionUID = 6069978816868983893L;
    /**
     * 工商登记
     */
    private List<ProjectCompanyHolderBO> shareholderList;
    /**
     * 最新公示（十大股东）
     */
    private List<ProjectShareHolderBO> top10Shareholders;
}
