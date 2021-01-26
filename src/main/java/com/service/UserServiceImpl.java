package com.service;

import com.entity.NewsInfo;
import com.entity.UserLogin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.NewsInfoMapper;
import com.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;


    @Override
    public void insertUserLogin(String userName, String passWd,String role) {
        userLoginMapper.insertUser(userName,passWd,role);
    }

    @Override
    public void updateUserpassWd(String userName, String passWd) {
        userLoginMapper.updatepassWd(userName,passWd);
    }

    @Override
    public UserLogin queryById(Integer userId){

        UserLogin userLogin = userLoginMapper.queryById(userId);
    return  userLogin;
    }

    @Override
    public UserLogin queryByUserName(String userName){
        UserLogin userLogin =userLoginMapper.queryByUserName(userName);
        return  userLogin;
    }

    @Override
    public UserLogin queryRoleByUserName(String userName, String passWd){
        UserLogin userLogin =userLoginMapper.queryRole(userName,passWd);
        return  userLogin;
    }

    @Override
    public String queryUserNameByUserId(Integer userId) {
       return userLoginMapper.queryUserNameByUserId(userId);
    }


    public PageInfo<NewsInfo> queryNews(Integer currentPage, Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<NewsInfo> newsInfos = newsInfoMapper.selectAll();
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return pageInfo;

    }
}
