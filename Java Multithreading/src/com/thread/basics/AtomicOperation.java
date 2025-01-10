package com.thread.basics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AtomicOperation {

    public static void main(String[] args) throws InterruptedException {
        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
        Thread1 thread1 = new Thread1(minMaxMetrics);
        Thread2 thread2 = new Thread2(minMaxMetrics);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Min value is "+ minMaxMetrics.getMin());
        System.out.println("Max value is "+ minMaxMetrics.getMax());

    }

}

class Thread2 extends  Thread{

    MinMaxMetrics minMaxMetrics;

    Thread2(MinMaxMetrics minMaxMetrics){
        this.minMaxMetrics = minMaxMetrics;
    }

    @Override
    public void run(){
        minMaxMetrics.addSample(200L);
        minMaxMetrics.addSample(1L);
        minMaxMetrics.addSample(3L);
    }

}

class Thread1 extends  Thread{

    MinMaxMetrics minMaxMetrics;

    Thread1(MinMaxMetrics minMaxMetrics){
        this.minMaxMetrics = minMaxMetrics;
    }

    @Override
    public void run(){
        minMaxMetrics.addSample(100L);
        minMaxMetrics.addSample(10L);
        minMaxMetrics.addSample(5L);
    }

}

class MinMaxMetrics {

   private volatile long minValue;
    private volatile long maxValue;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        this.minValue = Long.MAX_VALUE;
        this.maxValue = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        synchronized (this){
            this.minValue = Math.min(this.minValue, newSample);
            this.maxValue = Math.max(this.maxValue, newSample);
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return this.minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return this.maxValue;
    }
}
