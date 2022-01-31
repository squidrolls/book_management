package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.Vo.*;
import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.mapper.*;
import com.example.bookmanagement.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.text.SimpleDateFormat;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private OpenClassMapper openClassMapper;

    @Resource
    private UploadAppendMapper uploadAppendMapper;

    @Override
    public boolean teacherIsExist(int id) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        List<Teacher> teacher = teacherMapper.selectByExample(teacherExample);
        if (teacher == null) { return false; }
        if (teacher.size() < 1) { return false; }
        return true;
    }

    @Override
    public boolean teacherLogin(int id, String password) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        List<Teacher> teacher = teacherMapper.selectByExample(teacherExample);
        if (teacher == null) { return false; }
        for(Teacher a:teacher){
            if(a.getTeacherPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String teacherInfo(int id) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        List<Teacher> teacher = teacherMapper.selectByExample(teacherExample);
        if (teacher.size() == 1) {
            return teacher.get(0).getTeacherInfo();
        }
        return null;
    }

    @Override
    public List<OpenClassVo> getOpenClassNow(Teacher teacher) throws ParseException {
        OpenClassExample openClassExample = new OpenClassExample();
        OpenClassExample.Criteria openClassExampleCriteria = openClassExample.createCriteria();
        if (teacher.getTeacherId() != null) {
            openClassExampleCriteria.andTeacherIdEqualTo(teacher.getTeacherId());
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        openClassExampleCriteria.andStartYearEqualTo(sdf.parse("2021"));
//        openClassExampleCriteria.andStartTermEqualTo("1");
        List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);
        List<OpenClassVo> openClassVos = new LinkedList<>();
        if (openClasses == null) {
            return openClassVos;
        }
        for(OpenClass o:openClasses) {
            OpenClassVo openClassVo = new OpenClassVo();
            openClassVo.setOpenId(o.getOpenId());
            openClassVo.setTeacherId(o.getTeacherId());
            openClassVo.setStartYear(o.getStartYear());
            openClassVo.setStartTerm(o.getStartTerm());
            openClassVo.setGrade(o.getGrade());
            openClassVo.setMajor(o.getMajor());
            ClassExample classExample = new ClassExample();
            ClassExample.Criteria classExampleCriteria = classExample.createCriteria();
            classExampleCriteria.andCourseIdEqualTo(o.getCourseId());
            List<Class> classes = classMapper.selectByExample(classExample);
            if (classes.size() == 1) {
                openClassVo.setClassInfo(classes.get(0));
                openClassVos.add(openClassVo);
            }
        }
        return openClassVos;
    }

    @Override
    public List<OpenClassVo> getOpenClass(Teacher teacher, String startYear, String startTerm) throws ParseException {
        OpenClassExample openClassExample = new OpenClassExample();
        OpenClassExample.Criteria openClassExampleCriteria = openClassExample.createCriteria();

        List<OpenClassVo> openClassVos = new LinkedList<>();
        if (teacher.getTeacherId() == null || startTerm == null || startYear == null) {
            return openClassVos;
        }

        openClassExampleCriteria.andTeacherIdEqualTo(teacher.getTeacherId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        openClassExampleCriteria.andStartYearEqualTo(sdf.parse(startYear));
        openClassExampleCriteria.andStartTermEqualTo(startTerm);
        List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);

        for(OpenClass o:openClasses) {
            OpenClassVo openClassVo = new OpenClassVo();
            openClassVo.setOpenId(o.getOpenId());
            openClassVo.setTeacherId(o.getTeacherId());
            openClassVo.setStartYear(o.getStartYear());
            openClassVo.setStartTerm(o.getStartTerm());
            openClassVo.setGrade(o.getGrade());
            openClassVo.setMajor(o.getMajor());

            UploadAppendExample uploadAppendExample = new UploadAppendExample();
            UploadAppendExample.Criteria uploadAppendExampleCriteria = uploadAppendExample.createCriteria();
            uploadAppendExampleCriteria.andOpenIdEqualTo(o.getOpenId());
            List<UploadAppendKey> uploadAppends = uploadAppendMapper.selectByExample(uploadAppendExample);
            openClassVo.setUploadAppendKeys(uploadAppends);

            ClassExample classExample = new ClassExample();
            ClassExample.Criteria classExampleCriteria = classExample.createCriteria();
            classExampleCriteria.andCourseIdEqualTo(o.getCourseId());
            List<Class> classes = classMapper.selectByExample(classExample);

            if (classes.size() == 1) {
                openClassVo.setClassInfo(classes.get(0));
                openClassVos.add(openClassVo);
            }
        }
        return openClassVos;
    }

    @Override
    public OpenClassVo getThisClass(String openId) {
        OpenClassExample openClassExample = new OpenClassExample();
        OpenClassExample.Criteria openClassExampleCriteria = openClassExample.createCriteria();
        openClassExampleCriteria.andOpenIdEqualTo(openId);
        List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);
        OpenClassVo openClassVo = new OpenClassVo();
        if (openClasses == null) {
            return openClassVo;
        }
        if (openClasses.size() == 1) {
            OpenClass openClass = openClasses.get(0);
            openClassVo.setOpenId(openClass.getOpenId());
            openClassVo.setTeacherId(openClass.getTeacherId());
            openClassVo.setStartYear(openClass.getStartYear());
            openClassVo.setStartTerm(openClass.getStartTerm());
            openClassVo.setGrade(openClass.getGrade());
            openClassVo.setMajor(openClass.getMajor());

            ClassExample classExample = new ClassExample();
            ClassExample.Criteria classExampleCriteria = classExample.createCriteria();
            classExampleCriteria.andCourseIdEqualTo(openClass.getCourseId());
            List<Class> classes = classMapper.selectByExample(classExample);
            System.out.println("这一门课：" + classes.get(0).getCourseId());

            if (classes.size() == 1) {
                openClassVo.setClassInfo(classes.get(0));
                System.out.println("这一门课的id：" + openClassVo.getClassInfo().getCourseId());
                System.out.println("这一门课的名字：" + openClassVo.getClassInfo().getCourseName());
                System.out.println("这一门课的简介：" + openClassVo.getClassInfo().getCourseIntroduction());
            }

            UploadAppendExample uploadAppendExample = new UploadAppendExample();
            UploadAppendExample.Criteria uploadAppendExampleCriteria = uploadAppendExample.createCriteria();
            uploadAppendExampleCriteria.andOpenIdEqualTo(openClass.getOpenId());
            List<UploadAppendKey> uploadAppends = uploadAppendMapper.selectByExample(uploadAppendExample);
            openClassVo.setUploadAppendKeys(uploadAppends);

        }
        return openClassVo;
    }

    @Override
    public boolean changeIntro(Class classInfo) {
        int n = classMapper.updateByPrimaryKeySelective(classInfo);
        if (n > 0) {
            return true;
        }
        return false;
    }
}
