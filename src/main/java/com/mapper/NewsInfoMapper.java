package com.mapper;

import com.entity.NewsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsInfo record);

    NewsInfo selectByPrimaryKey(Integer id);

    List<NewsInfo> selectAll();

    int updateByPrimaryKey(NewsInfo record);
}