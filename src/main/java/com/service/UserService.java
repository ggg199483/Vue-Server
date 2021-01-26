package com.service;

import com.entity.NewsInfo;
import com.entity.UserLogin;
import com.github.pagehelper.PageInfo;

public interface UserService {
    void insertUserLogin(String userName, String passWd,String role);

    void updateUserpassWd(String userName, String passWd);

    UserLogin queryByUserName(String userName);//通过账号获取用户信息

    UserLogin queryById(Integer userId);

    String queryUserNameByUserId(Integer userId);

    UserLogin queryRoleByUserName(String userName, String passWd);//通过账号密码获取用户信息

    public PageInfo<NewsInfo> queryNews(Integer currentPage, Integer pageSize);

}
