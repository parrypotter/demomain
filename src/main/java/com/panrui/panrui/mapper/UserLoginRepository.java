package com.panrui.panrui.mapper;

import com.panrui.panrui.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value="userLoginRepository")
@Mapper
public interface UserLoginRepository {
    User doLoginIn(@Param(value = "username")String username,
                   @Param(value = "password")String password);
}
