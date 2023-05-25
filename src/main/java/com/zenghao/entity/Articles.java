package com.zenghao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Articles {

    private Integer id;

    private String title;

    private String content;

    private String author;

    private String image;

    private Date date;

}