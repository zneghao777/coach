package com.zenghao.controller.course;


import com.baomidou.mybatisplus.extension.api.R;
import com.zenghao.core.common.resp.RestResp;
import com.zenghao.dto.req.CourseUpdateQo;
import com.zenghao.dto.req.CourserAddQo;
import com.zenghao.dto.resp.CoursePo;
import com.zenghao.entity.Courser;
import com.zenghao.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courser")
@Api(value = "团课相关",tags = "团课相关")
public class CourserController {


    private final CourseService courseService;
    @Operation(summary = "团课新增")
    @PostMapping("/add")
    public RestResp add(@RequestBody CourserAddQo courserAddQo) {
        return courseService.add(courserAddQo);
    }

    @Operation(summary = "团课修改")
    @PostMapping("/update")
    public RestResp update(@RequestBody CourseUpdateQo courseUpdateQo) {
        return courseService.update(courseUpdateQo);
    }

    @Operation(summary = "团课查询")
    @GetMapping("/selectList")
    public RestResp<List<CoursePo>> selectList() {
        return courseService.selectList();
    }

    @Operation(summary = "查询团课详情")
    @GetMapping("/selectById")
    public RestResp queryRoot( String id) {
        Courser courser = courseService.selectById(id);
        return RestResp.ok(courser);
    }

}
