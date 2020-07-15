package com.liyz.dubbo.service.file.service;

import com.liyz.dubbo.common.dao.abs.AbstractService;
import com.liyz.dubbo.service.file.model.FileInfoDO;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 17:33
 */
@Service
public class FileInfoService extends AbstractService<FileInfoDO> {

    public FileInfoDO getByMd5(String fileMd5) {
        FileInfoDO param = new FileInfoDO();
        param.setFileMd5(fileMd5);
        param.setIsInactive(null);
        return mapper.selectOne(param);
    }
}
