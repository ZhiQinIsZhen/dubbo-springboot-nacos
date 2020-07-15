package com.liyz.dubbo.service.file.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/25 17:19
 */
@Data
@Table(name = "file_info")
public class FileInfoDO implements Serializable {
    private static final long serialVersionUID = -2688567307516206895L;

    @Id
    @Column(name = "file_key")
    private String fileKey;

    @Column(name = "file_md5")
    private String fileMd5;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_ext")
    private String fileExt;

    @Column(name = "file_type")
    private Integer fileType;

    @Column(name = "is_inactive")
    private Integer isInactive = 0;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
