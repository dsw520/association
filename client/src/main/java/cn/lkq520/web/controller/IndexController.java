package cn.lkq520.web.controller;

import cn.lkq520.pojo.File;
import cn.lkq520.web.service.FileService;
import cn.lkq520.web.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2020/12/23 10:58
 */
@Controller
public class IndexController {

    @Autowired private NavService navService;
    @Autowired private FileService fileService;

    @RequestMapping({"index","/"})
    public String index(Model model){
        List<File> fileList = fileService.getListByFileTypeId(4);
        model.addAttribute("fileList",fileList);
        return "index";
    }
}
