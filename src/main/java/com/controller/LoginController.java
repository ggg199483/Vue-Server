package com.controller;

import com.entity.UserLogin;
import com.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by tim on 2021/1/18
 **/
@RestController
public class LoginController extends AbstractController{

    @Autowired
    private UserService userService;
    private static Map userMap = new HashMap();

    @PostMapping("/login/loginbyemail")
    public String loginByemail(@RequestBody String body){
        System.out.println("body:"+body);
        System.out.println("login!");
        Map resultMap = new HashMap<>();
        resultMap.put("code",200);
        resultMap.put("msg","success");
        Role role = new Role();
        role.setRole(Arrays.asList("admin"));
        String uuid = UUID.randomUUID().toString();
        System.out.println("生成uuid:"+uuid);
        role.setToken(uuid);
        role.setIntroduction("guanliyuan");
        role.setName(" super admin");
        role.setUid("001");
        userMap.put(uuid,role);

        //通过用户名和密码去查询role 然后返回给前端
        JSONObject jsonObject = JSONObject.parseObject(body);
        String userName = jsonObject.getString("email");
        String passWd = jsonObject.getString("password");
        UserLogin userLogin = userService.queryRoleByUserName(userName, passWd);
        if(userLogin!=null){
            role.setRole(Arrays.asList(userLogin.getRole()));
            System.out.println(userLogin.getRole());
        }



        return JSONObject.toJSONString(role);
    }


    @GetMapping("/user/info")
    public String userInfo(@RequestParam("token")String token){

        System.out.println("userInfo!");
        System.out.println("token:"+token);
        Object user = userMap.get(token);
        return JSONObject.toJSONString(user);

//        Map resultMap = new HashMap<>();
//        resultMap.put("code",200);
//        resultMap.put("msg","success");
//        Role role = new Role();
//        role.setRole(Arrays.asList("admin"));
//        String uuid = UUID.randomUUID().toString();
//        System.out.println("生成uuid:"+uuid);
//        role.setToken(uuid);
//        role.setIntroduction("guanliyuan");
//        role.setName(" super admin");
//        role.setUid("001");
//        userMap.put(uuid,role);
//        return JSONObject.toJSONString(role);
    }

    @PostMapping("/user/register")//将注册信息比对后，写入user_login表
    public String register(@RequestBody()String body){
            System.out.println(" register  body::"+body);

            JSONObject jsonObject2 = JSONObject.parseObject(body);
            String userName = jsonObject2.getString("email");
            String passWd = jsonObject2.getString("password");
            String role= jsonObject2.getString("role");
            System.out.println(role);
            UserLogin userLogin = userService.queryByUserName(userName);
            if(userLogin!=null){
                return fail("该用户已存在");
            }else{
                userService.insertUserLogin(userName, passWd,role);
            }
            return success("注册成功");


    }


    @GetMapping("/test")
    public String test(){

        System.out.println("test!");
        return "success";

    }


    class Role{
        private List<String> role;

        private String token;

        private String introduction;

        private String name;

        private String uid;

        public List<String> getRole() {
            return role;
        }

        public void setRole(List<String> role) {
            this.role = role;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
