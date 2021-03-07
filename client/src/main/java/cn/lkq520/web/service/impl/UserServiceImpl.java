package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.User;
import cn.lkq520.utils.SaltUtils;
import cn.lkq520.web.mapper.UserMapper;
import cn.lkq520.web.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired private UserMapper userMapper;

    @Override
    public User findRolesByAccount(String account) {
        return userMapper.findRolesByAccount(account);
    }

    @Override
    public User findByAccount(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void register(User user) {
        //明文密码进行MD5+salt+hash散列
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //加盐加密密码且散列1024此
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userMapper.insert(user);
    }
}
