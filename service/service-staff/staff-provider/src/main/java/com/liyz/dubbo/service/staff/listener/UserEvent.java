package com.liyz.dubbo.service.staff.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/11/24 10:29
 */
@Getter
@Setter
public class UserEvent extends ApplicationEvent {

    private Long userId;

    public UserEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
}
