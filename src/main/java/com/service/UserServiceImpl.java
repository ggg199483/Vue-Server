package com.service;

import com.entity.UserLogin;
import com.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public void insertUserLogin(String userName, String passWd) {
        userLoginMapper.insertUser(userName,passWd);
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
    public UserLogin queryRoleByUserName(String userName, String passWd){
        UserLogin userLogin =userLoginMapper.queryRole(userName,passWd);
        return  userLogin;
    }

    @Override
    public String queryUserNameByUserId(Integer userId) {
       return userLoginMapper.queryUserNameByUserId(userId);
    }
}
