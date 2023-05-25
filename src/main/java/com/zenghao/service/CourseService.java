package com.zenghao.service;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.CourseUpdateQo;
import com.zenghao.dto.req.CourserAddQo;
import com.zenghao.dto.resp.CoursePo;
import com.zenghao.entity.Courser;

import java.util.List;

public interface CourseService {
    RestResp add(CourserAddQo courserAddQo);

    RestResp update(CourseUpdateQo courseUpdateQo);

    RestResp<List<CoursePo>> selectList();

    Courser selectById(String id);
}
