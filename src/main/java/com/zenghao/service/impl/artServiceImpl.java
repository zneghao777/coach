package com.zenghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zenghao.core.common.resp.RestResp;
import com.zenghao.entity.Articles;
import com.zenghao.mapper.ArtMapper;
import com.zenghao.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class artServiceImpl implements ArtService {

    private final ArtMapper artMapper;

    @Override
    public RestResp artList() {
        LambdaQueryWrapper<Articles> articlesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Articles> articles = artMapper.selectList(articlesLambdaQueryWrapper);
        return RestResp.ok(articles);
    }

    @Override
    public RestResp artById(String id) {
        LambdaQueryWrapper<Articles> articlesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articlesLambdaQueryWrapper.eq(Articles::getId,id);
        Articles articles = artMapper.selectOne(articlesLambdaQueryWrapper);

        return RestResp.ok(articles);
    }
}
