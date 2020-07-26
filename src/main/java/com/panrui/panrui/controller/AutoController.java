package com.panrui.panrui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class AutoController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "/user/register")
    public String register(){
        return "register";
    }

    @PostMapping(value = "/user/login")
    public String LoginIn(@RequestParam(value = "username")String username,
                          @RequestParam(value = "password")String password,
                          Map<String,Object> mapMessage , HttpSession httpSession){
        if(!StringUtils.isEmpty(username)&&"123".equals(password)){
            httpSession.setAttribute("loginUser" , username);
            return "redirect:/main.html";
        }else {
            mapMessage.put("msg","输出有误，请重新登录！");
            return "index";
        }

    }
}
