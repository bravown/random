package com.wn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户类
 *
 * @author 王宁 2021/10/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private Team team;
    private List<Object> list;

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(Long id, String name, Integer age, Team team, List<Object> list) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
        this.list = list;
    }

    public User(Long id, String name, Integer age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }
}
