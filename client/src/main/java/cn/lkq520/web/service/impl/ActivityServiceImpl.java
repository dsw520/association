package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.Activity;
import cn.lkq520.web.mapper.ActivityMapper;
import cn.lkq520.web.service.ActivityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getListByCludId(int cid, int limit, int status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","activity_name","hold_time");
        queryWrapper.eq("club_id",cid);
        queryWrapper.eq("status",status);
        queryWrapper.last("limit " + limit);
        return activityMapper.selectList(queryWrapper);
    }
}
