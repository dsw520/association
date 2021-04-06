package cn.lkq520.web.controller.interceptor;

import cn.lkq520.pojo.Nav;
import cn.lkq520.utils.ApplicationContextUtils;
import cn.lkq520.web.service.NavService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2021/3/14 16:36
 */
public class MenuInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        NavService navService = (NavService) ApplicationContextUtils.getBean("navServiceImpl");
        List<Nav> navList = navService.getNavWithItem();
        if (mav != null){
            mav.addObject("navList",navList);
        }
    }
}
