package cn.lkq520.config;

import cn.lkq520.shiro.cache.RedisCacheManager;
import cn.lkq520.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//import cn.lkq520.shiro.realms.CustomerRealm;

/**
 * 用来整合shiro框架相关的配置类
 *
 * @author Luo
 * @version 1.0
 * @date 2020/11/28 11:25
 */
@Configuration
public class ShiroConfig {

    /**
     * 1。创建ShiroFilter
     * anon: 无需认证就可以访问
     * authc: 必须认证了才能访问
     * user: 必须拥有 记住我 功能才能访问
     * perms: 拥有对某个资源的权限才能访问
     * role: 拥有某个角色权限才能访问
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给Filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        //配置系统公共资源
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/login.html", "anon");
        map.put("/user/login", "anon");
        map.put("/user/getCode", "anon");

        map.put("/**", "anon");
        // map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //默认认证页面路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginView");

        return shiroFilterFactoryBean;
    }


    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("realm") Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //给安全管理器设置Realm
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


    //3.创建自定义Realm
    @Bean("realm")
    public Realm realm() {
        CustomerRealm customerRealm = new CustomerRealm();
        //修改凭证效验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true); //开启认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");

        return customerRealm;
    }


    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return rememberMeCookie
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(30*24*60*60);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return rememberMeManager
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    //创建DefaultWebSessionManager类，并DI注入到IOC容器中
    @Bean
    public DefaultWebSessionManager mySessionManager(){
        DefaultWebSessionManager defaultSessionManager = new DefaultWebSessionManager();
        //将sessionIdUrlRewritingEnabled属性设置成false
        defaultSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultSessionManager;
    }
}
