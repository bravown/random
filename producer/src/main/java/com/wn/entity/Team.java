package com.wn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
