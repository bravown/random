package com.wn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wn.entity.User;

import java.util.List;

/**
 * @author 王宁 2021/11/22
 */
public interface UserService extends IService<User> {

    List<User> queryUser();

    int addUser();

    int selectUser();
}
