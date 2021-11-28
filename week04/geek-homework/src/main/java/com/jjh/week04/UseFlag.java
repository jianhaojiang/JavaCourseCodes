package com.jjh.week04;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 22:36
 **/
public class UseFlag {

    static volatile boolean subFinish = false;
    private static String hello = "1111";

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadSub()).start();
        while (!subFinish){
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
                subFinish = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
