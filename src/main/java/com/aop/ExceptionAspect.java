package com.aop;

import com.alibaba.fastjson.JSONObject;
import com.entity.ResponseEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tim on 2021/1/25
 **/


@Aspect
@Component
public class ExceptionAspect {


    @Pointcut("execution(* com.controller..*.*(..))")
////    @Pointcut("execution(public String com.example.demo.aop.impl.UserAOPServiceImpl(int))")
//    @Pointcut("execution(public String com.example.demo.aop.impl.UserAOPServiceImpl.*(..))")
    public void pointCut(){}


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result  ;
        try {
            result= point.proceed();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new ResponseEntity(null,500,"服务器异常"));
    }

}
