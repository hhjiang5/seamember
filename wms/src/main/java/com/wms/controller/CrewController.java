package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.Result;
import com.wms.entity.Crew;
import com.wms.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/crew")

public class CrewController {

     @Autowired
    private CrewService crewService;

     @GetMapping("/page")
     public Result<Page<Crew>> page(
             @RequestParam(defaultValue = "1") Integer pageNum,
             @RequestParam(defaultValue = "10") Integer pageSize,
             @RequestParam(required = false) String name,
             @RequestParam(required = false) String status,
             @RequestParam(required = false) String crewNo)
     {
         QueryWrapper<Crew> qw = new QueryWrapper<>();
         if (StringUtils.hasText(name)) qw.like("name", name);
         if (StringUtils.hasText(crewNo)) qw.like("crew_no", crewNo);
         if (StringUtils.hasText(status)) qw.eq("status", status);
         qw.orderByDesc("id");

         return Result.ok(crewService.page(new Page<>(pageNum, pageSize), qw));

    }

    @GetMapping("/{id}")
    public Result<Crew> getById(@PathVariable Long id) {
        return Result.ok(crewService.getById(id));
    }

    @GetMapping("/idCard/{idCard}")
    public Result<Crew> getByIdCard(@PathVariable String idCard) {
        return Result.ok(crewService.getOne(
                new QueryWrapper<Crew>().eq("id_card", idCard)));
    }

    @GetMapping("/CrewNo/{CrewNo}")
    public Result<Crew> getByCrewNo(@PathVariable String CrewNo) {
        return Result.ok(crewService.getOne(
                new QueryWrapper<Crew>().eq("crew_no", CrewNo)));
    }

    @PostMapping
    public Result<?> add(@Valid @RequestBody Crew crew) {
        crewService.save(crew);
        return Result.ok();
    }

    @PutMapping
    public Result<?> update(@Valid @RequestBody Crew crew) {
        crewService.updateById(crew);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        crewService.removeById(id);
        return Result.ok();
    }
}
