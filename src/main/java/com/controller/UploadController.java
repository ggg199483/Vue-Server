package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

@RestController
public class UploadController extends AbstractController{

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {


        System.out.printf("*******************upload*********");
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        request.getHeader("");
        //以 String 的形式返回指定请求头的值。如果该请求不包含指定名称的头，则此方法返回 null。
        // 如果有多个具有相同名称的头，则此方法返回请求中的第一个头。头名称是不区分大小写的。可以将此方法与任何请求头一起使用

        String fileName = file.getOriginalFilename();
        System.out.println("fileName:"+fileName);
        //生成文件地址
        //可以新建一个  id-地址 对应的文件表 然后读取地址表获取文件的地址
        String filePath = "D:/comDownload/";
        File dest = new File(filePath + fileName);
        System.out.println("dest:"+dest);
        try {
            if (!dest.exists()) {// 判断目录是否存在
                dest.mkdirs();
            }
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }


    /**
     * 获取下载文件地址  nodeJS直接下载
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/download")
    public String downLoadUrl( HttpServletResponse response) {
        System.out.printf("--url--");
        System.out.println(response);
        String filePath = "D:/comDownload/NodeJS.zip";
        //后台根据download的参数去寻找文件 然后找到文件路径filePath
        File file = new File(filePath);
        if (!file.exists()){
            //文件不存在返回文件不存在提示
            return  fail(filePath);
        }
        return successData(filePath);
    }


}
