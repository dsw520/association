package cn.lkq520.web.service;

import cn.lkq520.pojo.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
public interface NewsService extends IService<News> {


    PageInfo<News> getPageByNavIdOrItemId(Integer navId, Integer itId, Integer pageNum, int size, int status);

    /**
     * <p>获取社团新闻通过社团ID</p>
     *
     * @param clubId 社团ID
     * @param passage_type_id 1-普通文章 2-常用文档 3-社团文件 4-首页轮播图 5-通知文章
     * @param limit 获取数目
     * @param status 文章状态 0-已删除 1-正常
     * @return java.util.List<cn.lkq520.pojo.News>
     */
    List<News> getListByCludId(int clubId,int passage_type_id,int limit,int status);

    List<News> getListByNavId(int navId, int clubId, int passage_type_id, int limit, int status);
}
