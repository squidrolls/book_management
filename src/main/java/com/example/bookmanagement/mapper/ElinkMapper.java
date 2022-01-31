package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.Elink;
import com.example.bookmanagement.domain.ElinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ElinkMapper {
    long countByExample(ElinkExample example);

    int deleteByExample(ElinkExample example);

    int deleteByPrimaryKey(String isbn);

    int insert(Elink record);

    int insertSelective(Elink record);

    List<Elink> selectByExample(ElinkExample example);

    Elink selectByPrimaryKey(String isbn);

    int updateByExampleSelective(@Param("record") Elink record, @Param("example") ElinkExample example);

    int updateByExample(@Param("record") Elink record, @Param("example") ElinkExample example);

    int updateByPrimaryKeySelective(Elink record);

    int updateByPrimaryKey(Elink record);
}