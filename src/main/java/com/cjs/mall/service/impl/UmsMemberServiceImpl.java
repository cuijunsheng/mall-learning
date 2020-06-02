package com.cjs.mall.service.impl;

import com.cjs.mall.common.web.CommonResult;
import com.cjs.mall.service.RedisService;
import com.cjs.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-14 13:25
 **/
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.authCode.prefix}")
    private String REDIS_KEY_AUTHCODE_PREFIX;

    @Value("${redis.key.authCode.expire}")
    private Long REDIS_KEY_AUTHCODE_EXPRIE;

    @Override
    public CommonResult genetateAuthCode(String telephone) {

        StringBuffer authCode = new StringBuffer();
        Random random = new Random();
        int limit = 6;
        for (int i = 0; i < limit; i++) {

            authCode.append(random.nextInt(10));
        }

        redisService.set(REDIS_KEY_AUTHCODE_PREFIX + telephone, authCode.toString());
        redisService.expire(REDIS_KEY_AUTHCODE_PREFIX + telephone, REDIS_KEY_AUTHCODE_EXPRIE);

        return CommonResult.success("验证码获取成功！", authCode.toString());
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {

            return CommonResult.success("请输入验证码");
        }

        String value = redisService.get(REDIS_KEY_AUTHCODE_PREFIX + telephone);
        if (authCode.equals(value)) {

            return CommonResult.success("验证成功！", null);
        } else {

            return CommonResult.failed("验证码不正确！");
        }
    }
}
