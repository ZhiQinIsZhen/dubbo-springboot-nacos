package com.liyz.dubbo.service.search.remote;

import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.service.search.bo.JudgementResultBO;
import com.liyz.dubbo.service.search.bo.JudgementResultPageQueryBO;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/16 15:19
 */
public interface RemoteJudgementResultService {

    int save(JudgementResultBO judgementResultBO);

    int save(List<JudgementResultBO> list);

    void delete(String id);

    void delete(List<String> ids);

    JudgementResultBO getById(String id);

    Page<JudgementResultBO> search(PageBaseBO pageBaseBO);

    Page<JudgementResultBO> search(JudgementResultPageQueryBO queryBO);
}
