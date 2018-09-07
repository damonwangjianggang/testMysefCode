/*package com.ylch.ylcy.ylcy.user.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;
@Service
public class MyRealm extends AuthorizingRealm {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    *//**
     * 大坑！，必须重写此方法，不然Shiro会报错
     *//*
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        UserEntity user = userService.getUserEntity(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        
        return simpleAuthorizationInfo;
    }

    *//**
     * 
     *//*
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserEntity userBean = userService.getUserEntity(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPasswd())) {
        	throw new AuthenticationException("TokenExpired");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
*/