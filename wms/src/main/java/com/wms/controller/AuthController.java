package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.common.Result;
import com.wms.entity.User;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // 登录
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User req) {
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", req.getUsername())
                .eq("password", req.getPassword()));
        if (user == null) return Result.fail("用户名或密码错误");

        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("role", user.getRole());
        data.put("realName", user.getRealName());
        return Result.ok(data);
    }
}
