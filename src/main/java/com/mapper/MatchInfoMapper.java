package com.mapper;

import com.dto.MatchInfoDto;
import com.entity.MatchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MatchInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchInfo record);

    MatchInfo selectByPrimaryKey(Integer id);

    List<MatchInfo> selectAll();

    int updateByPrimaryKey(MatchInfo record);

    List<MatchInfoDto> selectMatchInfoDtoList();

    Integer insertMatch(@Param("matchInfoDto") MatchInfoDto matchInfoDto);
}