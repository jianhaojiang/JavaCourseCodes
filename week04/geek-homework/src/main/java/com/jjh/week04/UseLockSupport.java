package com.jjh.week04;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 21:20
 **/
public class UseLockSupport {

    private static String hello = "1111";

    public static void main(String[] args) {
        new Thread(new ThreadSub(Thread.currentThread())).start();
        //主线程进入等待
        LockSupport.park();
        System.out.println(hello);
    }

    public static class ThreadSub implements Runnable {

        Thread main = null;

        public ThreadSub(Thread main) {
            this.main = main;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                hello = "hello geek";
                //子线程执行完，调用主线程unpark，主线程进入就绪状态
                LockSupport.unpark(main);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
