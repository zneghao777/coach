package com.zenghao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String userName;

    private String password;

    private String phone;

    private Date createTime;

    private Date updateTime;
}
