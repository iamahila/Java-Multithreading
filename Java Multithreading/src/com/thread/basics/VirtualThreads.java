package com.thread.basics;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreads {
    public static void main(String[] args){

        int NUMBER_OF_THREADS = 10000;
        Runnable r = ()->{
            try{
                System.out.println("Before starting: "+ Thread.currentThread());
                Thread.sleep(1000);
                System.out.println("After starting: "+ Thread.currentThread());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        };
        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<NUMBER_OF_THREADS; i++){
            Thread thread = Thread.ofVirtual().unstarted(r);
            threads.add(thread);
        }

        for(Thread thread : threads){
            thread.start();
        }
    }
}
