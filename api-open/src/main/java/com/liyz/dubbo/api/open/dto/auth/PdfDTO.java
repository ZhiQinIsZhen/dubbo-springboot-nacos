package com.liyz.dubbo.api.open.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/12/3 10:31
 */
@Data
public class PdfDTO implements Serializable {
    private static final long serialVersionUID = 1782675857438324020L;


    @NotBlank(groups = {Pdf.class}, message = "公司名不能为空")
    @ApiModelProperty(value = "公司名", example = "仟金顶")
    private String companyName;

    @NotBlank(groups = {Pdf.class}, message = "详情页html不能为空")
    @ApiModelProperty(value = "详情页html", example = "详情页html")
    private String mainStr;

    public interface Pdf {
    }
}
