package com.panrui.panrui.controller;


import com.panrui.panrui.bean.Role;
import com.panrui.panrui.bean.User;
import com.panrui.panrui.service.admin.AdminHandlerService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Controller
public class AdminHandlerController {

    /*管理员处理范围：
    * 1、更改用户权限--》超管升级普通用户为管理员或者降级，普通管理员可以封禁用户
    * 2、设置用户--》封禁用户
    * （*）3、查看详细测试数据*/

    /*修改用户权限,管理员升降*/

    @Resource(name = "adminHandlerService")
    private AdminHandlerService adminHandlerService;

    /*设置上调函数和下调函数*/
    @PostMapping("/admin/resetUpUserRole")
    /*当前前段遗留问题——》无法通过td向后端提交数据，后端数据验证已完成，策略模式可正常进行*/
    private Map<Boolean,String> ResetUpUserRole(Role role,@RequestParam("uid") String uid){
        role.setUid(Integer.parseInt(uid));
        return adminHandlerService.upUserRole(role);
    }

    @PostMapping("/admin/resetDownUserRole")
    private Map<Boolean,String> ResetDownUserRole(Role role){
        return adminHandlerService.downUserRole(role);
    }

    @PostMapping("/admin/resetUserEnable")
    private Map<Boolean,String> ResetUserEnable(Role role){
        return adminHandlerService.setUserEnable(role);
    }

    @PostMapping("/admin/showUser")
    private String showUsers(Model modelMessage){
         modelMessage.addAttribute("msgUsers",adminHandlerService.showUsers());
         return "font/model";
    }


    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,List<Role>> searchUser(User user , Map<String,List<Role>> mapGet){
        //JSONObject jsonObject = JSONObject.fromObject(user.getUsername());
        if(adminHandlerService.selectUsers(user.getUsername())!=null){
            mapGet.put("true",adminHandlerService.selectUsers(user.getUsername()));
        }else {
            mapGet.put("false",null);
        }
        System.out.println(mapGet);
        return mapGet;
    }
    /*展示所有用户实验数据数据，但是具体要展示哪些呢？？？*/



}
