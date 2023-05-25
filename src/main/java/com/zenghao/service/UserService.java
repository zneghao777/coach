package com.zenghao.service;


import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.*;
import com.zenghao.dto.resp.UserInfoPo;
import com.zenghao.dto.resp.UserLoginPo;
import com.zenghao.entity.Reservation;

public interface UserService {
    RestResp<UserLoginPo> login(UserLoginQo user);

    RestResp logout();

    RestResp register(UserRegisterQo userRegisterQo);

    RestResp userInfoAdd(UserInfoQo userInfo);

    RestResp<UserInfoPo> userInfo(String username);

    RestResp userInfoUpdate(UserInfoQo userInfo);

    RestResp reservation(ReservationQo reservationQo);

    RestResp reservationDelete(String id);

    RestResp login2(LoginQo user);

    RestResp reservationList(String username);
}
