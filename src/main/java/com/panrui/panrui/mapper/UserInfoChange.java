package com.panrui.panrui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userInfoChange")
@Mapper
public interface UserInfoChange {


    /*通过前端session里获取username*/
    int updateUserNickName(@Param(value = "username")String username , @Param(value = "nickname")String nickname);


    int updateUserPassword(@Param(value = "username")String username , @Param(value = "password")String password);

}
