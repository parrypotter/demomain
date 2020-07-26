package com.panrui.panrui.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AutoStaticConfiguration  implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("index");
        registry.addViewController("/main.html").setViewName("main");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**").excludePathPatterns
                ("/" , "/index" ,"/user/login" ,"/user/main" ,"/user/register" ,"*.css" , "*.js");
    }
}
