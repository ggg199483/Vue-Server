package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.dto.MatchInfoDto;
import com.entity.MatchInfo;
import com.entity.NewsInfo;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class ManagerController extends AbstractController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-match")
    public String getMatch(String pageSize, String currentPage) {
        if (!StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(pageSize)) {
            return fail("页码错误");
        }

        Integer pageNum = Integer.valueOf(currentPage);
        Integer pageSizeNum = Integer.valueOf(pageSize);
        PageInfo<MatchInfoDto> pageInfo = userService.queryMatchs(pageNum, pageSizeNum);
        return successData(pageInfo);
    }

    @PostMapping("/publish-match")
    public String loginByemail(@RequestBody String body) {
        System.out.println("请求到了！");
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String title = jsonObject.getString("title");
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        String type=jsonObject.getString("type");
        Integer maxCount= jsonObject.getInteger("maxCount");
        String college= jsonObject.getString("college");
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime) ||maxCount == null || StringUtils.isBlank(college)){
            return fail("必填项不能为空");
        }

        if (startTime.compareTo(endTime)>0){
            return fail("结束时间不能小于开始时间");
        }
        MatchInfoDto matchInfoDto =new MatchInfoDto();
        matchInfoDto.setTitle(title);
        matchInfoDto.setType(type);
        matchInfoDto.setCollege(college);
        matchInfoDto.setStartDate(change(startTime));
        matchInfoDto.setEndDate(change(endTime));
        matchInfoDto.setMaxCount(maxCount);
        Integer result =  userService.insertMatchInfo(matchInfoDto);
        if (result >0){
            return success("插入成功");
        }else{
            return fail("插入失败");
        }



    }

    private String change(String a){
        a = a.replace("T"," ");
        a = a.substring(0,19);
        return a;
    }


}