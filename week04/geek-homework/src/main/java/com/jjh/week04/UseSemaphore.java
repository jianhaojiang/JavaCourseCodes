package com.jjh.week04;

import java.util.concurrent.Semaphore;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 22:19
 **/
public class UseSemaphore {
    static Semaphore semaphore = new Semaphore(0);
    private static String hello = "1111";

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadSub()).start();
        //信号为0，则进入休眠
        semaphore.acquire();
        System.out.println(hello);
    }

    public static class ThreadSub implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                hello = "hello geek";
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
