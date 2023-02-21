package com.zenghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zenghao.core.common.constant.ErrorCodeEnum;
import com.zenghao.core.common.exception.BusinessException;
import com.zenghao.entity.LoginUser;
import com.zenghao.entity.User;
import com.zenghao.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(user)){
            throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_ERROR);
        }
        //封装成UserDetails对象返回
        return new LoginUser(user);
    }


}
