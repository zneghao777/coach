package com.zenghao.service;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.CoachAddQo;
import com.zenghao.dto.resp.CoachPo;
import com.zenghao.entity.Coach;

import java.util.List;

public interface CoachService{


    RestResp<List<CoachPo>> selectList();

    RestResp add(CoachAddQo coachAddQo);

    RestResp<CoachPo> selectById(String id);
}
