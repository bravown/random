package com.wn.controller;


import com.wn.entity.User;
import com.wn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第一个控制器
 *
 * @author 王宁 2021/10/12
 */
@RestController
@RequestMapping("one")
public class OneController {

    @Resource(name = "userService2Impl")
    private UserService userService2;

    @Autowired
    private UserService userService2Impl;

    @PostMapping("/one")
    public String first(@RequestBody User name) {
        System.out.println(name);
        return name.toString();
    }

    @GetMapping("/two")
    public String second(@RequestBody User name, @RequestParam("token") String token) {
        System.out.println(name + "/br");
        System.out.println(token);
        return token + "->" + name;
    }

    @PostMapping("/queryUser")
    public List<User> queryUser() {
        return userService2Impl.queryUser();
    }

    @PostMapping("/addUser")
    public int addUser() {
        int i = userService2Impl.addUser();
        return i;
    }
    @PostMapping("/selectUser")
    public int selectUser() {
        int i = userService2Impl.selectUser();
        return i;
    }
}