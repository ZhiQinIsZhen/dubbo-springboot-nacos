package com.liyz.dubbo.service.member.bus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/19 17:27
 */
@Getter
@Setter
@AllArgsConstructor
public class UserEvent implements Serializable {
    private static final long serialVersionUID = 3797155081281160733L;

    private String userName;
}
