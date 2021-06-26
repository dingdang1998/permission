package com.labi.entity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: microservice
 * @description: jwt工具类
 * @author: dzp
 * @create: 2021-06-25 14:06
 **/
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtil {
    private String key;
    private long ttl;

    /**
     * 服务端生成jwt-token的方法
     *
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJwt(String id, String subject, String roles) {
        Date now = new Date();
        long nowMillis = System.currentTimeMillis();
        return Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + ttl))
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("roles", roles).compact();
    }

    /**
     * 客户端jwt-token时进行的校验方法
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
