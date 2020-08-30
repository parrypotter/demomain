package com.panrui.panrui.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
//@Entity代表当前类是一个实体类
@Entity
//@Table用来对应数据库的表名
@Table(name = "user")
public class User  {
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
    private Integer userRole;//用户权限，0代表普通用户，1代表管理员用户，1需要超级账号授权，超级账号权限为2
    private Integer userEnable;//用户使用权限，0代表正常使用，1代表异常账户被封控账户；

}