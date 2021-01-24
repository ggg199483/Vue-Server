package com.service;

import com.entity.UserLogin;

public interface UserService {
    void insertUserLogin(String userName, String passWd);

    void updateUserpassWd(String userName, String passWd);

    UserLogin queryById(Integer userId);

    String queryUserNameByUserId(Integer userId);

    UserLogin queryRoleByUserName(String userName, String passWd);

}
