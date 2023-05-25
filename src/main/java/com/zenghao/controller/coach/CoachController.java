package com.zenghao.controller.coach;

import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.CoachAddQo;
import com.zenghao.dto.resp.CoachPo;
import com.zenghao.service.CoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coach")
@Api(value = "教练相关",tags = "教练相关")
public class CoachController {

    private final CoachService coachService;

    @Operation(summary = "查询所有")
    @GetMapping ("/selectList")
    public RestResp<List<CoachPo>> selectList(){
        return coachService.selectList();
    }

    @Operation(summary = "教练新增")
    @GetMapping ("/add")
    public RestResp add(CoachAddQo coachAddQo){
        return coachService.add(coachAddQo);
    }

    @Operation(summary = "根据id查询")
    @GetMapping ("/selectById")
    public RestResp<CoachPo> selectById(String id){
        return coachService.selectById(id);
    }


}
