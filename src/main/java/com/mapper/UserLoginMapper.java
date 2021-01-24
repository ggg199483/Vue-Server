package com.mapper;

import com.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginMapper {
    UserLogin queryById(@Param("id") Integer id);

    Integer insertUser(@Param("userName") String userName,@Param("passWord") String passWord);

    String queryUserNameByUserId(@Param("userIdss") Integer userId);
    

    void updatepassWd(@Param("userName") String userName,@Param("passWord") String passWord);

    UserLogin queryRole(String userName, @Param("passWord")String passWd);
}
