package com.wn.controller;

import com.wn.entity.User;
import com.wn.service.UserService;
import com.wn.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 第一个控制器
 *
 * @author 王宁 2021/10/12
 */
@RestController
@RequestMapping("one")
public class OneController {

    @Autowired
    private UserService userService;

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
        return userService.queryUser();
    }

    @PostMapping("/addUser")
    public int addUser() {
        int i = userService.addUser();
        return i;
    }
    @PostMapping("/selectUser")
    public int selectUser() {
        int i = userService.selectUser();
        return i;
    }
}