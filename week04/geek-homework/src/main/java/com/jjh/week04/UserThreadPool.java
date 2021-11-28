package com.jjh.week04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 20:47
 **/
public class UserThreadPool {

    private static String hello = "1111";

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new ThreadSub());
        executorService.shutdown();
        while (!executorService.isTerminated()){
            Thread.sleep(100);
        }
        System.out.println(hello);
    }

    public static class ThreadSub implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                hello = "hello geek";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
