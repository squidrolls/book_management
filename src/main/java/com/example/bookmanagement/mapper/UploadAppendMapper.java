package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.UploadAppendExample;
import com.example.bookmanagement.domain.UploadAppendKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadAppendMapper {
    long countByExample(UploadAppendExample example);

    int deleteByExample(UploadAppendExample example);

    int deleteByPrimaryKey(UploadAppendKey key);

    int insert(UploadAppendKey record);

    int insertSelective(UploadAppendKey record);

    List<UploadAppendKey> selectByExample(UploadAppendExample example);

    int updateByExampleSelective(@Param("record") UploadAppendKey record, @Param("example") UploadAppendExample example);

    int updateByExample(@Param("record") UploadAppendKey record, @Param("example") UploadAppendExample example);
}