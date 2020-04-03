package com.cjs.mall.service.impl;

import com.cjs.mall.mbg.mapper.UmsAdminPermissionRelationMapper;
import com.cjs.mall.mbg.model.UmsAdmin;
import com.cjs.mall.mbg.model.UmsPermission;
import com.cjs.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-04-03 15:18
 **/
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public List<UmsPermission> listPermissions(Long adminId) {
        return null;
    }
}
