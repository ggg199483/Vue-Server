package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.dto.MatchInfoDto;
import com.entity.News;
import com.entity.NewsInfo;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;

    @GetMapping("/get-news")
    public String getNews(String pageSize,String currentPage){
        System.out.println(pageSize);
        System.out.println(currentPage);
        if(!StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(pageSize)){
            return fail("页码错误");
        }
//        return fail("反正是足有偶偶搜啊的");
        Integer pageNum = Integer.valueOf(currentPage);
        Integer pageSizeNum = Integer.valueOf(pageSize);
        PageInfo<NewsInfo> pageInfo = userService.queryNews(pageNum, pageSizeNum);
        return successData(pageInfo);
    }

    @GetMapping("/get-news-info")
    public String getNewsById(String newsId){
        System.out.println("newsid:"+newsId);
        if(!StringUtils.isNumeric(newsId)){
            return fail("newsId错误");
        }
        Integer newsIdInt = Integer.valueOf(newsId);
        NewsInfo newsInfo = userService.queryNewById(newsIdInt);
        return successData(newsInfo);
    }



}
