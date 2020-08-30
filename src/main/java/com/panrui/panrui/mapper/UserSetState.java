package com.panrui.panrui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userSetState")
@Mapper
public interface UserSetState {
    /*设置用户激活*/
    int setUserState(@Param(value = "username")String username);

    /*激活前检查用户是否遭到封禁*/
    String checkUserEnable(@Param(value = "username")String username);

    /*通过userSession里的username反email再次发验证码*/
    String getEmailByUserName(@Param(value = "username")String username);

}
