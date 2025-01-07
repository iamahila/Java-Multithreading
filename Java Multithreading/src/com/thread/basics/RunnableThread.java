package com.thread.basics;

public class RunnableThread {
    public static void main(String[] args) {

        //Thread creation using Runnable
        Thread thread = new Thread(()->{
            System.out.println("Hello from inside run method using thread "
                    + Thread.currentThread().getName());
            throw new RuntimeException("Intentional Exception");
        }
                );
        thread.setName("My First Thread");
        thread.setPriority(Thread.MAX_PRIORITY); //static priority to calc (dynamic priority = static + bonus (os))

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Showing unhandled exception here!");
            }
        });

        System.out.println("Thread name before starting thread "+ Thread.currentThread().getName());
        thread.start();
        System.out.println("Thread name after starting thread "+ Thread.currentThread().getName());

    }
}