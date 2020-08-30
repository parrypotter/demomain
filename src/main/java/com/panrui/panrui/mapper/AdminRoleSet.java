package com.panrui.panrui.mapper;

import com.panrui.panrui.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("adminRoleSet")
@Mapper
public interface AdminRoleSet {

    int upUserRole(@Param(value = "uid")String uid);

    int downUserRole(@Param(value = "uid")String uid);

    int setUserEnable(@Param(value = "uid")String uid);

    List<Role> showUsers();

    List<Role> selectUsers(@Param(value = "username")String username);


}
