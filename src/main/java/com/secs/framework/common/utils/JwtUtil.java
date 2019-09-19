package com.secs.framework.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT token 工具类,提供JWT生成,校验,工作
 *
 * @Date 2019-05-25
 * @Description: TODO
 */
@ConfigurationProperties(prefix = "renren.jwt")
@Component
public class JwtUtil {
//    @Value("${renren.jwt.secret}")
    private String secret;
//    @Value("${renren.jwt.expire}")
    private Long expire;
//    @Value("${renren.jwt.header}")
    private String header;
 
 
    /**
     *
     * 生成JWT token
     * @param userId
     * @return
     */
    public String generateToken(Long userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
 
    }
 
    /**
     *
     * 解析JWT token
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        try {
 
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
 
    /**
     *
     * 校验token是否过期
     * @param expiprationTime
     * @return
     */
    public boolean isTokenExpired(Date expiprationTime){
        return expiprationTime.before(new Date());
    }
    public String getSecret() {
        return secret;
    }
 
    public void setSecret(String secret) {
        this.secret = secret;
    }
 
    public Long getExpire() {
        return expire;
    }
 
    public void setExpire(Long expire) {
        this.expire = expire;
    }
 
    public String getHeader() {
        return header;
    }
 
    public void setHeader(String header) {
        this.header = header;
    }
}
