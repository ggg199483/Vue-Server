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

        MatchInfo checkcollege=userService.checkCollege(id);

        if(teacherId !=null && teacherId > 0){
            return fail("该竞赛已有负责老师");
        }
        if(checkcollege.getCollege()==role.getCollege()){
            Integer result = userService.updateTeacherId(Integer.valueOf(role.getUserId()), id);
            if(result > 0){
                return success("老师报名成功");
            }else{
                return fail("报名失败");
            }
        }
        else {
            return fail("学院不同 不可报名");
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
        System.out.println("matchId:"+matchId);
        MatchInfoDto pageInfo=userService.queryMatchInfo(matchId);
        return successData(pageInfo);
    }

    @PostMapping("/apply-pass")
    public String applyDisposeP(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer id=jsonObject.getInteger("id");
        System.out.println("请求到了stu_appy中的id"+id);
        Integer result =userService.disposeMatchStatus(id,1);
        System.out.println(result);
        if(result > 0){
            return success("该学生审核通过");
        }else{
            return fail("修改失败");
        }
    }

    @PostMapping("/apply-fail")
    public String applyDisposeF(@RequestBody String body){
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer id=jsonObject.getInteger("id");
        System.out.println("请求到了stu_appy中的id"+id);
        Integer result =userService.disposeMatchStatus(id,2);
        if(result > 0){
            return success("该学生审核不通过");
        }else{
            return fail("修改失败");
        }
    }

    @GetMapping("/check_passnum")
    public String checkPassNumByMatchId(Integer matchId){
        System.out.println("matchId:"+matchId);
        Integer passNum= userService.checkPassNum(matchId);
        System.out.println("成功加入该竞赛人数为"+passNum);
        return successData(passNum);
    }
}
