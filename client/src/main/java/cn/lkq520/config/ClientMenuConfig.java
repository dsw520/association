package cn.lkq520.config;

import cn.lkq520.web.controller.interceptor.MenuInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 返回客户端的菜单数据
 *
 * @author Luo
 * @version 1.0
 * @date 2021/3/14 16:40
 */
@Configuration
public class ClientMenuConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new MenuInterceptor());
        registration.addPathPatterns("/*")
                .addPathPatterns("/user/registerView")
                .addPathPatterns("/news/**")
                .addPathPatterns("/items/**")
                .addPathPatterns("/club/**");
    }
}
