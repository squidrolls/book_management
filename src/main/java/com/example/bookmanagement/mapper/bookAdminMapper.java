package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.bookAdmin;
import com.example.bookmanagement.domain.bookAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface bookAdminMapper {
    long countByExample(bookAdminExample example);

    int deleteByExample(bookAdminExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(bookAdmin record);

    int insertSelective(bookAdmin record);

    List<bookAdmin> selectByExample(bookAdminExample example);

    bookAdmin selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("record") bookAdmin record, @Param("example") bookAdminExample example);

    int updateByExample(@Param("record") bookAdmin record, @Param("example") bookAdminExample example);

    int updateByPrimaryKeySelective(bookAdmin record);

    int updateByPrimaryKey(bookAdmin record);
}