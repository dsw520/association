package cn.lkq520.web.controller;


import cn.lkq520.pojo.Club;
import cn.lkq520.pojo.File;
import cn.lkq520.pojo.News;
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

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Controller
@RequestMapping("items")
public class ItemsController {

    @Autowired private ClubService clubService;
    @Autowired private NewsService newsService;
    @Autowired private FileService fileService;

    @RequestMapping({"/{navId}/{itId}/{currentPage}"})
    public String item(Model model, @ModelAttribute @PathVariable("navId") Integer navId,@ModelAttribute @PathVariable("itId") Integer itId,
                        @PathVariable(value = "currentPage") Integer currentPage){
        List<File> fileList = fileService.getListByFileTypeId(4);
        model.addAttribute("fileList",fileList);
        if(navId == 5){
            PageInfo<Club> clubPageInfo = clubService.getByItemId(currentPage,6,itId);
            model.addAttribute("clubPageInfo",clubPageInfo);
            return "clubList";
        }else{
            PageInfo<News> newsInfo = newsService.getPageByNavIdOrItemId(navId,itId,currentPage,6,1);
            model.addAttribute("newsInfo",newsInfo);
            return "items";
        }
    }
}

