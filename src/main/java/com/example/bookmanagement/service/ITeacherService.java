package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.Vo.*;

import java.text.ParseException;
import java.util.List;

public interface ITeacherService {

    // 验证教师用户是否存在
    public boolean teacherIsExist(int id);

    // 教师登陆
    public boolean teacherLogin(int name,String password);

    // 返回老师名字
    public String teacherInfo(int id);

    //获取本学期教师用户开设课程
    public List<OpenClassVo> getOpenClassNow(Teacher teacher) throws ParseException;

    //获取本学期教师用户开设课程
    public List<OpenClassVo> getOpenClass(Teacher teacher, String startYear, String startTerm) throws ParseException;

    //根据openId或许课程
    public OpenClassVo getThisClass(String openId);

    //修改简介
    public boolean changeIntro(Class classInfo);
}
