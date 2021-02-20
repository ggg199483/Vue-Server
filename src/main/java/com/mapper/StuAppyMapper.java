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
}