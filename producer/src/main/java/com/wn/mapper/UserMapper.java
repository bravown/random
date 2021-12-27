package com.wn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wn.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 王宁 2021/11/22
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id = 46")
    List<User> queryUser();

    int addUser(User user);
}
