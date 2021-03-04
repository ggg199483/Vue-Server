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
@RequestMapping("/student")
public class StudentController extends AbstractController{


    @Autowired
    private UserService userService;

    @GetMapping("/get-userInfo")
    public String getUserCollege(String token){
        System.out.println("token:"+token);
        System.out.println("请求学生的信息");

        UserToken userToken=userService.queryByToken(token);
        String userInfo=userToken.getUserInfo();

        System.out.println("userInfo:"+userInfo);
        System.out.println("----------");
        LoginController.Role role = JSONObject.parseObject(userInfo,LoginController.Role.class);
        String college=userService.queryUserCollege(role.getUserId());
        if(college.length()!=0){
            System.out.println("college:"+college);
            return successData(college);
        }else {
            return fail("获取用户信息失败");
        }
    }

    @PostMapping("/apply_com")
    public String applyCompetition(@RequestBody String body){
        System.out.println("请求到了学生的申请");
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String token = jsonObject.getString("token");
        Integer id=jsonObject.getInteger("id");

        UserToken userToken=userService.queryByToken(token);
        String userInfo=userToken.getUserInfo();
        System.out.println("userInfo:"+userInfo);
        System.out.println("----------");
        LoginController.Role role = JSONObject.parseObject(userInfo,LoginController.Role.class);

        Integer judgement= userService.checkStuApply(id, role.getUserId());
        if (judgement>0){
            return fail("你已申请过该竞赛项目");
        }else if(judgement==0){
            return successData(role);
        }else {
            return fail("申请失败");
        }

    }

    @GetMapping("/get-myapply")
    public String getMyMatch(String pageSize, String currentPage,String token) {
        System.out.println(pageSize);
        System.out.println(currentPage);

        System.out.println("获取自己的竞赛申请列表：");
        System.out.println(token);
        if (!StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(pageSize)) {
            return fail("页码错误");
        }
        Integer pageNum = Integer.valueOf(currentPage);
        Integer pageSizeNum = Integer.valueOf(pageSize);

        UserToken userToken=userService.queryByToken(token);
        String userInfo=userToken.getUserInfo();
        LoginController.Role role = JSONObject.parseObject(userInfo,LoginController.Role.class);
        System.out.println("role.getUserId():"+role.getUserId());
        PageInfo<StuAppyDto> pageInfo = userService.queryMyApplyList(pageNum, pageSizeNum,role.getUserId());
        return successData(pageInfo);
    }


}
