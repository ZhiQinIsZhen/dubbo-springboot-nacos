package com.liyz.dubbo.security.client.bo;

import com.liyz.dubbo.security.core.constant.SecurityConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * 注释:spring权限校验与自身校验融合的一个中间类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:53
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
        sb.append(method).append(SecurityConstant.GRANTED_AUTHORITY_SPLIT).append(url);
        return sb.toString();
    }
}
