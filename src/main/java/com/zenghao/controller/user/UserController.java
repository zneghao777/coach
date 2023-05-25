package com.zenghao.controller.user;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.*;
import com.zenghao.dto.resp.UserInfoPo;
import com.zenghao.dto.resp.UserLoginPo;
import com.zenghao.entity.Reservation;
import com.zenghao.service.ArtService;
import com.zenghao.service.IcosFileService;
import com.zenghao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(value = "用户相关",tags = "用户相关")
public class UserController {

    private final UserService userService;

    private final IcosFileService cosFileService;

    private final ArtService artService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public RestResp<UserLoginPo> login(@RequestBody UserLoginQo user){
        return userService.login(user);
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login2")
    public RestResp login2(@RequestBody LoginQo user){
        return userService.login2(user);
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

    @Operation(summary = "用户信息新增")
    @PostMapping("/userInfoAdd")
    public RestResp userInfoAdd(@RequestBody UserInfoQo userInfo){
        return userService.userInfoAdd(userInfo);
    }

    @Operation(summary = "用户信息查询")
    @GetMapping("/userInfo")
    public RestResp<UserInfoPo> userInfo(String username){
        return userService.userInfo(username);
    }

    @Operation(summary = "用户信息更新")
    @PostMapping("/userInfoUpdate")
    public RestResp userInfoUpdate(@RequestBody UserInfoQo userInfo){
        return userService.userInfoUpdate(userInfo);
    }

    @Operation(summary = "用户预约教练，团课")
    @PostMapping("/reservation")
    public RestResp reservation(@RequestBody ReservationQo reservationQo){
        return userService.reservation(reservationQo);
    }

    @Operation(summary = "用户预约教练，团课取消预约")
    @PostMapping("/reservationDelete")
    public RestResp reservationDelete(String id){
        return userService.reservationDelete(id);
    }

    @Operation(summary = "预约信息查询")
    @GetMapping("/reservationList")
    public RestResp reservationList(String username){
        return userService.reservationList(username);
    }

    @Operation(summary = "cos上传")
    @PostMapping("/cosUpload")
    public RestResp cosUpload(@RequestPart("file") MultipartFile file){
        return cosFileService.cosUpload(file);
    }

    @Operation(summary = "健身常识查询")
    @GetMapping("/artList")
    public RestResp artList(){
        return artService.artList();
    }

    @Operation(summary = "健身常识id查询")
    @GetMapping("/artById")
    public RestResp artById(String id){
        return artService.artById(id);
    }

}
