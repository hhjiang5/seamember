package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.common.Result;
import com.wms.entity.*;
import com.wms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stat")
public class StatController {

    @Autowired private CrewService crewService;
    @Autowired private CertificateService certificateService;
    @Autowired private ContractService contractService;
    @Autowired private DispatchService dispatchService;

    // 已有：总览
    @GetMapping("/overview")
    public Result<?> overview() {
        Map<String, Object> map = new HashMap<>();
        map.put("totalCrew", crewService.count());
        map.put("onboardCount", crewService.count(new QueryWrapper<Crew>().eq("status", "在船")));
        map.put("onshoreCount", crewService.count(new QueryWrapper<Crew>().eq("status", "在岸")));
        map.put("expiringCert", certificateService.count(
                new QueryWrapper<Certificate>()
                        .ge("expire_date", java.time.LocalDate.now())
                        .le("expire_date", java.time.LocalDate.now().plusDays(30))));
        map.put("expiringContract", contractService.count(
                new QueryWrapper<Contract>()
                        .ge("end_date", java.time.LocalDate.now())
                        .le("end_date", java.time.LocalDate.now().plusDays(30))));
        return Result.ok(map);
    }

    // 新增①：职务分布
    @GetMapping("/position")
    public Result<?> positionStat() {
        return Result.ok(crewService.listMaps(
                new QueryWrapper<Crew>()
                        .select("position, count(*) as num")
                        .groupBy("position")));
    }

    // 新增②：状态分布
    @GetMapping("/status")
    public Result<?> statusStat() {
        return Result.ok(crewService.listMaps(
                new QueryWrapper<Crew>()
                        .select("status, count(*) as num")
                        .groupBy("status")));
    }

    // 新增③：船舶分布（在船船员按船名）
    @GetMapping("/ship")
    public Result<?> shipStat() {
        return Result.ok(dispatchService.listMaps(
                new QueryWrapper<Dispatch>()
                        .select("ship_name, count(*) as num")
                        .eq("status", "在船")
                        .groupBy("ship_name")));
    }
}
