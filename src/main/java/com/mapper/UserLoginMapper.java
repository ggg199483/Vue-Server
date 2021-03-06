package com.mapper;

import com.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginMapper {
    UserLogin queryById(@Param("id") Integer id);

    UserLogin queryByUserName(@Param("userName")String userName);

    Integer insertUser(@Param("userName") String userName,@Param("passWord") String passWord,@Param("role") String role,@Param("college") Integer college);

    String queryUserNameByUserId(@Param("userIdss") Integer userId);

    String queryUserCollege(@Param("id")Integer id);

    void updatePassWd(@Param("userName") String userName,@Param("passWord") String passWord);

    UserLogin queryRole(String userName, @Param("passWord")String passWd);

}
