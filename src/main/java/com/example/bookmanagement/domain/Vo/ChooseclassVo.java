package com.example.bookmanagement.domain.Vo;

import com.example.bookmanagement.domain.ChooseClassKey;
import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.OpenClass;
import com.example.bookmanagement.domain.Teacher;

import java.util.Date;

public class ChooseclassVo {
    //    int studentId;
    private String courseName;

    private Integer courseId;

    private String teacherInfo;
    OpenClass openclassInfo;





    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacherInfo(String teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public String getTeacherInfo() {
        return teacherInfo;
    }

    //    public int getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }

    public OpenClass getOpenclassInfo() {return openclassInfo;
    }

    public void setOpenclassInfo(OpenClass openclassInfo) {this.openclassInfo = openclassInfo;
    }



}
