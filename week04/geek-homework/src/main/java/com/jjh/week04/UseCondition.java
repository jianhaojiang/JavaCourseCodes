package com.jjh.week04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 21:44
 **/
public class UseCondition {
    private static String hello = "1111";

    static final Lock lock = new ReentrantLock();
    static final Condition subFinish = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        try {
            new Thread(new ThreadSub(lock, subFinish)).start();
            subFinish.await();
            System.out.println(hello);
        } finally {
            lock.unlock();
        }

    }

    public static class ThreadSub implements Runnable {

        Lock lock;
        Condition subFinish;

        public ThreadSub(Lock lock, Condition subFinish) {
            this.lock = lock;
            this.subFinish = subFinish;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    hello = "hello geek";
                    subFinish.signal();
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
