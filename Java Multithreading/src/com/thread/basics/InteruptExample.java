package com.thread.basics;

public class InteruptExample {

    public static void main(String[] args){

        Thread thread1 = new MyThread1();
        thread1.start();

        Thread thread2 = new MyThread2();
        thread2.start();

        thread1.interrupt();
        thread2.interrupt();

    }
}

class MyThread1 extends Thread{
    //exception catched for interrupt
    @Override
    public void run(){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ix){
            System.out.println("Exception caused ");
        }
    }
}

class MyThread2 extends Thread{
    int a = 0;
    //find hotspot for interupt and check manually
    @Override
    public void run(){
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interupted");
                return;
            }
            a = a + 12;
            System.out.println("Value is: " + a);
        }
    }
}
