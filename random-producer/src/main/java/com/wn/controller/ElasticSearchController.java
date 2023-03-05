package com.wn.controller;

import com.wn.entity.EsEvent;
import com.wn.utils.ElasticsearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王宁 2021/12/17
 */
@RestController
@RequestMapping("es")
public class ElasticSearchController {

    @Autowired
    ElasticsearchUtil elasticsearchUtil;

    /**
     * 创建一个索引
     *
     * @param
     * @author 王宁 2021/12/17
     */
    @PostMapping("/createIndex")
    public String createIndex() {
        try {
            elasticsearchUtil.createIndexIfNotExist(EsEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
