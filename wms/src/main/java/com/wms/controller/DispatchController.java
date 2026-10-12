package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.common.Result;
import com.wms.entity.Crew;
import com.wms.entity.Dispatch;
import com.wms.service.CrewService;
import com.wms.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private CrewService crewService;

    // 查某船员所有派员记录
    @GetMapping("/list/{crewId}")
    public Result<List<Dispatch>> list(@PathVariable Long crewId) {
        return Result.ok(dispatchService.list(
                new QueryWrapper<Dispatch>().eq("crew_id", crewId)
                        .orderByDesc("onboard_date")));
    }

    // 查某船当前在船人员
    @GetMapping("/ship/{shipName}")
    public Result<List<Dispatch>> byShip(@PathVariable String shipName) {
        return Result.ok(dispatchService.list(
                new QueryWrapper<Dispatch>()
                        .eq("ship_name", shipName)
                        .eq("status", "在船")));
    }

    // 派员上船（含状态联动）
    @PostMapping("/onboard")
    @Transactional
    public Result<?> onboard(@RequestBody Dispatch d) {
        Crew crew = crewService.getById(d.getCrewId());
        if (crew == null) return Result.fail("船员不存在");
        if ("在船".equals(crew.getStatus())) return Result.fail("该船员已在船");

        d.setOnboardDate(LocalDate.now());
        d.setStatus("在船");
        dispatchService.save(d);

        crew.setStatus("在船");
        crewService.updateById(crew);
        return Result.ok();
    }

    // 下船（含状态联动）
    @PostMapping("/offboard/{id}")
    @Transactional
    public Result<?> offboard(@PathVariable Long id) {
        Dispatch d = dispatchService.getById(id);
        if (d == null || "已下船".equals(d.getStatus())) {
            return Result.fail("记录无效");
        }
        d.setOffboardDate(LocalDate.now());
        d.setStatus("已下船");
        dispatchService.updateById(d);

        Crew crew = crewService.getById(d.getCrewId());
        crew.setStatus("在岸");
        crewService.updateById(crew);
        return Result.ok();
    }
}
