package com.wn.controller;

import com.wn.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * 第一个控制器
 *
 * @author 王宁 2021/10/12
 */
@RestController
@RequestMapping("one")
public class OneController {

    @PostMapping("/one")
    public String first(@RequestBody User name) {
        System.out.println(name);
        return name.toString();
    }
    @GetMapping("/two")
    public String second(@RequestBody User name, @RequestParam("token") String token) {
        System.out.println(name+"/br");
        System.out.println(token);
        return token+"->"+name;
    }
}
