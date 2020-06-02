package com.luffy;


public class TestGeofence {

    private static final long[] FENCE_RADIUS_NUM_TIME = {3 * 1000, 3 * 1000, 18 * 1000};
    private static final int INIT_RETRY_MAX_NUM = FENCE_RADIUS_NUM_TIME.length + 1;

    private static void test() {
        int initCount = 0;
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        while (initCount++ < INIT_RETRY_MAX_NUM) {
            System.out.println("initCount == ：" + initCount);
            System.out.println("sleepTime == ：" + (System.currentTimeMillis() - startTime) / 1000);
            System.out.println("做事");
            if (initCount < INIT_RETRY_MAX_NUM) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(FENCE_RADIUS_NUM_TIME[initCount - 1]);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("所用时间：" + (endTime - startTime) / 1000);
    }

    public static void main(String[] args) {
        new Thread("我的测试任务") {
            @Override
            public void run() {
                test();
            }
        }.start();
    }
}
