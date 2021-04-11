package cn.lkq520.web.service;

import cn.lkq520.pojo.Club;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
public interface ClubService extends IService<Club> {

    PageInfo<Club> getByItemId(Integer current, Integer size, Integer itId);

}
