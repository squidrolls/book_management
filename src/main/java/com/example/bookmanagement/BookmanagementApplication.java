package com.example.bookmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.example.bookmanagement.mapper")
@ServletComponentScan
public class BookmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagementApplication.class, args);
    }

}
