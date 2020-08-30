package com.panrui.panrui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userResetEmail")
@Mapper
public interface UserResetEmail {

    /*检查再次输入的邮箱是否相同*/
    String checkUserEmailIsSimilar(@Param(value = "username")String username);

    /*重新设置邮箱*/
    int updateUserEmail(@Param(value = "username")String username , @Param(value = "email")String email);

}
