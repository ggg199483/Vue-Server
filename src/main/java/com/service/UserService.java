package com.service;

import com.dto.MatchInfoDto;
import com.entity.MatchInfo;
import com.entity.NewsInfo;
import com.entity.UserLogin;
import com.entity.UserToken;
import com.github.pagehelper.PageInfo;

public interface UserService {
    void insertUserLogin(String userName, String passWd,String role);

    void updateUserpassWd(String userName, String passWd);

    UserLogin queryByUserName(String userName);//通过账号获取用户信息

    UserLogin queryById(Integer userId);

    String queryUserNameByUserId(Integer userId);

    UserLogin queryRoleByUserName(String userName, String passWd);//通过账号密码获取用户信息

    public PageInfo<NewsInfo> queryNews(Integer currentPage, Integer pageSize);

    void insertUserToken(String token,String info);//添加user_token信息

    UserToken queryByToken(String token);//通过token查找信息

    NewsInfo queryNewById(Integer newId);//通过ID查找new_info信息

    PageInfo<MatchInfoDto> queryMatchs(Integer currentPage, Integer pageSize);//获取所有match_info信息

    Integer insertMatchInfo(MatchInfoDto matchInfoDto);
}
