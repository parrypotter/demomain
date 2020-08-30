package com.panrui.panrui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userRegisterRepository")
@Mapper
public interface UserRegisterRepository {
    Integer doRegisterIn(
                      @Param(value = "uid")Integer uid ,
                      @Param(value = "username")String username,
                      @Param(value = "password")String password,
                      @Param(value = "nickname")String nickname,
                      @Param(value = "email")String email);

    String findByUserName(@Param(value = "username")String username);


  /*  //激活函数
    void setUserState(@Param(value = "userState")String userState);

    //权限设置函数和用户风控设置函数，应该放在管理员处，此处为临时存放
    void setUserRole(@Param(value = "userRole")String userRole);

    void setUserEnable( @Param(value = "userEnable")String userEnable);*/
}
