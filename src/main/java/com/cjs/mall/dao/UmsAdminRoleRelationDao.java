package com.cjs.mall.dao;

import com.cjs.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-04-07 15:52
 **/
public interface UmsAdminRoleRelationDao {

    /**
     * 获取对应后台用户的权限列表
     * @param adminId
     * @return
     */
    List<UmsPermission> listPermissions(Long adminId);
}
