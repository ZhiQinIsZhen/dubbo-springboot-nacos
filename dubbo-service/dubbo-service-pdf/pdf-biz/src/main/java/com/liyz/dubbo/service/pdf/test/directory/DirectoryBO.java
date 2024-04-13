package com.liyz.dubbo.service.pdf.test.directory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:40
 */
@Getter
@Setter
public class DirectoryBO implements Serializable {
    private static final long serialVersionUID = -3976462703922478873L;

    /**
     * 虚拟根目录
     */
    @JsonIgnore
    protected Dire root;

    public DirectoryBO() {
        // 根目录
        root = new Dire(null, "root", 1, 0, "root");
    }
}
