package com.proxy;

public class DidiCallCenterProxy implements Didi {
    DidiCompany didi;

    public DidiCallCenterProxy(DidiCompany didi) {
        this.didi = didi;
    }

    public void complain() {
        didi.complain();//调用主题的方法
    }

    public void queryCarRecord() {
        System.out.println("不好意思，一线客服没有权限，请去总公司查询");
    }

}
