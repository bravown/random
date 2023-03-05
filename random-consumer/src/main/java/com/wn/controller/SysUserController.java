package com.wn.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 王宁
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @GetMapping()
    public void hello() {

    }
    @PostMapping()
    public void helloWorld() {

    }

    @RequestMapping()
    public void helloWorld2() {

    }
}

