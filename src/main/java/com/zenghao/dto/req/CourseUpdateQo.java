package com.zenghao.dto.req;

import lombok.Data;

@Data
public class CourseUpdateQo {

    private String id;

    private String name;

    private Integer duration;

    private String description;

    private String courseId;

    private String coachId;

    private String price;

    private String time;
}
