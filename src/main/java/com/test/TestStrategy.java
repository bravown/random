package com.test;

public class TestStrategy {
    public static void main(String[] args) {
        Strategy redPen = new RedPen();
        Strategy greenPen = new GreenPen();
        Strategy bluePen = new BluePen();

        Context context = new Context();
        context.setStrategy(redPen);
        context.executeDraw();

        // 切换策略
        context.setStrategy(bluePen);
        context.executeDraw();
    }
}
