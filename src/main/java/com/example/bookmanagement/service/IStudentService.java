package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.Vo.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface IStudentService {

    // 验证用户是否存在
    public boolean studentIsExist(int id);

    // 登陆
    public boolean studentLogin(int name,String password);

    //获取学生的课程课程

    public List<ChooseclassVo> ClasschooseNow(Student student) throws ParseException;

    public List<OpenClassVo> getAllClasses() throws ParseException;

    public List<OpenClassVo> getClassSel(Integer grade, String major ) throws ParseException;

    public List<OpenClassVo> getClassSer(String searchContent) throws ParseException;

    //返回课程简介
    public String ClassInfo(int courseid);

    public String studentName(int id);




//
//    //获取本学期教师用户开设课程
//    public List<OpenClassVo> getOpenClass(Teacher teacher, Date startYear, String startTerm);
}
