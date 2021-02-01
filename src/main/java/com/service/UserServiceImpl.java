package com.service;

import com.dto.MatchInfoDto;
import com.entity.MatchInfo;
import com.entity.NewsInfo;
import com.entity.UserLogin;
import com.entity.UserToken;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.MatchInfoMapper;
import com.mapper.NewsInfoMapper;
import com.mapper.UserLoginMapper;
import com.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private MatchInfoMapper matchInfoMapper;


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

    @Override
    public void insertUserToken(String token, String info) {
        userTokenMapper.insertToken(token,info);
    }

    @Override
    public UserToken queryByToken(String token) {
        return userTokenMapper.queryToken(token);
    }

    @Override
    public NewsInfo queryNewById(Integer newId){
        return newsInfoMapper.selectByPrimaryKey(newId);
    }

    public PageInfo<MatchInfoDto> queryMatchs(Integer currentPage, Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<MatchInfoDto> matchInfos = matchInfoMapper.selectMatchInfoDtoList();
        PageInfo<MatchInfoDto> pageInfo = new PageInfo<>(matchInfos);
        return pageInfo;
    }

    @Override
    public Integer insertMatchInfo(MatchInfoDto matchInfoDto) {
       return matchInfoMapper.insertMatch(matchInfoDto);
    }


}
