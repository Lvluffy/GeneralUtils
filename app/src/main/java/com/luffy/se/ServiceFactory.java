package com.luffy.se;

public class ServiceFactory {
    public static IService makeService() {
        if (true) {
            return new BaseService();
        } else {
            return new SimpleService();
        }
    }
}
