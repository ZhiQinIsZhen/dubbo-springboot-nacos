package com.liyz.dubbo.common.security.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 14:36
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthGrantedAuthority implements GrantedAuthority {

    private String url;

    private String method;

    @Override
    public String getAuthority() {
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(";").append(url);
        return sb.toString();
    }
}
