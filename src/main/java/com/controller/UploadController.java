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
        String filePath = "D:/360Downloads/21/";
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


    @GetMapping("/download")
    public String downLoad( HttpServletResponse response) throws Exception {
        System.out.printf("dddddddddddddddddddddddddddd");
        String filePath = "D:/360Downloads/21/test2.txt";
        response.setHeader("media", "file");
        return  filePath;
    }

//    public void downLoad( HttpServletResponse response) throws Exception {
//        System.out.printf("dddddddddddddddddddddddddddd");
//        String filePath = "D:/360Downloads/21/鼠标连点器(F3开 F4关).exe";
//        File f = new File(filePath);
//        if (!f.exists()) {
//            response.sendError(404, "File not found!");
//            return;
//        }
//        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
//        byte[] buf = new byte[1024];
//        int len = 0;
//
//        response.reset(); // 非常重要
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "No-cache");
//        response.setDateHeader("Expires", 0);
//        OutputStream out = response.getOutputStream();
//        while ((len = br.read(buf)) > 0)
//            out.write(buf, 0, len);
//        br.close();
//        out.close();
//    }

}