package com.wn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 公司类
 *
 * @author 王宁 2021/10/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private Long id;
    private String name;
    private String address;
    private List<String> region;
    private Map<String,String> boss;
    private List<User> staff;

    public Team(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
