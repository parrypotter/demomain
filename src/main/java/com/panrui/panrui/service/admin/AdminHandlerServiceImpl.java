package com.panrui.panrui.service.admin;

import com.panrui.panrui.bean.Role;
import com.panrui.panrui.handler.AbstractHandler;
import com.panrui.panrui.handler.HandlerContext;
import com.panrui.panrui.mapper.AdminRoleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/*此处改用策略模式
* */
@Service("adminHandlerService")
public class AdminHandlerServiceImpl implements AdminHandlerService {


    @Autowired
    private HandlerContext handlerContext;

    @Resource(name = "adminRoleSet")
    private AdminRoleSet adminRoleSet;

    @Override
    public Map<Boolean,String> upUserRole(Role role) {
        role.setType(String.valueOf(Math.min(adminRoleSet.upUserRole(String.valueOf(role.getUid())), 2)));
        AbstractHandler handler = handlerContext.getInstance(role.getType());
        return handler.abstractHandler(role);
    }

    @Override
    public Map<Boolean,String> downUserRole(Role role) {
        role.setType(String.valueOf(Math.min(adminRoleSet.downUserRole(String.valueOf(role.getUid())), 2)));
        AbstractHandler handler = handlerContext.getInstance(role.getType());
        return handler.abstractHandler(role);
    }

    @Override
    public Map<Boolean,String> setUserEnable(Role role) {
        role.setType(String.valueOf(Math.min(adminRoleSet.setUserEnable(String.valueOf(role.getUid())), 2)));
        AbstractHandler handler = handlerContext.getInstance(role.getType());
        return handler.abstractHandler(role);
    }

    @Override
    public List<Role> showUsers() {
        return adminRoleSet.showUsers();
    }

    @Override
    public List<Role> selectUsers(String username) {
        return adminRoleSet.selectUsers(username);
    }
}
