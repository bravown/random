package com.wn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.entity.User;
import com.wn.mapper.UserMapper;
import com.wn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王宁 2021/11/22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }

    @Override
    public int addUser() {
        User user = new User();
        user.setName("陈").setAge(13);
        return userMapper.addUser(user);
    }
    @Override
    public int selectUser() {
        LambdaQueryWrapper<User> queryLambda = Wrappers.<User>lambdaQuery().eq(User::getAge, 12);
        List<User> list = list(queryLambda);
        System.out.println(list);
        return 1;
    }
}
