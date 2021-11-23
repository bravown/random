package com.wn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wn.entity.User;
import java.util.List;

/**
 * @author 王宁 2021/11/22
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUser();
    int addUser(User user);
}
