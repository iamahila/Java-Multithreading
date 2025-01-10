package com.thread.basics;

import java.util.Objects;

public class Deadlock {

    public static void main(String[] args){

        Route route = new Route();

        Bus1 bus1 = new Bus1(route);
        Bus2 bus2 = new Bus2(route);

        bus1.start();
        bus2.start();

    }


}

class Bus1 extends Thread{

    private Route route;

    Bus1(Route route){
        this.route = route;
    }

    @Override
    public void run(){
        route.routeA();
    }

}

class Bus2 extends Thread{

    private Route route;

    Bus2(Route route){
        this.route = route;
    }

    @Override
    public void run(){
        route.routeB();
    }

}

class Route{

    Object route1 = new Object();
    Object route2 = new Object();

    public void routeA(){

        synchronized (route1){
            System.out.println("In Route A");
            synchronized (route2){
                System.out.println("Train Passed");
            }
        }

    }

    public void routeB(){
        synchronized (route1){
            System.out.println("In Route B");
            synchronized (route2){
                System.out.println("Train Passed");
            }
        }
    }

}