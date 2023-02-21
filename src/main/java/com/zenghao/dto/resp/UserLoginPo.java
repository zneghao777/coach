package com.zenghao.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginPo {

    @Schema(description = "用户id")
    private String id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "token")
    private String token;
}
