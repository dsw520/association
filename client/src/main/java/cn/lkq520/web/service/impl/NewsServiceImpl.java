package cn.lkq520.web.service.impl;

import cn.lkq520.pojo.News;
import cn.lkq520.web.mapper.NewsMapper;
import cn.lkq520.web.service.NewsService;
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
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired private NewsMapper newsMapper;

    @Override
    public PageInfo<News> getPageByNavIdOrItemId(Integer navId, Integer itId, Integer pageNum, int size, int status) {
        PageHelper.startPage(pageNum,size);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","title","publish_time");
        queryWrapper.eq("nav_id",navId);
        if(itId != 0){
            queryWrapper.eq("item_id",itId);
        }
        queryWrapper.eq("status",status);
        List<News> newsList = newsMapper.selectList(queryWrapper);
        return new PageInfo<>(newsList);
    }

    @Override
    public List<News> getListByCludId(int clubId,int passage_type_id,int limit,int status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","title","publish_time");
        queryWrapper.eq("club_id",clubId);
        if (status != -1){
            queryWrapper.eq("status",status);
        }
        if (passage_type_id != -1) {
            queryWrapper.eq("passage_type_id",passage_type_id);
        }
        queryWrapper.orderByDesc("publish_time");
        queryWrapper.last("limit "+limit);
        return newsMapper.selectList(queryWrapper);
    }

    @Override
    public List<News> getListByNavId(int navId, int clubId, int passage_type_id, int limit, int status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","title","publish_time");
        if(navId != -1){
            queryWrapper.eq("nav_id",navId);
        }
        if(clubId != -1){
            queryWrapper.eq("club_id",clubId);
        }
        if (status != -1){
            queryWrapper.eq("status",status);
        }
        if (passage_type_id != -1) {
            queryWrapper.eq("passage_type_id",passage_type_id);
        }
        queryWrapper.orderByDesc("publish_time");
        queryWrapper.last("limit "+limit);
        return newsMapper.selectList(queryWrapper);
    }

}
