package com.zenghao.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginQo {

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户密码")
    private String password;

}
