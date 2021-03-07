package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.Club;
import cn.lkq520.web.mapper.ClubMapper;
import cn.lkq520.web.service.ClubService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {

    @Autowired private ClubMapper clubMapper;

    @Override
    public PageInfo<Club> getByItemId(Integer current, Integer size,Integer itId) {
        PageHelper.startPage(current,size);

        QueryWrapper<Club> wrapper = new QueryWrapper<>();
        wrapper.eq("club_item_id",itId);
        List<Club> clubsList = clubMapper.selectList(wrapper);

        return new PageInfo<>(clubsList);
    }
}
