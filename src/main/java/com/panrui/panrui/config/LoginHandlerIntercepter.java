package com.panrui.panrui.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*设置登录前页面访问权限*/
public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    //目标方法执行之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object reqUser = request.getSession().getAttribute("loginUser");
        if(reqUser!=null){
            return true;
        }else{
            request.setAttribute("msg","没有权限访问，请先登录！");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
