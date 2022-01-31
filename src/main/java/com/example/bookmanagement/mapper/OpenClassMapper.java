package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.OpenClass;
import com.example.bookmanagement.domain.OpenClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenClassMapper {
    long countByExample(OpenClassExample example);

    int deleteByExample(OpenClassExample example);

    int deleteByPrimaryKey(String openId);

    int insert(OpenClass record);

    int insertSelective(OpenClass record);

    List<OpenClass> selectByExample(OpenClassExample example);

    OpenClass selectByPrimaryKey(String openId);

    int updateByExampleSelective(@Param("record") OpenClass record, @Param("example") OpenClassExample example);

    int updateByExample(@Param("record") OpenClass record, @Param("example") OpenClassExample example);

    int updateByPrimaryKeySelective(OpenClass record);

    int updateByPrimaryKey(OpenClass record);
}