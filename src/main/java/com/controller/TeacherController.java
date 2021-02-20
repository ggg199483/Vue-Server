package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.dto.MatchInfoDto;
import com.dto.StuAppyDto;
import com.entity.MatchInfo;
import com.entity.NewsInfo;
import com.entity.UserToken;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends AbstractController{

    @Autowired
    private UserService userService;

    @GetMapping("/get-match")
    public String getMatch(String pageSize, String currentPage) {
        System.out.println(pageSize);
        System.out.println(currentPage);
        if (!StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(pageSize)) {
            return fail("页码错误");
        }
        Integer pageNum = Integer.valueOf(currentPage);
        Integer pageSizeNum = Integer.valueOf(pageSize);
        PageInfo<MatchInfoDto> pageInfo = userService.queryMatchs(pageNum, pageSizeNum);
        return successData(pageInfo);
    }

    @GetMapping("/get-mymatch")
    public String getMyMatch(String pageSize, String currentPage,String token) {
        System.out.println(pageSize);
        System.out.println(currentPage);
        System.out.println(token);
        if (!StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(pageSize)) {
            return fail("页码错误");
        }
        Integer pageNum = Integer.valueOf(currentPage);
        Integer pageSizeNum = Integer.valueOf(pageSize);

        UserToken userToken=userService.queryByToken(token);
        String userInfo=userToken.getUserInfo();
        LoginController.Role role = JSONObject.parseObject(userInfo,LoginController.Role.class);

        PageInfo<MatchInfoDto> pageInfo = userService.queryMyMatchs(pageNum, pageSizeNum,Integer.valueOf(role.getUserId()));
        return successData(pageInfo);
    }

    @PostMapping("/enter-comp")
    public String enterComp(@RequestBody String body) {
        System.out.println("请求到了！");
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String token = jsonObject.getString("token");
        Integer id=jsonObject.getInteger("id");

        UserToken userToken=userService.queryByToken(token);
        String userInfo=userToken.getUserInfo();
        LoginController.Role role = JSONObject.parseObject(userInfo,LoginController.Role.class);

        System.out.println(userInfo);
        System.out.println("----------");

        Integer teacherId = userService.selectTeacherId(id);

        if(teacherId !=null && teacherId > 0){
            return fail("该竞赛已有负责老师");
        }
        Integer result = userService.updateTeacherId(Integer.valueOf(role.getUserId()), id);
        if(result > 0){
            return success("老师报名成功");
        }else{
            return fail("fail");
        }

    }

    @GetMapping("/get-stumatch")
    public String getStuMatch(String pageSize, String currentPage,Integer matchId) {
        System.out.println(pageSize);
        System.out.println(currentPage);
        System.out.println(matchId);
        if (!StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(pageSize)) {
            return fail("页码错误");
        }
        Integer pageNum = Integer.valueOf(currentPage);
        Integer pageSizeNum = Integer.valueOf(pageSize);

        PageInfo<StuAppyDto> pageInfo=userService.queryStuMatchs(pageNum,pageSizeNum,matchId);
        return successData(pageInfo);
    }

    @GetMapping("/get-matchinfo")
    public String getStuMatch(Integer matchId) {
        System.out.println("sss"+matchId);
        MatchInfoDto pageInfo=userService.queryMatchInfo(matchId);
        return successData(pageInfo);
    }

}
