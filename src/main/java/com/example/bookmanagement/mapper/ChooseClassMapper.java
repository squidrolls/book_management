package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.ChooseClassExample;
import com.example.bookmanagement.domain.ChooseClassKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChooseClassMapper {
    long countByExample(ChooseClassExample example);

    int deleteByExample(ChooseClassExample example);

    int deleteByPrimaryKey(ChooseClassKey key);

    int insert(ChooseClassKey record);

    int insertSelective(ChooseClassKey record);

    List<ChooseClassKey> selectByExample(ChooseClassExample example);

    int updateByExampleSelective(@Param("record") ChooseClassKey record, @Param("example") ChooseClassExample example);

    int updateByExample(@Param("record") ChooseClassKey record, @Param("example") ChooseClassExample example);
}