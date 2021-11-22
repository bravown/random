package com.wn.mapper;

import com.wn.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王宁 2021/11/22
 */
@Component
public interface UserMapper {

    List<User> queryUser();
    int addUser(User user);
}
