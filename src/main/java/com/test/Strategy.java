package com.test;

public interface Strategy {
    void draw();
}


class RedPen implements Strategy{

    @Override
    public void draw() {
        System.out.println("用红笔");
    }
}
class BluePen implements Strategy{

    @Override
    public void draw() {
        System.out.println("用蓝笔");
    }
}
class GreenPen implements Strategy{

    @Override
    public void draw() {
        System.out.println("用绿笔");
    }
}