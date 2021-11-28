package com.jjh.week04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 22:14
 **/
public class UseCyclicBarrier {

    private static String hello = "1111";
    //当2个线程调用await 才继续执行
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        new Thread(new ThreadSub()).start();
        cyclicBarrier.await();
        System.out.println(hello);

    }

    public static class ThreadSub implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                hello = "hello geek";
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
