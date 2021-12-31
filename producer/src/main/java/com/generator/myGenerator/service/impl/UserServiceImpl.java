package com.generator.myGenerator.service.impl;

import com.generator.myGenerator.entity.User;
import com.generator.myGenerator.mapper.UserMapper;
import com.generator.myGenerator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangning
 * @since 2021-12-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
