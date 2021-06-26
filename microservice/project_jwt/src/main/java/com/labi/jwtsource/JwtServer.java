package com.labi.jwtsource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: microservice
 * @description: 服务端
 * @author: dzp
 * @create: 2021-06-25 10:35
 **/
public class JwtServer {
    /**
     * 服务端产生token
     */
    public static void createToken() {
        /**
         * 1.Header
         */
        Map<String, Object> header = new HashMap<>();
        //类型
        header.put("typ", "jwt");
        //加密算法
        header.put("alg", "HS256");
        /**
         * 2.PayLoad
         * 有好多形式，可以通过map来放，也可以通过推荐的Claims来放
         */
        Claims claims = new DefaultClaims();
        claims.setId("microservice")
                //业务描述
                .setSubject("微服务项目")
                //签发时间
                .setIssuedAt(new Date())
                //有效时间（半小时内有效[当前时间往后推半小时]）
                .setExpiration(new Date(System.currentTimeMillis() + 1800 * 1000))
                .setAudience("自定义声明信息");
        /**
         * 3.Signature
         */
        //盐
        String salt = "labi";
        byte[] saltBase64 = DatatypeConverter.parseBase64Binary(salt);
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        //将盐放入此对象中
        SecretKeySpec secretKey = new SecretKeySpec(saltBase64, hs256.getJcaName());
        /**
         * 通过以上三部分生成token
         */
        String token = Jwts.builder().setHeader(header).setClaims(claims).signWith(hs256, secretKey).compact();
        System.out.println("服务端生成token：" + token);
    }

    public static void main(String[] args) {
        createToken();
    }
}
