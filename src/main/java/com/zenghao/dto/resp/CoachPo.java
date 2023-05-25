package com.zenghao.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CoachPo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    public CoachPo(String id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public CoachPo() {

    }
}
