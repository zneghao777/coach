package com.zenghao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class Coach {

    @ApiModelProperty("id")
    private Integer id;

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
