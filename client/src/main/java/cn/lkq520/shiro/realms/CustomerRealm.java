package cn.lkq520.shiro.realms;


import cn.lkq520.pojo.Role;
import cn.lkq520.pojo.User;
import cn.lkq520.shiro.salt.MyByteSource;
import cn.lkq520.web.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2020/11/28 11:39
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进去授权信息操作");
        String account = (String) principals.getPrimaryPrincipal();
        User user = userService.findRolesByAccount(account);
        if (!ObjectUtils.isEmpty(user.getRoleList())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : user.getRoleList()) {
                simpleAuthorizationInfo.addRole(role.getName());
            }
            return simpleAuthorizationInfo;
        }
        /*System.out.println("调用权限验证： "+user);
        if(user.getName().equals("luo")){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRole("user");

            authorizationInfo.addStringPermission("user:query:*");
            authorizationInfo.addStringPermission("user:update:*");

            return authorizationInfo;
        }*/
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("进去认证信息操作");
        //获取身份信息
        String principal = (String) token.getPrincipal();
        //在工厂中获取service对象
        User user = userService.findByAccount(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), new MyByteSource(user.getSalt()), this.getName());
        }
        return null;
    }
}
