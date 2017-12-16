package com.qingcheng.code.common.configration.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

/**
 * Created by liguohua on 2017/5/4.
 */
public class JwtTokenService {
    public static String SECRETKEY = "secretkey4qingchengxueyuan2017";
    public static String TOKENPREFIX = "Bearer ";

    /**
     * 用于解析JwtToken
     */
    public static Object parseJwtToken(String token) {
        if (!token.startsWith("Bearer ")) {
            throw new RuntimeException("is not Bearer token");
        }
        token = token.trim().substring(7);

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
        } catch (final SignatureException e) {
            new RuntimeException("token解析错误");
        }
        return claims;
    }

    /**
     * 用于生成JwtToken
     */
    public static String createJwtToken(String subject, long TTLMillis, String claimBeanName, Object claimBean) {
        //添加Token生成时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //添加Token过期时间
        long expMillis = nowMillis + TTLMillis;
        Date exp = new Date(expMillis);
        return TOKENPREFIX + Jwts.builder().setSubject(subject)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(now)
                .setExpiration(exp)
                .setNotBefore(now)
                .claim(claimBeanName, claimBean)
                //.signWith(SignatureAlgorithm.HS256, secretkey).compact();
                .signWith(SignatureAlgorithm.HS512, SECRETKEY).compact();
    }
}
