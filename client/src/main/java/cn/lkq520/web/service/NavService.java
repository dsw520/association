package cn.lkq520.web.service;

import cn.lkq520.pojo.Nav;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
public interface NavService extends IService<Nav> {

    List<Nav> getNavWithItem();
}
