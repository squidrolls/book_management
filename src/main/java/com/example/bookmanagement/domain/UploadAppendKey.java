package com.example.bookmanagement.domain;

public class UploadAppendKey {
    private String openId;

    private String appendix;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getAppendix() {
        return appendix;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix == null ? null : appendix.trim();
    }
}