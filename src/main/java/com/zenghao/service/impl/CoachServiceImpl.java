package com.zenghao.service.impl;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.core.common.util.SimpleKeyUtils;
import com.zenghao.dto.req.CoachAddQo;
import com.zenghao.dto.resp.CoachPo;
import com.zenghao.entity.Coach;
import com.zenghao.mapper.CoachMapper;
import com.zenghao.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachMapper coachMapper;

    @Override
    public RestResp<List<CoachPo>> selectList() {

        List<CoachPo> coachPoList = coachMapper.selectList(null).stream().map(v ->
                        new CoachPo(v.getId(),v.getName(),v.getDescription(),v.getImageUrl()))
                .collect(Collectors.toList());

        return RestResp.ok(coachPoList);

    }

    @Override
    public RestResp add(CoachAddQo coachAddQo) {
        Coach coach = new Coach();
        BeanUtils.copyProperties(coachAddQo,coach);
        coach.setId(SimpleKeyUtils.genShortUuid());
        coachMapper.insert(coach);
        return RestResp.ok("新增成功！");
    }

    @Override
    public RestResp<CoachPo> selectById(String id){
        Coach coach = coachMapper.selectById(id);
        CoachPo coachPo = new CoachPo();
        BeanUtils.copyProperties(coach,coachPo);
        return RestResp.ok(coachPo);
    }
}
