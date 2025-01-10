package com.thread.basics;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {

  public static void main(String[] args){
      Parking parking = new Parking();
      for(int i=1; i<6; i++){
          int finalI = i;
          Thread thread = new Thread(()-> parking.doPark("car "+ finalI));
          thread.start();

      }
  }
}

class Parking{

    private final Semaphore slot = new Semaphore(2);
//    private ReentrantLock lock = new ReentrantLock(); //one at a time

    void doPark(String car){
        try{
//            lock.lock();
            System.out.println(car + " entered parking");
            slot.acquire();
            System.out.println(car + " parked");
            Thread.sleep(2000);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            System.out.println(car + " leaving");
            slot.release();
//            lock.unlock();
        }
    }
}
