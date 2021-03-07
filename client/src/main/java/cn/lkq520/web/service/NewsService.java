package cn.lkq520.web.service;

import cn.lkq520.pojo.News;
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
public interface NewsService extends IService<News> {


    PageInfo<News> getPageByNavIdOrItemId(Integer navId, Integer itId, Integer pageNum, int size, int status);
}
