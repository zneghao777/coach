package com.zenghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zenghao.entity.User;
import com.zenghao.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
