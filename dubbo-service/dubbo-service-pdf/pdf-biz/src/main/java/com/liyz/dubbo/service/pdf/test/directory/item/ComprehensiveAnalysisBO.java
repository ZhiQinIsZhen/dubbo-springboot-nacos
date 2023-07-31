package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 11:12
 */
@Getter
@Setter
public class ComprehensiveAnalysisBO implements Serializable {
    private static final long serialVersionUID = 2759046302553467948L;

    /**
     * 类型(0:舆情情况;1:历史类型;2:基本情况;3:经营情况;4:财务情况;5:诉讼情况;6:项目情况;7:其他情况;8:总结;9:重点关注)
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 关键词
     */
    private String keyword;
}
