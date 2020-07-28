package com.panrui.panrui.controller;

import com.panrui.panrui.bean.User;
import com.panrui.panrui.mapper.UserLoginRepository;
import com.panrui.panrui.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.SystemHealth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class AutoLoginController {

    @Autowired
    private UserLoginRepository userRepository;

    @Resource(name="userLoginService")
    private UserLoginService userLoginService;

    @GetMapping(value = "/user/register")
    public String register(){
        return "register";
    }

    @PostMapping(value = "/user/login")
    public String LoginIn(User user, Map<String,Object> mapMessage , HttpSession httpSession){
        User loginInState = userLoginService.doLoginIn(user.getUsername(),user.getPassword());
        System.out.println("断点1");
        if(loginInState!=null){
            httpSession.setAttribute("loginUser" , loginInState);
            return "redirect:/main.html";
        }else {
            mapMessage.put("msg","输出有误，请重新登录！");
            return "index";
        }

    }
}
