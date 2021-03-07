package cn.lkq520.web.mapper;

import cn.lkq520.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
public interface UserMapper extends BaseMapper<User> {

    User findRolesByAccount(String account);
}
