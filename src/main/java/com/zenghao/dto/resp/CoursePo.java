package com.zenghao.dto.resp;

import lombok.Data;

@Data
public class CoursePo {

    private String id;

    private String name;

    private Integer duration;

    private String description;

    private String price;

    private String coach;

    private String urls;

    private String time;
}
