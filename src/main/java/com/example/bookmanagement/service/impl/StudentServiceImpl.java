package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.domain.*;
import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.Vo.ChooseclassVo;
import com.example.bookmanagement.domain.Vo.OpenClassVo;
import com.example.bookmanagement.mapper.*;
import com.example.bookmanagement.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClassMapper classMapper;

    @Resource
    private ChooseClassMapper chooseClassMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private OpenClassMapper openClassMapper;

    @Override
    public boolean studentIsExist(int id) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentIdEqualTo(id);
        List<Student> student = studentMapper.selectByExample(studentExample);
        if (student == null) {
            return false;
        }
        if (student.size() < 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean studentLogin(int id, String password) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentIdEqualTo(id);
        List<Student> student = studentMapper.selectByExample(studentExample);
        if (student == null) {
            return false;
        }
        for (Student a : student) {
            if (a.getStudentPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ChooseclassVo> ClasschooseNow(Student student) throws ParseException {
        ChooseClassExample chooseClassExample = new ChooseClassExample();
        ChooseClassExample.Criteria chooseClassExampleCriteria = chooseClassExample.createCriteria();
        if (student.getStudentId() != null) {
            chooseClassExampleCriteria.andStudentIdEqualTo(student.getStudentId());
        }

        List<ChooseClassKey> chooseClasses = chooseClassMapper.selectByExample(chooseClassExample);
        List<ChooseclassVo> chooseclassVos = new LinkedList<>();

        if (chooseClasses == null) {
            return chooseclassVos;
        }
        for (ChooseClassKey o : chooseClasses) {
            ChooseclassVo chooseclassVo = new ChooseclassVo();

            OpenClassExample openClassExample = new OpenClassExample();
            OpenClassExample.Criteria openclassExampleCriteria = openClassExample.createCriteria();
            openclassExampleCriteria.andOpenIdEqualTo(o.getOpenId());
            List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);

            if (openClasses.size() == 1) {
                chooseclassVo.setOpenclassInfo(openClasses.get(0));
            }

            ClassExample classExample = new ClassExample();
            ClassExample.Criteria classExampleCriteria = classExample.createCriteria();
            classExampleCriteria.andCourseIdEqualTo(chooseclassVo.getOpenclassInfo().getCourseId());
            List<Class> classes = classMapper.selectByExample(classExample);

            if (classes.size() == 1) {
                chooseclassVo.setCourseName(classes.get(0).getCourseName());
                chooseclassVo.setCourseId(classes.get(0).getCourseId());
            }

            TeacherExample teacherExample = new TeacherExample();
            TeacherExample.Criteria teacherExampleCriteria = teacherExample.createCriteria();
            teacherExampleCriteria.andTeacherIdEqualTo(chooseclassVo.getOpenclassInfo().getTeacherId());
            List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);

            if (teachers.size() == 1) {
                chooseclassVo.setTeacherInfo(teachers.get(0).getTeacherInfo());
            }

            chooseclassVos.add(chooseclassVo);
        }
        return chooseclassVos;
    }

    @Override
    public List<OpenClassVo> getAllClasses() throws ParseException {
        OpenClassExample openClassExample = new OpenClassExample();
        OpenClassExample.Criteria openClassExampleCriteria = openClassExample.createCriteria();

        List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);
        List<OpenClassVo> openClassVos = new LinkedList<>();
        if (openClasses == null) {
            return openClassVos;
        }
        for (OpenClass o : openClasses) {
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
            }
            openClassVos.add(openClassVo);
        }
        return openClassVos;
    }


    @Override
    public List<OpenClassVo> getClassSel(Integer grade, String major) throws ParseException {
        OpenClassExample openClassExample = new OpenClassExample();
        OpenClassExample.Criteria openClassExampleCriteria = openClassExample.createCriteria();

        List<OpenClassVo> openClassVos = new LinkedList<>();
        openClassExampleCriteria.andGradeEqualTo(grade);
        openClassExampleCriteria.andMajorEqualTo(major);
        List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);

        for (OpenClass o : openClasses) {
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

            System.out.println("这一门课：" + classes.get(0).getCourseId());


            if (classes.size() == 1) {
                openClassVo.setClassInfo(classes.get(0));
            }

//            UploadAppendExample uploadAppendExample = new UploadAppendExample();
//            UploadAppendExample.Criteria uploadAppendExampleCriteria = uploadAppendExample.createCriteria();
//            uploadAppendExampleCriteria.andOpenIdEqualTo(o.getOpenId());
//            List<UploadAppendKey> uploadAppends = uploadAppendMapper.selectByExample(uploadAppendExample);
//            openClassVo.setUploadAppendKeys(uploadAppends);

            openClassVos.add(openClassVo);
        }
        return openClassVos;
    }


    @Override
    public List<OpenClassVo> getClassSer(String searchContent) throws ParseException {

        ClassExample classExample = new ClassExample();
        ClassExample.Criteria classExampleCritera = classExample.createCriteria();
        List<OpenClassVo> openClassVos = new LinkedList<>();

//        searchContent = "%" +searchContent+ "%";
        classExampleCritera.andCourseNameLike(searchContent);
        List<Class> classes = classMapper.selectByExample(classExample);
        if (classes == null) {
            return openClassVos;
        }
        for (Class o : classes) {
            OpenClassVo openClassVo = new OpenClassVo();
            OpenClassExample openClassExample = new OpenClassExample();
            OpenClassExample.Criteria openclassExampleCriteria = openClassExample.createCriteria();
            openclassExampleCriteria.andCourseIdEqualTo(o.getCourseId());
            List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);
            if (classes.size() == 1) {
                openClassVo.setClassInfo(classes.get(0));
            }
            if (openClasses.size() == 1) {
                openClassVo.setOpenId(openClasses.get(0).getOpenId());
                openClassVo.setStartYear(openClasses.get(0).getStartYear());
                openClassVo.setStartTerm(openClasses.get(0).getStartTerm());
                openClassVo.setGrade(openClasses.get(0).getGrade());
                openClassVo.setMajor(openClasses.get(0).getMajor());

            }
            openClassVos.add(openClassVo);
        }
        return openClassVos;
    }

    @Override
    public String ClassInfo(int courseid) {
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria= classExample.createCriteria();
        criteria.andCourseIdEqualTo(courseid);
        List<Class> classes= classMapper.selectByExample(classExample);
        if(classes.size() == 1){
            System.out.println("这一门课的简介是：" + classes.get(0).getCourseIntroduction());
            System.out.println("这一门课的id：" + classes.get(0).getCourseId());
            System.out.println("这一门课的id：" + classes.get(0).getCourseName());
            return classes.get(0).getCourseIntroduction();


        }
        return null;
    }
    @Override
    public String studentName(int id) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentIdEqualTo(id);
        List<Student> student = studentMapper.selectByExample(studentExample);
        if (student.size() == 1) {
            return student.get(0).getStudentName();
        }
        return null;
    }
}

