package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.BuyBook;
import com.example.bookmanagement.domain.BuyBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyBookMapper {
    long countByExample(BuyBookExample example);

    int deleteByExample(BuyBookExample example);

    int deleteByPrimaryKey(Integer buyId);

    int insert(BuyBook record);

    int insertSelective(BuyBook record);

    List<BuyBook> selectByExample(BuyBookExample example);

    BuyBook selectByPrimaryKey(Integer buyId);

    int updateByExampleSelective(@Param("record") BuyBook record, @Param("example") BuyBookExample example);

    int updateByExample(@Param("record") BuyBook record, @Param("example") BuyBookExample example);

    int updateByPrimaryKeySelective(BuyBook record);

    int updateByPrimaryKey(BuyBook record);
}