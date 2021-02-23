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

    MatchInfo checkCollege(@Param("id")Integer id);//检查报名老师和课题的学院是否一样

    List<MatchInfo> selectAll();

    int updateByPrimaryKey(MatchInfo record);

    List<MatchInfoDto> selectMatchInfoDtoList();

    List<MatchInfoDto> selectMyMatchInfoDtoList(@Param("teacherId")Integer teacherId);

    Integer insertMatch(@Param("matchInfoDto") MatchInfoDto matchInfoDto);

    Integer selectTeaId(@Param("id")Integer id);//查找竞赛老师

    Integer updateTeaId(@Param("teacherId")Integer teacherId,@Param("id")Integer id);//修改竞赛老师

    MatchInfoDto selectMatchInfoDto(@Param("matchId")Integer matchId);


}