package cn.lkq520.web.controller;


import cn.lkq520.pojo.File;
import cn.lkq520.pojo.Nav;
import cn.lkq520.pojo.News;
import cn.lkq520.web.service.FileService;
import cn.lkq520.web.service.NavService;
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
 *  前端控制器
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Controller
@RequestMapping("news")
public class NewsController {

    @Autowired private NavService navService;
    @Autowired private NewsService newsService;
    @Autowired private FileService fileService;


    @RequestMapping("/detail/{newId}")
    public String detail(Model model,@PathVariable("newId") Integer newId){
        List<Nav> navList = navService.getNavWithItem();
        List<File> fileList = fileService.getListByFileTypeId(4);
        News news = newsService.getById(newId);
        model.addAttribute("navList",navList);
        model.addAttribute("fileList",fileList);
        model.addAttribute("news",news);
        return "detail";
    }

    @ResponseBody
    @RequestMapping("/list/{navId}/{itId}/{pageNum}")
    public PageInfo<News> list(Model model,@ModelAttribute @PathVariable("navId") Integer navId,
                               @ModelAttribute @PathVariable("itId") Integer itId,
                                @PathVariable(value = "pageNum") Integer pageNum){
        PageInfo<News> newsInfo = newsService.getPageByNavIdOrItemId(navId,itId,pageNum,6,1);
        return newsInfo;
    }

}

