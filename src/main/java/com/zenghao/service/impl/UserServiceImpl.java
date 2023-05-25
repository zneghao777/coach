package com.zenghao.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zenghao.core.common.constant.ErrorCodeEnum;
import com.zenghao.core.common.exception.BusinessException;
import com.zenghao.core.common.resp.RestResp;
import com.zenghao.core.common.util.JwtUtil;
import com.zenghao.core.common.util.RedisCache;
import com.zenghao.core.common.util.SimpleKeyUtils;
import com.zenghao.dto.req.*;
import com.zenghao.dto.resp.UserInfoPo;
import com.zenghao.dto.resp.UserLoginPo;
import com.zenghao.entity.LoginUser;
import com.zenghao.entity.Reservation;
import com.zenghao.entity.User;
import com.zenghao.entity.UserInfo;
import com.zenghao.mapper.ReservationMapper;
import com.zenghao.mapper.UserInfoMapper;
import com.zenghao.mapper.UserMapper;
import com.zenghao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserInfoMapper userInfoMapper;

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    private final ReservationMapper reservationMapper;

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
            return new RestResp(ErrorCodeEnum.USER_NAME_EXIST);
        }
        //判断用户手机号是否已经存在
        LambdaQueryWrapper<User> userQueryWrapper2 = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getPhone,userRegisterQo.getPhone());
        User user2 = null;
        try {
            user2 = userMapper.selectOne(userQueryWrapper2);
        } catch (Exception e) {
            return new RestResp(ErrorCodeEnum.USER_PHONE_EXIST);
        }
        if(Objects.nonNull(user2)){
            return new RestResp(ErrorCodeEnum.USER_PHONE_EXIST);
        }
        User userRegister = new User();
        userRegister.setUserName(userRegisterQo.getUserName());
        userRegister.setPassword(userRegisterQo.getPassword());
        userRegister.setPhone(userRegisterQo.getPhone());
        userRegister.setCreateTime(new Date());
        userMapper.insert(userRegister);
        return new RestResp(ErrorCodeEnum.REGISTER_OK);
    }

    @Override
    public RestResp userInfoAdd(UserInfoQo userInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(userInfo,info);
        info.setUserId((long) Math.toIntExact(loginUser.getUser().getId()));
        userInfoMapper.insert(info);
        return RestResp.ok("操作成功");
    }

    @Override
    public RestResp<UserInfoPo> userInfo(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoLambdaQueryWrapper.eq(UserInfo::getUserId,user.getId());
        UserInfo info = userInfoMapper.selectOne(userInfoLambdaQueryWrapper);
        UserInfoPo userInfoPo = new UserInfoPo();
        BeanUtils.copyProperties(info,userInfoPo);
        return RestResp.ok(userInfoPo);
    }

    @Override
    public RestResp userInfoUpdate(UserInfoQo userInfo) {

        LambdaUpdateWrapper<UserInfo> userInfoWrapper = new LambdaUpdateWrapper<>();
        userInfoWrapper.eq(UserInfo::getUserName,userInfo.getUserName())
                .set(UserInfo::getHobby,userInfo.getHobby())
                .set(UserInfo::getHeight,userInfo.getHeight())
                .set(UserInfo::getBodyFat,userInfo.getBodyFat())
                .set(UserInfo::getWeight,userInfo.getWeight());
        userInfoMapper.update(null,userInfoWrapper);
        return RestResp.ok("修改成功");
    }

    @Override
    public RestResp reservation(ReservationQo reservationQo) {
        Reservation reservation = new Reservation();
        reservation.setId(SimpleKeyUtils.genShortUuid());
        reservation.setType(reservationQo.getType());
        reservation.setCoachName(reservationQo.getCoachName());
        reservation.setCourseName(reservationQo.getCourseName());
        reservation.setUserName(reservationQo.getUserName());
        reservation.setReservationTime(reservationQo.getReservationTime());
        reservation.setRemark(reservationQo.getRemark());
        reservationMapper.insert(reservation);
        return RestResp.ok("预约成功！");
    }

    @Override
    public RestResp reservationDelete(String id) {
        reservationMapper.deleteById(id);
        return RestResp.ok("取消成功！");
    }

    @Override
    public RestResp login2(LoginQo user) {

        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getUserName,user.getUserName());
        User user1 = userMapper.selectOne(userQueryWrapper);
        if (user1 == null){
            return RestResp.error();
        }
        if (user.getPassword().equals(user1.getPassword())){
            return RestResp.ok(user1.getUserName());
        }else {
            return RestResp.error();
        }
    }

    @Override
    public RestResp reservationList(String username) {
        LambdaQueryWrapper<Reservation> QueryWrapper = new LambdaQueryWrapper<>();
        QueryWrapper.eq(Reservation::getUserName,username);
        List<Reservation> reservationList = reservationMapper.selectList(QueryWrapper);
        return RestResp.ok(reservationList);
    }
}
