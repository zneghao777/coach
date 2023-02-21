package com.zenghao.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterPo {

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "手机号")
    private String phone;
}
