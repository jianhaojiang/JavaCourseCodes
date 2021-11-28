package com.jjh.week04;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 20:47
 **/
public class UserJoin {

    private static String hello = "1111";

    public static void main(String[] args) throws InterruptedException {
        Thread sub = new Thread(new ThreadSub());
        sub.start();
        sub.join();
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
