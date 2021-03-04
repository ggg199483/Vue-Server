package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.entity.UserLogin;
import com.entity.UserToken;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController extends AbstractController{


    @Autowired
    private UserService userService;

    @PostMapping("/change-userLoginInfo")
    public String changePassWord(@RequestBody String body){
        System.out.println("请求到了学生的申请");
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String token = jsonObject.getString("token");
        String userName = jsonObject.getString("userName");
        String passWord=jsonObject.getString("passWord");
        String newPassWord=jsonObject.getString("newPassWord");

        UserToken userToken=userService.queryByToken(token);
        String userInfo=userToken.getUserInfo();
        System.out.println("userInfo:"+userInfo);
        System.out.println("----------");
        LoginController.Role role = JSONObject.parseObject(userInfo,LoginController.Role.class);

        UserLogin userLogin =userService.queryById(role.getUserId());
        System.out.println("userLogin:"+userLogin);
        if(userLogin==null){
            return fail("未获取到对象");
        }
        else if(userLogin.getUserName()!=userName){
            return fail("账号不正确");
        }else if(userLogin.getPassWord()!=passWord){
            return fail("原密码不正确");
        }else {
            userService.updateUserPassWd(userName,newPassWord);
            userLogin.setPassWord(newPassWord);
            return successData(userLogin);
        }
    }
}
