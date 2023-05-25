package com.zenghao.service;

import com.zenghao.core.common.resp.RestResp;

public interface ArtService {


    RestResp artList();

    RestResp artById(String id);
}
