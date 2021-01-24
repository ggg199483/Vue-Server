package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.UserLogin;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        Map resultMap = new HashMap();
        resultMap.put("code",200);
        resultMap.put("message","login success!");




        System.out.println("test !logiin!");
        return JSONObject.toJSONString(resultMap);
    }
    @GetMapping("/test2")
    public String test2(){
        Map resultMap = new HashMap();
        resultMap.put("code",200);
        resultMap.put("message","22222222login success!");




        System.out.println("test !logiin!");
        return JSONObject.toJSONString(resultMap);
    }


    @PostMapping("/test3")
    public String test3(@RequestBody  String body){
        System.out.println("body"+JSONObject.toJSONString(body));
        JSONObject jsonObject = JSONObject.parseObject(body);
        String userName = jsonObject.getString("userName");
        String passWd = jsonObject.getString("passWord");
        System.out.println(userName);
        System.out.println(passWd);


        //userService.insertUserLogin(userName,passWd);
        userService.updateUserpassWd(userName,passWd);

        Map resultMap = new HashMap();
        resultMap.put("code",200);
        resultMap.put("message","3login success!");



        System.out.println("test !logiin3!");
        return JSONObject.toJSONString(resultMap);
    }


    @PostMapping("/test4")
    public String test4(@RequestBody  String body){
        System.out.println("body"+JSONObject.toJSONString(body));
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer userId = jsonObject.getInteger("userId");
        System.out.println(userId);

        String userName = userService.queryUserNameByUserId(userId);

        Map resultMap = new HashMap();
        resultMap.put("code",200);
        resultMap.put("data",userName);



        System.out.println("test !logiin4!");
        return JSONObject.toJSONString(resultMap);
    }
}
