package com.xiaoxi.javademo.test1;

public class Test {
    public static Test getInstance() {
        return Instance.INSTANCE;
    }

    private static class Instance{
        private static final Test INSTANCE = new Test();
    }
}
