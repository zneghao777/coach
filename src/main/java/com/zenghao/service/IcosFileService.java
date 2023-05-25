package com.zenghao.service;

import com.zenghao.core.common.resp.RestResp;
import org.springframework.web.multipart.MultipartFile;

public interface IcosFileService {

    RestResp cosUpload(MultipartFile file);
}
