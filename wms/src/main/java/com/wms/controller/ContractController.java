package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.Result;
import com.wms.entity.Contract;
import com.wms.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    // 分页查询
    @GetMapping("/page")
    public Result<Page<Contract>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Long crewId) {

        QueryWrapper<Contract> qw = new QueryWrapper<>();
        if (crewId != null) qw.eq("crew_id", crewId);
        qw.orderByDesc("id");
        return Result.ok(contractService.page(new Page<>(pageNum, pageSize), qw));
    }

    // 查某船员所有合同
    @GetMapping("/list/{crewId}")
    public Result<List<Contract>> listByCrew(@PathVariable Long crewId) {
        return Result.ok(contractService.list(
                new QueryWrapper<Contract>().eq("crew_id", crewId)));
    }

    // 新增
    @PostMapping
    public Result<?> add(@RequestBody Contract contract) {
        contractService.save(contract);
        return Result.ok();
    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody Contract contract) {
        contractService.updateById(contract);
        return Result.ok();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        contractService.removeById(id);
        return Result.ok();
    }

    // 到期提醒（30天内）
    @GetMapping("/expiring")
    public Result<List<Contract>> expiring() {
        LocalDate today = LocalDate.now();
        return Result.ok(contractService.list(
                new QueryWrapper<Contract>()
                        .ge("end_date", today)
                        .le("end_date", today.plusDays(30))));
    }
}