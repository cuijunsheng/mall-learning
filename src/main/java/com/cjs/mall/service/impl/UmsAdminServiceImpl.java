package com.cjs.mall.service.impl;

import com.cjs.mall.common.utils.JwtTokenUtils;
import com.cjs.mall.dao.UmsAdminRoleRelationDao;
import com.cjs.mall.mbg.mapper.UmsAdminMapper;
import com.cjs.mall.mbg.model.UmsAdmin;
import com.cjs.mall.mbg.model.UmsAdminExample;
import com.cjs.mall.mbg.model.UmsPermission;
import com.cjs.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-04-03 15:18
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        if (adminList.size() > 0) {
            return adminList.get(0);
        }

        return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = JwtTokenUtils.generateToken(userDetails);
        } catch (AuthenticationException e) {
            logger.info("登录异常:{}", e.getMessage());
        }

        return token;
    }

    @Override
    public List<UmsPermission> listPermissions(Long adminId) {

        return adminRoleRelationDao.listPermissions(adminId);
    }
}
