package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.Result;
import com.wms.entity.Training;
import com.wms.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/page")
    public Result<Page<Training>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Long crewId) {
        QueryWrapper<Training> qw = new QueryWrapper<>();
        if (crewId != null) qw.eq("crew_id", crewId);
        qw.orderByDesc("id");
        return Result.ok(trainingService.page(new Page<>(pageNum, pageSize), qw));
    }

    @GetMapping("/list/{crewId}")
    public Result<List<Training>> list(@PathVariable Long crewId) {
        return Result.ok(trainingService.list(
                new QueryWrapper<Training>().eq("crew_id", crewId)));
    }

    @PostMapping
    public Result<?> add(@RequestBody Training training) {
        trainingService.save(training);
        return Result.ok();
    }

    @PutMapping
    public Result<?> update(@RequestBody Training training) {
        trainingService.updateById(training);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        trainingService.removeById(id);
        return Result.ok();
    }
}
