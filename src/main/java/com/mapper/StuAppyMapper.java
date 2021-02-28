package com.mapper;

import com.dto.MatchInfoDto;
import com.dto.StuAppyDto;
import com.entity.StuAppy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StuAppyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StuAppy record);

    StuAppy selectByPrimaryKey(Integer id);

    List<StuAppy> selectAll();

    int updateByPrimaryKey(StuAppy record);

    List<StuAppyDto> selectStuAppyList(@Param("matchId")Integer matchId);

    List<StuAppyDto> selectMyApplyList(@Param("studentId")Integer studentId);

    Integer updateMatchStatus(@Param("id")Integer id,@Param("matchStatus")Integer matchStatus);//改变审核状态

    Integer checkStuNum(@Param("matchId")Integer matchId);//计算该竞赛人数是否超过maxCount

    Integer insertStuApply(@Param("studentId")Integer studentId,@Param("userName")String userName,@Param("matchId")Integer matchId);//插入学生申请

    Integer checkStuApply(@Param("matchId")Integer matchId,@Param("studentId")Integer studentId);//查找申请是否存在
}