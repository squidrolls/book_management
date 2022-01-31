package com.example.bookmanagement.domain.Vo;

import com.example.bookmanagement.domain.Class;
import com.example.bookmanagement.domain.UploadAppendKey;

import java.util.Date;
import java.util.List;

public class OpenClassVo {
    private String openId;

    private Integer teacherId;

    private Date startYear;

    private String startTerm;

    private Integer grade;

    private String major;

    Class classInfo;

    List<UploadAppendKey> uploadAppendKeys;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public String getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(String startTerm) {
        this.startTerm = startTerm == null ? null : startTerm.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Class getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(Class classInfo) {
        this.classInfo = classInfo;
    }

    public List<UploadAppendKey> getUploadAppendKeys() {
        return uploadAppendKeys;
    }

    public void setUploadAppendKeys(List<UploadAppendKey> uploadAppendKeys) {
        this.uploadAppendKeys = uploadAppendKeys;
    }
}
