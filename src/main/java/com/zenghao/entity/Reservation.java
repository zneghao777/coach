package com.zenghao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Reservation {

    @TableId
    private String id;

    private String userName;

    private Date reservationTime;

    private Integer type;

    private String coachName;

    private String courseName;

    private String remark;
}
