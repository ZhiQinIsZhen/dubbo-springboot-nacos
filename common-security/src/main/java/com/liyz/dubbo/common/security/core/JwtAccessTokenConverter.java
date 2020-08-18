package com.liyz.dubbo.common.security.core;

import com.liyz.dubbo.common.security.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 注释:jwt解析
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 10:37
 */
@Component
public class JwtAccessTokenConverter {

    @Value("${jwt.signing.key:liyz}")
    private String signingKey;
    @Value("${jwt.expiration:604800}")
    private Long expiration;

    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_USER_ID = "userId";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    /**
     * 获取token 用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(final String token) {
        String username = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (Objects.nonNull(claims)) {
                username = claims.getSubject();
            }
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取token创建时间
     *
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(final String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获取token过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(final String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 获取设备信息
     *
     * @param token
     * @return
     */
    public String getAudienceFromToken(final String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    /**
     * token是否失效
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(final String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成token
     *
     * @param userDetails
     * @param device
     * @param date
     * @param userId
     * @return
     */
    public String generateToken(final UserDetails userDetails, final Device device, final Date date, final Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.AUDIENCE, generateAudience(device));
        claims.put(CLAIM_KEY_CREATED, date);
        claims.put(CLAIM_KEY_USER_ID, userId);
        return generateToken(claims);
    }

    private String generateAudience(final Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    private String generateToken(final Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public String refreshToken(final String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 生成token失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 验证token
     *
     * @param token
     * @param userDetails
     * @param device
     * @return
     */
    public Integer validateToken(final String token, final UserDetails userDetails, final Device device) {
        LoginUserDetails user = (LoginUserDetails) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);

        Date lastPasswordResetDate;
        if (device.isMobile()) {
            lastPasswordResetDate = user.getLastAppPasswordResetDate();
        } else {
            lastPasswordResetDate = user.getLastWebPasswordResetDate();
        }
        return username.equals(user.getUsername()) ? !isTokenExpired(token)
                ? !isCreatedBeforeLastPasswordReset(created, lastPasswordResetDate)
                ? SecurityConstant.VALIDATE_TOKEN_SUCCESS : SecurityConstant.VALIDATE_TOKEN_FAIL_OTHER_LOGIN
                : SecurityConstant.VALIDATE_TOKEN_FAIL_EXPIRED : SecurityConstant.VALIDATE_TOKEN_FAIL_USERNAME;

    }

    /**
     * 创建时间是否在上次设备登陆时间之前
     *
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(final Date created, final Date lastPasswordReset) {
        return (created == null || lastPasswordReset == null || created.before(lastPasswordReset));
    }
}
