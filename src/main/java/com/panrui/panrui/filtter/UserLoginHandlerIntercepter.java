package com.panrui.panrui.filtter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*设置登录前页面访问权限*/
public class UserLoginHandlerIntercepter implements HandlerInterceptor {
    private static final String[] IGNORE_URI ={"/index","/login"};
    @Override
    //目标方法执行之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean resultPath = false;
        Object reqUser = request.getSession().getAttribute("userSession");
        for (String urlStr:IGNORE_URI) {
            if ((request.getRequestURI()).contains(urlStr)) {
                resultPath = true;
                break;
            }
        }
        if(!resultPath){
            if(reqUser!=null){
                resultPath = true;
            }else {
                try {
                    request.setAttribute("msg","没有权限访问，请先登录！");
                    request.getRequestDispatcher("/").forward(request,response);
                    resultPath = false;
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }
            }
        }



        return resultPath;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
