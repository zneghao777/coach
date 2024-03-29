package com.zenghao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class Coach {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("教练名称")
    private String coachName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("图片地址")
    private String imageUrl;

}
