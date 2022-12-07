package com.zenghao.service.impl;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.resp.CoachPo;
import com.zenghao.mapper.CoachMapper;
import com.zenghao.service.CoachService;
import lombok.RequiredArgsConstructor;
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
}
