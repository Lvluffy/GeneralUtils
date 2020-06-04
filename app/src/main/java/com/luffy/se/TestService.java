package com.luffy.se;

public class TestService {
    private IService mService;

    public TestService() {
        mService = ServiceFactory.makeService();
    }

    public static void main(String[] args) {
        TestService mTestService = new TestService();
        if (mTestService.mService instanceof SimpleService) {
            System.out.println(mTestService.mService.getResult() + "---" + ((SimpleService) mTestService.mService).mReader);
        } else if (mTestService.mService instanceof BaseService) {
            System.out.println(mTestService.mService.getResult() + "---" + ((BaseService) mTestService.mService).mReader);
        }
    }
}
