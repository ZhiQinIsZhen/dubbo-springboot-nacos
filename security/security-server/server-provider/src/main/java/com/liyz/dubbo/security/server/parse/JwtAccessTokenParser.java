package com.liyz.dubbo.security.server.parse;

import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.security.core.user.ClaimDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * 注释:jwt解析
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 13:56
 */
@Service
public class JwtAccessTokenParser {

    @Value("${jwt.signing.key:liyz}")
    private String signingKey;
    @Value("${jwt.expiration:604800}")
    private Long expiration;

    private final static String CLAIM_DEVICE = "device";
    private final static String CLAIM_USER_ID = "userId";

    /**
     * 获取token 用户名
     *
     * @param token
     * @return
     */
    public String getUsernameByToken(final String token) {
        String username = null;
        final Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
            username = claims.getSubject();
        }
        return username;
    }

    /**
     * 获取token创建时间
     *
     * @param token
     * @return
     */
    public Date getCreationByToken(final String token) {
        Date created = null;
        final Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
            created = claims.getIssuedAt();
        }
        return created;
    }

    /**
     * 获取token过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationByToken(final String token) {
        Date expiration = null;
        final Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
            expiration = claims.getExpiration();
        }
        return expiration;
    }

    /**
     * 获取设备信息
     *
     * @param token
     * @return
     */
    public Integer getDeviceByToken(final String token) {
        Integer device = null;
        final Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
            device = claims.get(CLAIM_DEVICE, Integer.class);
        }
        return device;
    }

    /**
     * 获取认证信息
     *
     * @param token
     * @return
     */
    public ClaimDetail getDetailByToken(final String token) {
        ClaimDetail claimDetail = null;
        final Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
            claimDetail = new ClaimDetail();
            claimDetail.setUsername(claims.getSubject());
            claimDetail.setUserId(claims.get(CLAIM_USER_ID, Long.class));
            claimDetail.setDevice(claims.get(CLAIM_DEVICE, Integer.class));
            claimDetail.setAudience(claims.getAudience());
            claimDetail.setCreation(claims.getIssuedAt());
            claimDetail.setExpiration(claims.getExpiration());
        }
        return claimDetail;
    }

    /**
     * token是否失效
     *
     * @param token
     * @return
     */
    public Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationByToken(token);
        return Objects.nonNull(expiration) ? expiration.before(new Date()) : Boolean.TRUE;
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
     * @param claimDetail
     * @return
     */
    public String generateToken(final ClaimDetail claimDetail) {
        Claims claims = Jwts.claims()
                .setSubject(claimDetail.getUsername())
                .setIssuedAt(claimDetail.getCreation())
                .setAudience(claimDetail.getAudience());
        claims.put(CLAIM_DEVICE, claimDetail.getDevice());
        claims.put(CLAIM_USER_ID, claimDetail.getUserId());
        return generateToken(claims);
    }

    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    private String generateToken(final Claims claims) {
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
        String refreshedToken = null;
        final Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
            claims.setIssuedAt(DateUtil.currentDate());
            refreshedToken = generateToken(claims);
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
}
