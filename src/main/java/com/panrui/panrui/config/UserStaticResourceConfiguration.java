package com.panrui.panrui.config;



import com.panrui.panrui.filtter.UserLoginHandlerIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*页面过滤和定向跳转*/
@Configuration
public class UserStaticResourceConfiguration implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("index");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/user/register").setViewName("register");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main.html").setViewName("font/main");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginHandlerIntercepter()).addPathPatterns("/**").excludePathPatterns
                ("/" , "/index" ,"/user/login" ,"/user/main" ,"/user/register" , "/user/activation","*.ico" ,"/user/doRegister" );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/META-INF/resources/static");

    }
}
