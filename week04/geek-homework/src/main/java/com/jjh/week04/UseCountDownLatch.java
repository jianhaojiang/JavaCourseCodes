package com.jjh.week04;

import java.util.concurrent.CountDownLatch;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 22:00
 **/
public class UseCountDownLatch {

    private static String hello = "1111";

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new ThreadSub(countDownLatch)).start();
        countDownLatch.await();
        System.out.println(hello);

    }

    public static class ThreadSub implements Runnable {

        CountDownLatch countDownLatch;

        public ThreadSub(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                hello = "hello geek";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

}
