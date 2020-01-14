package com.cjs.mall.service;

import com.cjs.mall.common.CommonResult;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-14 13:19
 **/
public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    CommonResult genetateAuthCode(String telephone);

    /**
     * 判断验证码和手机号是否匹配
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone,String authCode);

}
