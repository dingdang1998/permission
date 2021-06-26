package com.labi.jwtsource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * @program: microservice
 * @description: 客户端【携带token访问服务端】
 * @author: dzp
 * @create: 2021-06-25 11:01
 **/
public class JwtClient {
    public static void visitServer(){
        String token = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtaWNyb3NlcnZpY2UiLCJzdWIiOiLlvq7mnI3liqHpobnnm64iLCJpYXQiOjE2MjQ1ODk4OTEsImV4cCI6MTYyNDU5MTY5MSwiYXVkIjoi6Ieq5a6a5LmJ5aOw5piO5L-h5oGvIn0.4yGOn9LMoVLD0_OwtaeFH69nghjB4huSNNXxgHCVIZ8";
        String salt = "labi" ;

        //获取jsw对象,jws就包含了所有的服务端校验信息
        Jws<Claims> jws = Jwts.parser().setSigningKey(salt).parseClaimsJws(token);
        System.out.println(jws);

        Claims claims = jws.getBody();
        System.out.println("header:"+jws.getHeader());
        System.out.println("payload:"+claims.getId());
        System.out.println("payload:"+claims.getSubject());
        System.out.println("payload:"+claims.getIssuedAt());
        System.out.println("payload:"+claims.getExpiration());
        System.out.println("payload:"+claims.getAudience());
        System.out.println("signature:"+jws.getSignature());
        //tokens是由【header.payload.signature】组成，截取第三部分看与获取的是否相同
        System.out.println(jws.getSignature().equals(token.split("\\.")[2]));
    }

    public static void main(String[] args) {
        visitServer();
    }
}
