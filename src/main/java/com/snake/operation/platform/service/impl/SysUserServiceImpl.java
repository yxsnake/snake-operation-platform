package com.snake.operation.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.SysUserMapper;
import com.snake.operation.platform.model.entity.SysUser;
import com.snake.operation.platform.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
