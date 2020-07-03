package com.liyz.dubbo.api.web.dto.page;

import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/30 11:03
 */
@Data
public class PageBaseDTO implements Serializable {
    private static final long serialVersionUID = 2460443394517199183L;

    @Min(value = 0, message = "页码大于等于0", groups = {Page.class})
    private Integer pageNum = 1;

    @Min(value = 0, message = "每页查询数量大于等于0", groups = {Page.class})
    private Integer pageSize = 10;

    public interface Page {}
}
