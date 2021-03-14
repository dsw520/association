package cn.lkq520.web.controller;

import cn.lkq520.pojo.User;
import cn.lkq520.web.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2020/11/28 12:38
 */
@Controller
@RequestMapping("user")
public class UserController {

    /**
     * session中的验证码
     */
    private String SHIRO_VERIFY_SESSION = "verifySessionCode";

    @Autowired private UserService userService;
    @Autowired private DefaultKaptcha defaultKaptcha;

    @RequestMapping("loginView")
    public String loginView(){
        System.out.println("跳转至login的html页面");
        return "login-1";
    }

    /**
     * 获取验证码
     * @param response
     */
    @RequestMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SHIRO_VERIFY_SESSION,createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 用来处理身份认证
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, String code, boolean rememberMe,HttpServletRequest request) {
        //比较验证码
        String codes = request.getParameter(SHIRO_VERIFY_SESSION);
        System.out.println(codes);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        System.out.println(rememberMe);
        subject.isRemembered();
        try {
            if(codes.equalsIgnoreCase(code)){
                subject.login(new UsernamePasswordToken(username, password));
                return "redirect:/index.html";
            }else{
                throw new RuntimeException("验证码出错!");
            }
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "redirect:/login.html";
    }

    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/login.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.html";
    }

    @RequestMapping("registerView")
    public String registerView(){
        return "register";
    }
}
