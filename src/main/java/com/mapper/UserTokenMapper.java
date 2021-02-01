package com.mapper;

import com.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTokenMapper {

    void insertToken(@Param("token") String token,@Param("userInfo") String userInfo);

    UserToken queryToken(@Param("token") String token);

}
