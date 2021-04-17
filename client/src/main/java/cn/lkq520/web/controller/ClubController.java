package cn.lkq520.web.controller;


import cn.lkq520.pojo.Activity;
import cn.lkq520.pojo.Club;
import cn.lkq520.pojo.File;
import cn.lkq520.pojo.News;
import cn.lkq520.utils.CommonsConstants;
import cn.lkq520.utils.TypeConstants;
import cn.lkq520.web.service.ActivityService;
import cn.lkq520.web.service.ClubService;
import cn.lkq520.web.service.FileService;
import cn.lkq520.web.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Controller
@RequestMapping("club")
public class ClubController {

    @Autowired private ClubService clubService;
    @Autowired private NewsService newsService;
    @Autowired private FileService fileService;
    @Autowired private ActivityService activityService;

    @ResponseBody
    @RequestMapping({"/{navId}/{itId}/{currentPage}"})
    public PageInfo<Club> club(@ModelAttribute @PathVariable("navId") int navId, @ModelAttribute @PathVariable("itId") int itId, @PathVariable(value = "currentPage") int currentPage) {
        PageInfo<Club> clubPageInfo = clubService.getByItemId(currentPage, 6, itId);
        return clubPageInfo;
    }

    @RequestMapping("/toClubDetail/{clubId}")
    public String toClubDetail(Model model, @PathVariable("clubId") int clubId) {
        //设置社团通知、最新文章的数目
        int limit = CommonsConstants.getInstance().NOTICE_LIMIT;

        Club club = clubService.getById(clubId);
        List<File> fileList = fileService.getListFilesByClubId(clubId,4);
        //获取社团通知
        List<News> newsList = newsService.getListByCludId(clubId, TypeConstants.NOTICE.id,limit,1);
        //获取最新信息
        List<News> presentList = newsService.getListByCludId(clubId,-1,limit,1);
        //获取社团活动
        List<Activity> activityList = activityService.getListByCludId(TypeConstants.CLUB.id,limit,1);
        //获取社团风采
        List<News> styleList = newsService.getListByNavId(3, clubId, TypeConstants.CLUB.id,limit,1);
        model.addAttribute("club", club);
        model.addAttribute("fileList", fileList);
        model.addAttribute("newsList", newsList);
        model.addAttribute("presentList", presentList);
        model.addAttribute("activityList", activityList);
        model.addAttribute("styleList", styleList);
        return "clubdetail";
    }

}

