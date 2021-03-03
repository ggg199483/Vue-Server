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


        System.out.printf("**********************upload*********");
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        request.getHeader("");
        String fileName = file.getOriginalFilename();
        //生成文件地址
        //可以新建一个  id-地址 对应的文件表 然后读取地址表获取文件的地址
        String filePath = "D:/download/";
        File dest = new File(filePath + fileName);
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
        System.out.printf("url");
        String filePath = "D:/download/downloadFinal.zip";
        //后台根据download的参数去寻找文件 然后找到文件路径filePath
        File file = new File(filePath);
        if (!file.exists()){
            //文件不存在返回文件不存在提示
            return  fail(filePath);
        }
        return successData(filePath);
    }


}
