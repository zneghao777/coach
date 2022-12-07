package com.zenghao.controller.coach;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.resp.CoachPo;
import com.zenghao.service.CoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coach")
@Api(tags = "教练相关接口")
public class CoachController {


    private final CoachService coachService;

    @ApiOperation(value = "查询所有")
    @GetMapping ("/selectList")
    public RestResp<List<CoachPo>> selectList(){

        return coachService.selectList();
    }

}