//          List<ChooseClassKey> chooseClasses = chooseClassMapper.selectByExample(chooseClassExample);
//        List<ChooseclassVo> chooseclassVos = new LinkedList<>();
//        if (chooseClasses == null) {
//            return chooseclassVos;
//        }
//        for(ChooseClassKey o:chooseClasses) {
//            ChooseclassVo chooseclassVo = new ChooseclassVo();
//
//            OpenClassExample openClassExample = new OpenClassExample();
//            OpenClassExample.Criteria openclassExampleCriteria = openClassExample.createCriteria();
//            openclassExampleCriteria.andOpenIdEqualTo(o.getOpenId());
//            List<OpenClass> openClasses = openClassMapper.selectByExample(openClassExample);
//
//            if (openClasses.size() == 1) {
//                chooseclassVo.setOpenclassInfo(openClasses.get(0));
//            }
//
//            ClassExample classExample = new ClassExample();
//            ClassExample.Criteria classExampleCriteria = classExample.createCriteria();
//            classExampleCriteria.andCourseIdEqualTo(chooseclassVo.getOpenclassInfo().getCourseId());
//            List<Class> classes = classMapper.selectByExample(classExample);
//
//            if (classes.size() == 1) {
//                chooseclassVo.setCourseName(classes.get(0).getCourseName());
//            }
//
//            TeacherExample teacherExample = new TeacherExample();
//            TeacherExample.Criteria teacherExampleCriteria = teacherExample.createCriteria();
//            teacherExampleCriteria.andTeacherIdEqualTo(chooseclassVo.getOpenclassInfo().getTeacherId());
//            List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
//
//            if(teachers.size() ==1){
//                chooseclassVo.setTeacherInfo(teachers.get(0).getTeacherInfo());
//            }
//
//            chooseclassVos.add(chooseclassVo);

//        for(Class o:classes){
//            OpenClassVo openClassVo= new OpenClassVo();
//            openClassVo.setClassInfo();
//        }

//        for(OpenClass o:openClasses) {
//            OpenClassVo openClassVo = new OpenClassVo();
//            openClassVo.setOpenId(o.getOpenId());
//            openClassVo.setTeacherId(o.getTeacherId());
//            openClassVo.setStartYear(o.getStartYear());
//            openClassVo.setStartTerm(o.getStartTerm());
//            openClassVo.setGrade(o.getGrade());
//            openClassVo.setMajor(o.getMajor());
//
//            ClassExample classExample = new ClassExample();
//            ClassExample.Criteria classExampleCriteria = classExample.createCriteria();
//            classExampleCriteria.andCourseIdEqualTo(o.getCourseId());
//            List<Class> classes = classMapper.selectByExample(classExample);
//
//            System.out.println("这一门课：" + classes.get(0).getCourseId());
//
//
//            if (classes.size() == 1) {
//                openClassVo.setClassInfo(classes.get(0));
//            }
//
////            UploadAppendExample uploadAppendExample = new UploadAppendExample();
////            UploadAppendExample.Criteria uploadAppendExampleCriteria = uploadAppendExample.createCriteria();
////            uploadAppendExampleCriteria.andOpenIdEqualTo(o.getOpenId());
////            List<UploadAppendKey> uploadAppends = uploadAppendMapper.selectByExample(uploadAppendExample);
////            openClassVo.setUploadAppendKeys(uploadAppends);
//
//            openClassVos.add(openClassVo);
//        }
//        return openClassVos;


//
//    TextbookExample textbookExample = new TextbookExample();
//    TextbookExample.Criteria textbookExampleCriteria = textbookExample.createCriteria();
//        if (searchMethod.equals("ISBN")) {
//        textbookExampleCriteria.andIsbnEqualTo(searchContent);
//    }else if (searchMethod.equals("bookname")) {
//        textbookExampleCriteria.andBookNameEqualTo(searchContent);
//    }else {
//        textbookExampleCriteria.andAuthorEqualTo(searchContent);
//    }
//    List<Textbook> textbooks = textbookMapper.selectByExample(textbookExample);
//
//        return textbooks;




//    @Override
//    public List<OpenClassVo> getOpenClass(Teacher teacher, Date startYear, String startTerm) {
//        return null;
//    }

//    @Override
//    public boolean addBookCategory(BookCategory bookCategory) {
//        int n=bookCategoryMapper.insert(bookCategory);
//        if(n>0){
//            return true;
//        }
//        return false;
//    }
