package cn.lkq520.web.controller;


import cn.lkq520.pojo.Club;
import cn.lkq520.web.service.ClubService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luo
 * @since 2020-12-22
 */
@Controller
@RequestMapping("club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @ResponseBody
    @RequestMapping({"/{navId}/{itId}/{currentPage}"})
    public PageInfo<Club> club(@ModelAttribute @PathVariable("navId") Integer navId, @ModelAttribute @PathVariable("itId") Integer itId, @PathVariable(value = "currentPage") Integer currentPage){
        PageInfo<Club> clubPageInfo = clubService.getByItemId(currentPage,6,itId);
        return clubPageInfo;
    }

}

