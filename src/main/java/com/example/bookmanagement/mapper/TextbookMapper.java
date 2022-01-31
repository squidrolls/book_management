package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.Textbook;
import com.example.bookmanagement.domain.TextbookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TextbookMapper {
    long countByExample(TextbookExample example);

    int deleteByExample(TextbookExample example);

    int deleteByPrimaryKey(String isbn);

    int insert(Textbook record);

    int insertSelective(Textbook record);

    List<Textbook> selectByExample(TextbookExample example);

    Textbook selectByPrimaryKey(String isbn);

    int updateByExampleSelective(@Param("record") Textbook record, @Param("example") TextbookExample example);

    int updateByExample(@Param("record") Textbook record, @Param("example") TextbookExample example);

    int updateByPrimaryKeySelective(Textbook record);

    int updateByPrimaryKey(Textbook record);
}