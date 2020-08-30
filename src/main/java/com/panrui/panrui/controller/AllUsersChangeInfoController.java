package com.panrui.panrui.controller;

import com.panrui.panrui.bean.User;
import com.panrui.panrui.service.info.UserInfoChangeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class AllUsersChangeInfoController {

    private Map<Integer,String> returnMap = new HashMap<>();

    @Resource(name = "userInfoChangeService")
    private UserInfoChangeService userInfoChangeService;


    @PostMapping("/allUsers/changeNickname")
    private Map<String ,String> changeUserNickname(HttpServletRequest httpServletRequest, User user , Map<String,String> mapMessage){
        returnMap = userInfoChangeService.updateUserNickName(user.getNickname(), (String) httpServletRequest.getSession().getAttribute("userSession"));
        if(returnMap.containsKey(1)){
            mapMessage.put("msg",returnMap.get(1)+"重置成功");
        }else {
            mapMessage.put("msg","重置失败或者数据库有问题");
        }
        return mapMessage;
    }

    @PostMapping("/allUsers/changePassword")
    private Map<String ,String> changeUserPassword(HttpServletRequest httpServletRequest, User user , Map<String,String> mapMessage){
        returnMap = userInfoChangeService.updateUserNickName(user.getPassword(), (String) httpServletRequest.getSession().getAttribute("userSession"));
        if(returnMap.containsKey(1)){
            mapMessage.put("msg",returnMap.get(1)+"重置成功");
        }else {
            mapMessage.put("msg","重置失败或者数据库有问题");
        }
        return mapMessage;
    }
}
