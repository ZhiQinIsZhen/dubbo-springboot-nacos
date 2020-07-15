package com.liyz.dubbo.service.file.remote;

import com.liyz.dubbo.service.file.bo.FileInfoBO;
import com.liyz.dubbo.service.file.bo.FileInfoListBO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 17:23
 */
public interface RemoteFileService {

    List<String> upload(@NotNull FileInfoListBO fileInfoListBO);

    FileInfoBO download(@NotNull FileInfoBO fileInfoBO);

    boolean delete(@NotNull FileInfoBO fileInfoBO);

    String update(@NotNull FileInfoBO fileInfoBO);
}
