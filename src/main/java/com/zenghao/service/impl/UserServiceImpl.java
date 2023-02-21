package com.zenghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zenghao.core.common.constant.ErrorCodeEnum;
import com.zenghao.core.common.exception.BusinessException;
import com.zenghao.core.common.resp.RestResp;
import com.zenghao.core.common.util.JwtUtil;
import com.zenghao.core.common.util.RedisCache;
import com.zenghao.dto.req.UserLoginQo;
import com.zenghao.dto.req.UserRegisterQo;
import com.zenghao.dto.resp.UserLoginPo;
import com.zenghao.entity.LoginUser;
import com.zenghao.entity.User;
import com.zenghao.mapper.UserMapper;
import com.zenghao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    @Override
    public RestResp<UserLoginPo> login(UserLoginQo user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出相应的提示
        if(Objects.isNull(authenticate)){
            throw new BusinessException(ErrorCodeEnum.USER_ERROR);
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        return RestResp.ok(UserLoginPo.builder()
                .userName(user.getUserName())
                .token(jwt)
                .build());
    }

    @Override
    public RestResp logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userId);
        return new RestResp(ErrorCodeEnum.LOGIN_OUT);
    }

    @Override
    public RestResp register(UserRegisterQo userRegisterQo) {
        //判断用户名是否已经存在
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getUserName,userRegisterQo.getUserName());
        User user = userMapper.selectOne(userQueryWrapper);
        if(Objects.nonNull(user)){
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }
        //判断用户手机号是否已经存在
        LambdaQueryWrapper<User> userQueryWrapper2 = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getPhone,userRegisterQo.getPhone());
        User user2 = userMapper.selectOne(userQueryWrapper2);
        if(Objects.nonNull(user2)){
            throw new BusinessException(ErrorCodeEnum.USER_PHONE_EXIST);
        }
        User userRegister = new User();
        userRegister.setUserName(userRegisterQo.getUserName());
        String encode = new BCryptPasswordEncoder().encode(userRegisterQo.getPassword());
        userRegister.setPassword(encode);
        userRegister.setPhone(userRegisterQo.getPhone());
        userRegister.setCreateTime(new Date());
        userMapper.insert(userRegister);
        return new RestResp(ErrorCodeEnum.REGISTER_OK);
    }
}
