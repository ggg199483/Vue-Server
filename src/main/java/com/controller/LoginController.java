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
public class LoginController extends AbstractController {

    @Autowired
    private UserService userService;
    private static Map userMap = new HashMap();

    @PostMapping("/login/loginbyemail")
    public String loginByemail(@RequestBody String body) {
        System.out.println("body:" + body);
        System.out.println("login!");
        Map resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", "success");
        Role role = new Role();
        role.setRole(Arrays.asList("admin"));
        String uuid = UUID.randomUUID().toString();
        System.out.println("生成uuid:" + uuid);
        role.setToken(uuid);
        role.setIntroduction("guanliyuan");
        role.setName(" super admin");
        role.setUid("001");
        userMap.put(uuid, role);

        //通过用户名和密码去查询role 然后返回给前端
        JSONObject jsonObject = JSONObject.parseObject(body);
        String userName = jsonObject.getString("email");
        String passWd = jsonObject.getString("password");
        String userRole = jsonObject.getString("role");
        //查询是否存在账号
        //是 继续走下面
        //否 返回账号不存在
        //查询密码是否正确
        //是 返回登录成功
        //否 密码错误
        UserLogin userLogin1 = userService.queryByUserName(userName);
        UserLogin userLogin2 = userService.queryRoleByUserName(userName, passWd);
        if (userLogin1 != null) {
            System.out.println("存在该用户");
            if (userLogin2 != null) {
                System.out.println("查找到用户");
                role.setRole(Arrays.asList(userLogin2.getRole()));
                System.out.println(userLogin2.getRole());
                return successData(role);
            }
            else {
                System.out.println("密码错误");
                return fail("密码错误");
            }
        } else {
            System.out.println(" 账号不存在!!!!!!!!!!!!!!!!");
            return fail("该账号不存在");
        }
    }


    @GetMapping("/user/info")
    public String userInfo(@RequestParam("token") String token) {

        System.out.println("userInfo!");
        System.out.println("token:" + token);
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
    public String register(@RequestBody() String body) {
        System.out.println(" register  body::" + body);

        JSONObject jsonObject2 = JSONObject.parseObject(body);
        String userName = jsonObject2.getString("email");
        String passWd = jsonObject2.getString("password");
        String role = jsonObject2.getString("role");
        System.out.println(role);
        UserLogin userLogin = userService.queryByUserName(userName);
        if (userLogin != null) {
            return fail("该用户已存在");
        } else {
            userService.insertUserLogin(userName, passWd, role);
        }
        return success("注册成功");


    }


    @GetMapping("/test")
    public String test() {

        System.out.println("test!");
        return "success";

    }


    class Role {
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
