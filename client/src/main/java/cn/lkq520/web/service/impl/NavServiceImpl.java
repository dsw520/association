package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.Nav;
import cn.lkq520.web.mapper.NavMapper;
import cn.lkq520.web.service.NavService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Service
public class NavServiceImpl extends ServiceImpl<NavMapper, Nav> implements NavService {

    @Autowired private NavMapper navMapper;
    @Autowired private RedisTemplate redisTemplate;

    @Override
    public List<Nav> getNavWithItem() {
        Object o = redisTemplate.opsForValue().get("association_menu");
        if (o != null) {
            //缓存中有数据
            return (List<Nav>) o;
        } else {
            List<Nav> navList = navMapper.getNavWithItem();
            if (navList.size() > 0) {
                redisTemplate.opsForValue().set("association_menu", navList);
                return navList;
            }
            return null;
        }
    }
}
