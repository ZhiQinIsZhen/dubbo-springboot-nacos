package com.liyz.dubbo.service.staff.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.staff.dao.StaffInfoMapper;
import com.liyz.dubbo.service.staff.model.StaffInfoDO;
import com.liyz.dubbo.service.staff.service.StaffInfoService;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/15 14:03
 */
@Service
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfoDO> implements StaffInfoService {
}
