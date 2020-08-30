package com.panrui.panrui.service.admin;

import com.panrui.panrui.bean.Role;
import java.util.List;
import java.util.Map;

public interface AdminHandlerService {


    Map<Boolean,String> upUserRole(Role role);

    Map<Boolean,String> downUserRole(Role role);

    Map<Boolean,String> setUserEnable(Role role);

    List<Role> showUsers();

    List<Role> selectUsers(String username);
}
