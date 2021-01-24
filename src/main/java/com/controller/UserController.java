package com.controller;


import com.entity.News;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractController{

    @GetMapping("/get-news")
    public String getNews(){

        List<News> newsList = new ArrayList<>();
        newsList.add(new News(1,"今天深圳下大雨"));
        newsList.add(new News(2,"我去买了一支笔"));
        newsList.add(new News(3,"点了个外卖不好吃"));
        newsList.add(new News(4,"是你的还是我的的"));
        newsList.add(new News(5,"妥协给我好不好"));
        newsList.add(new News(6,"哈哈哈哈急急急"));
        newsList.add(new News(7,"来电刺激的的"));
        newsList.add(new News(8,"搞完下班"));
        return successData(newsList);
    }


}
