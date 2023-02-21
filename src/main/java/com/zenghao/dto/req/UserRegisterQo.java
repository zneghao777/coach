package com.zenghao.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterQo {

    @Schema(description = "用户名",required = true)
    @NotBlank(message="用户不能为空！")
    private String userName;

    @Schema(description = "用户密码",required = true)
    @NotBlank(message="密码不能为空！")
    private String password;

    @Schema(description = "手机号",required = true)
    @NotBlank(message="手机号不能为空！")
    @Pattern(regexp="^1[3|4|5|6|7|8|9][0-9]{9}$",message="手机号格式不正确！")
    private String phone;
}
