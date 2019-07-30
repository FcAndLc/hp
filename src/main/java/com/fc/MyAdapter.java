package com.fc;

import com.fc.weixin.intercepter.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptor myInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加token拦截器,排除登录,注册接口
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("","/template/login","/template/register","/error");
        super.addInterceptors(registry);
    }
}
