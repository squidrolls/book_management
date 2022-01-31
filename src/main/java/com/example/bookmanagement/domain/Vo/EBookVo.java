package com.example.bookmanagement.domain.Vo;

import com.example.bookmanagement.domain.Textbook;

import java.util.Date;

public class EBookVo {
    Textbook textbook;
    String elink;

    public String getElink() {
        return elink;
    }

    public void setElink(String elink) {
        this.elink = elink;
    }

    public Textbook getTextbook() {
        return textbook;
    }

    public void setTextbook(Textbook textbook) {
        this.textbook = textbook;
    }
}
