package com.zenghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zenghao.core.common.resp.RestResp;
import com.zenghao.core.common.util.SimpleKeyUtils;
import com.zenghao.dto.req.CourseUpdateQo;
import com.zenghao.dto.req.CourserAddQo;
import com.zenghao.dto.resp.CoursePo;
import com.zenghao.entity.Coach;
import com.zenghao.entity.Courser;
import com.zenghao.mapper.CoachMapper;
import com.zenghao.mapper.CourserMapper;
import com.zenghao.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourserServiceImpl implements CourseService {

    private final CourserMapper courserMapper;

    private final CoachMapper coachMapper;

    @Override
    public RestResp add(CourserAddQo courserAddQo) {
        Courser courser = new Courser();
        courser.setId(SimpleKeyUtils.genShortUuid());
        courser.setDescription(courserAddQo.getDescription());
        courser.setDuration(courserAddQo.getDuration());
        courser.setCoachId(courser.getId());
        courser.setPrice(courserAddQo.getPrice());
        courser.setTime(courserAddQo.getTime());
        courser.setName(courserAddQo.getName());
        courserMapper.insert(courser);
        return RestResp.ok("新增成功！");
    }

    @Override
    public RestResp update(CourseUpdateQo courseUpdateQo) {
        LambdaUpdateWrapper<Courser> courserLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        courserLambdaUpdateWrapper.eq(Courser::getId,courseUpdateQo.getId())
                .set(Courser::getDuration,courseUpdateQo.getDuration())
                .set(Courser::getDescription,courseUpdateQo.getDescription())
                .set(Courser::getCoachId,courseUpdateQo.getCourseId())
                .set(Courser::getTime,courseUpdateQo.getTime())
                .set(Courser::getPrice,courseUpdateQo.getPrice())
                .set(Courser::getName,courseUpdateQo.getName());
        courserMapper.update(null,courserLambdaUpdateWrapper);
        return RestResp.ok("'修改成功");
    }

    @Override
    public RestResp<List<CoursePo>> selectList() {
        List<Courser> coursers = courserMapper.selectList(null);
        List<CoursePo> coursePos = new ArrayList<>();
        for (Courser courser : coursers) {
            String coachId = courser.getCoachId();
            Coach coach = coachMapper.selectById(coachId);
            CoursePo coursePo = new CoursePo();
            BeanUtils.copyProperties(courser,coursePo);
            coursePo.setCoach(coach.getName());
            coursePos.add(coursePo);
        }
        return RestResp.ok(coursePos);
    }

    @Override
    public Courser selectById(String id) {
        return courserMapper.selectById(id);
    }
}
