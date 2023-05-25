package com.zenghao.dto.req;

import lombok.Data;

@Data
public class CourserAddQo {

    private String name;

    private Integer duration;

    private String description;

    private String price;

    private String coachId;

    private String time;
}
