package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.ResponseEntity;

public class AbstractController {

    public String successData(Object data){
        ResponseEntity responseEntity = new ResponseEntity(data,200,"success");
        return JSONObject.toJSONString(responseEntity);
    }

    public String success(){
        ResponseEntity responseEntity = new ResponseEntity(null,200,"success");
        return JSONObject.toJSONString(responseEntity);
    }

    public String success(String message){
        ResponseEntity responseEntity = new ResponseEntity(null,200,message);
        return JSONObject.toJSONString(responseEntity);
    }

    public String fail(String message){
        ResponseEntity responseEntity = new ResponseEntity(null,500,message);
        return JSONObject.toJSONString(responseEntity);
    }

    public String fail(){
        ResponseEntity responseEntity = new ResponseEntity(null,500,"fail");
        return JSONObject.toJSONString(responseEntity);
    }
}
