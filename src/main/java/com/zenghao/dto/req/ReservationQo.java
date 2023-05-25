package com.zenghao.dto.req;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationQo {

    private Integer type;

    private String coachName;

    private String courseName;

    private String userName;

    private Date reservationTime;

    private String remark;

}
