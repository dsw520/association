package cn.lkq520.web.service;

import cn.lkq520.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
public interface UserService extends IService<User> {

    User findRolesByAccount(String account);

    User findByAccount(String account);

    void register(User user);
}
