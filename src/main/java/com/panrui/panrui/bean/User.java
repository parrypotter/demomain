package com.panrui.panrui.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


//@Entity代表当前类是一个实体类
@Entity
//@Table用来对应数据库的表名
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer uid;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    private String nickname;
    private Integer userState;//状态只有0或者1，0代表当前用户注册但未激活，1代表已激活状态
    private String email;
    private Date userLoginIn;
    private Date userLoginOut;
    private Integer userRole;//用户权限，0代表普通用户，1代表管理员用户，1需要超级账号授权，超级账号权限为2
    private Boolean userEnable;//用户使用权限，true代表正常使用，false代表异常账户被封控账户；



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

    /*public User(Integer uid, String username, String password, String nickname, Integer userState, String email, Date userLoginIn, Date userLoginOut, Integer userRole, Boolean userEnable) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.userState = userState;
        this.email = email;
        this.userLoginIn = userLoginIn;
        this.userLoginOut = userLoginOut;
        this.userRole = userRole;
        this.userEnable = userEnable;
    }*/

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
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

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Boolean isUserEnable() {
        return userEnable;
    }

    public void setUserEnable(boolean userEnable) {
        this.userEnable = userEnable;
    }
}