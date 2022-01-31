package com.example.bookmanagement.domain;

public class Elink {
    private String isbn;

    private String elink;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getElink() {
        return elink;
    }

    public void setElink(String elink) {
        this.elink = elink == null ? null : elink.trim();
    }
}