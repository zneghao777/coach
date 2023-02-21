package com.zenghao.controller.user;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.UserLoginQo;
import com.zenghao.dto.req.UserRegisterQo;
import com.zenghao.dto.resp.UserLoginPo;
import com.zenghao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(value = "用户相关",tags = "用户相关")
public class UserController {

    private final UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public RestResp<UserLoginPo> login(@RequestBody UserLoginQo user){
        return userService.login(user);
    }

    @Operation(summary = "用户退出登录")
    @PostMapping("/logout")
    public RestResp logout(){
        return userService.logout();
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public RestResp register(@Valid @RequestBody UserRegisterQo userRegisterQo){
        return userService.register(userRegisterQo);
    }


}
