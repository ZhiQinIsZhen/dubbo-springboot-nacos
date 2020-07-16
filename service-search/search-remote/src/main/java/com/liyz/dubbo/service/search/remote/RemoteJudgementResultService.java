package com.liyz.dubbo.service.search.remote;

import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.search.bo.JudgementResultBO;
import com.liyz.dubbo.service.search.bo.JudgementResultPageQueryBO;
import org.springframework.data.domain.PageImpl;

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

    PageImpl<JudgementResultBO> search(PageBaseBO pageBaseBO);

    PageImpl<JudgementResultBO> search(JudgementResultPageQueryBO queryBO);
}
