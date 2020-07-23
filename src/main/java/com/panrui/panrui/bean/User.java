package com.panrui.panrui.bean;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class User implements UserDetails {
    private long uid;
    private String username;
    private String password;
    private String nickname;
    private int userState;//状态只有0或者1，0代表当前用户注册但未激活，1代表已激活状态
    private String email;
    private Date userLoginIn;
    private Date userLoginOut;
    private int userRole;//用户权限，0代表普通用户，1代表管理员用户，1需要超级账号授权，超级账号权限为2
    private boolean userEnable;//用户使用权限，true代表正常使用，false代表异常账户被封控账户；


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUserLoginIn() {
        return userLoginIn;
    }

    public void setUserLoginIn(Date userLoginIn) {
        this.userLoginIn = userLoginIn;
    }

    public Date getUserLoginOut() {
        return userLoginOut;
    }

    public void setUserLoginOut(Date userLoginOut) {
        this.userLoginOut = userLoginOut;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public boolean isUserEnable() {
        return userEnable;
    }

    public void setUserEnable(boolean userEnable) {
        this.userEnable = userEnable;
    }


}