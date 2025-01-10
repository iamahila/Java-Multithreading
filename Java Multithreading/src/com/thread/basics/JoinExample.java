package com.thread.basics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class JoinExample {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result = BigInteger.ZERO;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */

        List<PowerCalculatingThread> threadList = new ArrayList<>();
        threadList.add(new PowerCalculatingThread(base1, power1));
        threadList.add(new PowerCalculatingThread(base2, power2));

        for(PowerCalculatingThread t : threadList){
            t.start();
            t.join();
        }

        for(PowerCalculatingThread t: threadList){
            result = result.add(t.getResult());
        }

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for(BigInteger i = power; i.compareTo(BigInteger.ZERO) > 0 ; i = i.subtract(BigInteger.ONE) ){
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinExample joinExample = new JoinExample();
        BigInteger result = joinExample.calculateResult(BigInteger.valueOf(10), BigInteger.valueOf(2),
                BigInteger.valueOf(20), BigInteger.valueOf(2));
        System.out.println(result);
    }
}