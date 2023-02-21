package com.zenghao.service;


import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.UserLoginQo;
import com.zenghao.dto.req.UserRegisterQo;
import com.zenghao.dto.resp.UserLoginPo;
import com.zenghao.entity.User;

public interface UserService {
    RestResp<UserLoginPo> login(UserLoginQo user);

    RestResp logout();

    RestResp register(UserRegisterQo userRegisterQo);
}
