package com.cjs.mall.service;

import com.cjs.mall.mbg.model.UmsAdmin;
import com.cjs.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * @description: 后台管理员用户Service
 * @author: cuijunsheng
 * @date: 2020-04-03 15:18
 **/
public interface UmsAdminService {

    /**
     * 根据用户名获取后台用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 后台用户登录
     * @param username
     * @param password
     * @return 返回jwt生成的token
     */
    String login(String username,String password);

    /**
     * 获取对应后台用户的权限列表
     * @param adminId
     * @return
     */
    List<UmsPermission> listPermissions(Long adminId);
}
