package com.zenghao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String userName;

    private String avatar;

    private String phone;

    private String height;

    private String weight;

    private String hobby;

    private String bodyFat;

}
