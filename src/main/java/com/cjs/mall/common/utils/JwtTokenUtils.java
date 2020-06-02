package com.cjs.mall.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: JwtToken生成的工具类
 *  JWT token的格式：header.payload.signature
 *  header的格式（算法、token的类型）：
 *  {"alg": "HS512","typ": "JWT"}
 *  payload的格式（用户名、创建时间、生成时间）：
 *  {"sub":"wang","created":1489079981393,"exp":1489684781}
 *  signature的生成算法：
 *  HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @author: cuijunsheng
 * @date: 2020-01-14 16:58
 **/
public class JwtTokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Value("${jwt.secret}")
    private static String secret;
    @Value("${jwt.expireTime}")
    private static Long expireTime;

    /**
     * 生成token
     *
     * @param userDetails
     * @return
     */
    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("sub:", userDetails.getUsername());
        claims.put("created:", new Date());

        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(generateExpireTime())
                .compact();
    }

    /**
     * 生成token过期时间
     *
     * @return
     */
    private static Date generateExpireTime() {

        return new Date(System.currentTimeMillis() + expireTime * 1000);
    }

    /**
     * 根据token获取登录用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();

        } catch (Exception e) {

        }

        return username;
    }

    /**
     * 根据token获取有效载荷
     *
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        } catch (Exception e) {
            logger.info("jwt验证失败：{}", token);
        }

        return claims;
    }

    /**
     * 从token中获取过期时间
     *
     * @param token
     * @return
     */
    private static Date getExpireTimeFromToken(String token) {

        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否已过期
     *
     * @param token
     * @return
     */
    private static boolean isTokenExpired(String token) {

        Date expireTime = getExpireTimeFromToken(token);
        return expireTime.before(new Date());
    }

    /**
     * token是否可以被刷新
     *
     * @param token
     * @return
     */
    public static boolean canRefresh(String token) {

        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public static String refreshToken(String token) {

        Claims claims = getClaimsFromToken(token);
        return generateToken(claims);
    }

    /**
     * 验证token是否还有效
     *
     * @param token
     * @param userDetails
     * @return
     */
    public static boolean validateToken(String token, UserDetails userDetails) {

        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
