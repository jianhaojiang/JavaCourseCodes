package com.jjh.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 20:23
 **/
public class UseFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new ThreadCall());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

    }

    public static class ThreadCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            return "hello geek";
        }
    }
}
