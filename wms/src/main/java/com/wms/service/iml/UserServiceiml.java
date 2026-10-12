package com.wms.service.iml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.mapper.UserMapper;
import com.wms.service.UserService;
import com.wms.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceiml extends ServiceImpl<UserMapper, User> implements UserService {
}
