package com.service;

import com.dto.MatchInfoDto;
import com.dto.StuAppyDto;
import com.entity.MatchInfo;
import com.entity.NewsInfo;
import com.entity.UserLogin;
import com.entity.UserToken;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.*;
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

    @Autowired
    private StuAppyMapper stuAppyMapper;

    @Override
    public void insertUserLogin(String userName, String passWd,String role,Integer college) {
        userLoginMapper.insertUser(userName,passWd,role,college);
    }

    @Override
    public void updateUserPassWd(String userName, String passWd) {
        userLoginMapper.updatePassWd(userName,passWd);
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

    @Override
    public String queryUserCollege(Integer id) {
        return userLoginMapper.queryUserCollege(id);
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

    @Override
    public PageInfo<MatchInfoDto> queryMatchs(Integer currentPage, Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<MatchInfoDto> matchInfos = matchInfoMapper.selectMatchInfoDtoList();
        PageInfo<MatchInfoDto> pageInfo = new PageInfo<>(matchInfos);
        return pageInfo;
    }

    @Override
    public PageInfo<MatchInfoDto> queryMyMatchs(Integer currentPage, Integer pageSize, Integer teacherId) {
        PageHelper.startPage(currentPage,pageSize);
        List<MatchInfoDto> matchInfos = matchInfoMapper.selectMyMatchInfoDtoList(teacherId);
        PageInfo<MatchInfoDto> pageInfo = new PageInfo<>(matchInfos);
        return pageInfo;
    }

    @Override
    public MatchInfo checkCollege(Integer id) {
        return matchInfoMapper.checkCollege(id);
    }

    @Override
    public MatchInfoDto queryMatchInfo(Integer matchId) {
        return matchInfoMapper.selectMatchInfoDto(matchId);
    }

    @Override
    public Integer insertMatchInfo(MatchInfoDto matchInfoDto) {
       return matchInfoMapper.insertMatch(matchInfoDto);
    }

    @Override
    public Integer selectTeacherId(Integer id) {
        return matchInfoMapper.selectTeaId(id);
    }

    @Override
    public Integer updateTeacherId(Integer teacherId, Integer id) {
        return matchInfoMapper.updateTeaId(teacherId,id);
    }

    @Override
    public PageInfo<StuAppyDto> queryStuMatchs(Integer currentPage, Integer pageSize,Integer matchId) {
        PageHelper.startPage(currentPage,pageSize);
        List<StuAppyDto> stuInfos = stuAppyMapper.selectStuAppyList(matchId);
        PageInfo<StuAppyDto> pageInfo = new PageInfo<>(stuInfos);
        return pageInfo;
    }

    @Override
    public PageInfo<StuAppyDto> queryMyApplyList(Integer currentPage, Integer pageSize, Integer studentId) {
        PageHelper.startPage(currentPage,pageSize);
        List<StuAppyDto> stuInfos = stuAppyMapper.selectMyApplyList(studentId);
        PageInfo<StuAppyDto> pageInfo = new PageInfo<>(stuInfos);
        return pageInfo;
    }

    @Override
    public Integer disposeMatchStatus(Integer id,Integer matchStatus) {
        return stuAppyMapper.updateMatchStatus(id,matchStatus);
    }

    @Override
    public Integer checkPassNum(Integer matchId) {
        return stuAppyMapper.checkStuNum(matchId);
    }

    @Override
    public Integer insertStuApply(Integer studentId, String userName, Integer matchId) {
        return stuAppyMapper.insertStuApply(studentId,userName,matchId);
    }

    @Override
    public Integer checkStuApply(Integer matchId, Integer studentId) {
        return stuAppyMapper.checkStuApply(matchId,studentId);
    }
}
