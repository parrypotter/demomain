package com.panrui.panrui.controller;

import com.panrui.panrui.bean.User;
import com.panrui.panrui.service.UserLoginService;
import com.panrui.panrui.service.UserRegisterService;
import com.panrui.panrui.service.UserResetEmailService;
import com.panrui.panrui.service.UserSetStateService;
import com.panrui.panrui.utils.EmailVerification;
import com.panrui.panrui.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserLoginAndRegisterController {

    private static Map<String , Object> UserResultMap = new HashMap<>();

    @Autowired
    private EmailVerification emailVerification;

    @Autowired
    private MD5Utils md5Utils;

    @Resource(name="userLoginService")
    private UserLoginService userLoginService;

    @Resource(name="userRegisterService")
    private UserRegisterService userRegisterService;

    @Resource(name = "userSetStateService")
    private UserSetStateService userSetStateService;

    @Resource(name = "userResetEmailService")
    private UserResetEmailService userResetEmailService;

    /*登录验证*/
    @PostMapping(value = "/user/login")
    private String LoginIn(User user, Map<String,Object> mapMessage , HttpSession httpSession){
        User loginInState = userLoginService.doLoginIn(user.getUsername(),user.getPassword());

        if(loginInState!=null){
             if(userLoginService.doUserState(loginInState.getUsername())){
                    httpSession.setAttribute("userSession" , loginInState.getUsername());
                    return "redirect:/main.html";
                }else{
                    if(emailVerification.sendEmailVerification(loginInState.getEmail())){
                        httpSession.setAttribute("userSession" , loginInState.getUsername());
                        mapMessage.put("msgRe","当前用户尚未激活，验证码已发送，请通过邮箱获取激活码！");
                        UserResultMap = emailVerification.resultMap;
                    }else{
                        mapMessage.put("msgReSq","当前未激活账户提供的邮箱不存在，请提供可用的邮箱！");
                        /*邮箱不存在时，应该怎么操作？
                        * 输入新邮箱再次进行验证*/
                        httpSession.setAttribute("userSession", user.getUsername());
                    }
                    /*发送邮箱验证码*/
                    return "register";
                }
        }else {
            mapMessage.put("msg","用户名或者密码输出有误，请重新登录！");
            return "index";
        }

    }
    /*注册-->配置用户*/
    @PostMapping(value = "/user/doRegister")
    private String RegisterIn(User user, Map<String,Object> mapMessage ,HttpSession httpSession){
        if(user.getUsername().equals(userRegisterService.doRegisterUserNameCheck(user.getUsername()))){
            mapMessage.put("msg","当前用户已存在，请重新注册！");
        }else {
            if(userRegisterService.doRegisterIn(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail())!=null){
                /*发送邮箱验证码*/
                if(emailVerification.sendEmailVerification(user.getEmail())){
                    mapMessage.put("msgRe","当前用户尚未激活，验证码已发送，请通过邮箱获取激活码！");
                    httpSession.setAttribute("userSession", user.getUsername());
                    UserResultMap = emailVerification.resultMap;
                }else{
                    mapMessage.put("msgReSq","当前未激活账户提供的邮箱不存在，请提供可用的邮箱！");
                    httpSession.setAttribute("userSession", user.getUsername());
                    /*邮箱不存在时*/

                }
            }else {
                mapMessage.put("msg","注册失败，请重新注册！");
            }
        }
        return "register";

    }

    /*注册-->激活用户*/
    @PostMapping(value = "/user/activation")
    private String UserActivation(HttpServletRequest httpServletRequest, Map<String,Object> mapMessage, HttpSession httpSession){

        String identity = httpServletRequest.getParameter("identity");
        String identityCode = md5Utils.setCode(identity);//生成MD5编码
        if(UserResultMap.size()==0){
            return "user/register";
        }
        String requestHash = UserResultMap.get("hash").toString();
        String requestTamp = UserResultMap.get("tamp").toString();
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendars = Calendar.getInstance();
        String currentTime = simple.format(calendars.getTime());
        if(requestTamp.compareTo(currentTime)>=0){
            if(requestHash.equals(identityCode)){
                /*设置激活函数*/
                if(userSetStateService.setUserState((String) httpServletRequest.getSession().getAttribute("userSession"))){
                    if(userSetStateService.checkUserEnable((String) httpServletRequest.getSession().getAttribute("userSession"))){
                        return "redirect:/main.html";
                    }else {
                        mapMessage.put("msg" , "你的账户已被封禁，请联系管理员解除！");
                        return "index";
                    }
                }

            }else{
                mapMessage.put("msgRe" , "验证码不正确，请重新输入!");
                return "register";
            }
        }else{
            /*输入验证码超时*/
            /*怎么再次发送验证码？？？
            * --》需要通过userSession里的username反查出email进行再次发送
            * */
            if(emailVerification.sendEmailVerification(userSetStateService.getEmailByUserName(
                    (String) httpServletRequest.getSession().getAttribute("userSession")
            ))){
                mapMessage.put("msgRe" , "验证码输入超时，当前验证码已重发，请重新输入！");
                httpSession.setAttribute("userSession", (String) httpServletRequest.getSession().getAttribute("userSession"));
                UserResultMap = emailVerification.resultMap;
            }else{
                mapMessage.put("msgReSq","当前未激活账户提供的邮箱不存在，请提供可用的邮箱！");
                /*邮箱不存在时*/


            }
            return "register";
        }

    return null;
    }

    @PostMapping("/user/resetEmail")
    private String UserResetEmail(HttpServletRequest httpServletRequest ,Map<String,Object> mapMessage){

        String resetEmail =  httpServletRequest.getParameter("resetEmail");
        String username = (String) httpServletRequest.getSession().getAttribute("userSession");
        if(userResetEmailService.checkUserEmailIsSimilar(username,resetEmail)){
            mapMessage.put("msgReSq","当前未激活账户提供的邮箱不存在，请提供可用的邮箱！");
            return "register";
        }else{
            if(userResetEmailService.updateUserEmail(username,resetEmail)==1){
                mapMessage.put("msg","当前用户设置新邮箱成功，请登录！");
                return "index";
            }
            if(userResetEmailService.updateUserEmail(username,resetEmail)==0){
                mapMessage.put("msg","当前用户无法设置新邮箱，请联系管理员！");
                return "index";
            }
            if(userResetEmailService.updateUserEmail(username,resetEmail)>1){
                mapMessage.put("msg","当前数据库存在重复数据，请联系管理员查看！");
                return "index";
            }
        }

        return null;
    }
}
