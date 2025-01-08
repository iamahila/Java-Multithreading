package com.thread.basics;

public class ThreadClass {

    public static void main(String[] args){
        Thread thread = new MyThread();
        thread.start();
    }

}

class MyThread extends Thread{

    @Override
    public void run(){
        System.out.println("Running from run method");
    }

}
