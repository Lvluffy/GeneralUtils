package com.luffy.se;

public class SimpleService extends BaseService {

    public SimpleService() {
        mReader = "simple";
    }

    @Override
    public String getResult() {
        return SimpleService.class.getSimpleName();
    }
}
