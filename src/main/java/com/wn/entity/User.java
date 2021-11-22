package com.wn.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author 王宁 2021/10/12
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
