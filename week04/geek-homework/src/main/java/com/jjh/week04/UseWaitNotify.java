package com.jjh.week04;


/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 20:35
 **/
public class UseWaitNotify {

    static Object lock = new Object();

    private static String hello = "1111";

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadSub()).start();
        synchronized (lock){
            lock.wait();
        }
        System.out.println(hello);
    }



    public static class ThreadSub implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                hello = "hello geek";
                synchronized (lock){
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
