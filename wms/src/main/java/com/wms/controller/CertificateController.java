package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.common.Result;
import com.wms.entity.Certificate;
import com.wms.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cert")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    // 查某船员所有证书
    @GetMapping("/list/{crewId}")
    public Result<List<Certificate>> list(@PathVariable Long crewId) {
        return Result.ok(certificateService.list(
                new QueryWrapper<Certificate>().eq("crew_id", crewId)));
    }

    // 新增
    @PostMapping
    public Result<?> add(@RequestBody Certificate cert) {
        certificateService.save(cert);
        return Result.ok();
    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody Certificate cert) {
        certificateService.updateById(cert);
        return Result.ok();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        certificateService.removeById(id);
        return Result.ok();
    }

    // 到期预警（30天内）
    @GetMapping("/expiring")
    public Result<List<Certificate>> expiring() {
        LocalDate today = LocalDate.now();
        return Result.ok(certificateService.list(
                new QueryWrapper<Certificate>()
                        .ge("expire_date", today)
                        .le("expire_date", today.plusDays(30))));
    }
}
