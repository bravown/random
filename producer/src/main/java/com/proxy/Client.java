package com.proxy;

public class Client {
    public static void main(String[] args) {
        DidiCompany dc = new DidiCompany();
        Didi didi = new DidiCallCenterProxy(dc);//生成代理对象
        didi.complain();//投诉
        didi.queryCarRecord();//查询出车记录
    }
}