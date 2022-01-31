package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.ChooseTextbookExample;
import com.example.bookmanagement.domain.ChooseTextbookKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChooseTextbookMapper {
    long countByExample(ChooseTextbookExample example);

    int deleteByExample(ChooseTextbookExample example);

    int deleteByPrimaryKey(ChooseTextbookKey key);

    int insert(ChooseTextbookKey record);

    int insertSelective(ChooseTextbookKey record);

    List<ChooseTextbookKey> selectByExample(ChooseTextbookExample example);

    int updateByExampleSelective(@Param("record") ChooseTextbookKey record, @Param("example") ChooseTextbookExample example);

    int updateByExample(@Param("record") ChooseTextbookKey record, @Param("example") ChooseTextbookExample example);
}