package com.service;

import com.dto.MatchInfoDto;
import com.dto.StuAppyDto;
import com.entity.MatchInfo;
import com.entity.NewsInfo;
import com.entity.UserLogin;
import com.entity.UserToken;
import com.github.pagehelper.PageInfo;

public interface UserService {
    void insertUserLogin(String userName, String passWd,String role,Integer college);

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

    PageInfo<MatchInfoDto> queryMyMatchs(Integer currentPage, Integer pageSize,Integer teacherId);//获取所有match_info信息

    MatchInfo checkCollege(Integer id);

    MatchInfoDto queryMatchInfo(Integer matchId);

    Integer insertMatchInfo(MatchInfoDto matchInfoDto);

    Integer selectTeacherId(Integer id);//通过竞赛信息的Id查找对应老师Id

    Integer updateTeacherId(Integer teacherId,Integer id);//通过竞赛列表的Id修改老师Id

    PageInfo<StuAppyDto> queryStuMatchs(Integer currentPage, Integer pageSize,Integer matchId);//获取所有stu_appy信息

    Integer disposeMatchStatus(Integer id,Integer matchStatus);//改变审核状态

    Integer checkPassNum(Integer matchId);//计算该竞赛人数是否超过maxCount
}
