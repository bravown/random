package com.wn.service;

import com.wn.entity.User;

import java.util.List;

/**
 * @author 王宁 2021/11/22
 */
public interface UserService {

    List<User> queryUser();

    int addUser();
}
