package com.proxy;

public class DidiCompany implements Didi {

    public void complain() {
        System.out.println("收到投诉，请耐心等待处理结果");
    }

    public void queryCarRecord() {
        System.out.println("正在查询出车记录，请耐心等待");
    }

}
