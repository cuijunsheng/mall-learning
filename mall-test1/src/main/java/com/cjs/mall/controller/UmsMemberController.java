package com.cjs.mall.controller;

import com.cjs.mall.common.api.CommonResult;
import com.cjs.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 会员登录注册管理控制器
 * @author: cuijunsheng
 * @date: 2020-01-14 13:16
 **/
@Api(tags = "UmsMemberController",description = "会员注册登录管理")
@RequestMapping("/member")
@Controller
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @GetMapping("getAuthCode")
    @ResponseBody
    public CommonResult getAuthCode(String telephone){

        return memberService.genetateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("verifyAuthCode")
    @ResponseBody
    public CommonResult verifyAuthCode(String telephone,String authCode){

        return memberService.verifyAuthCode(telephone,authCode);
    }
}
