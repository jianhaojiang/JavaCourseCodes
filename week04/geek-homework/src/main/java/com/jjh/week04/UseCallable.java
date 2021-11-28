package com.jjh.week04;

import java.util.concurrent.*;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-11-28 20:12
 **/
public class UseCallable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new ThreadCall());
        System.out.println(future.get());
        executorService.shutdown();
    }


    public static class ThreadCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            return "hello geek";
        }
    }

}
