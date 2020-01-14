package com.cjs.mall;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-14 17:05
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtTest {

    @Test
    public void test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","zs");
        map.put("password",123);
        String secret = Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
        System.out.println(secret);
    }
}
