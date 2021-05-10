package com.example.genesis.queue;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class InternalOrderQueue  {
    private static InternalOrderQueue uniqueInstance; //You don't need `volatile` here
    private static ConcurrentLinkedQueue<Object> concurrentLinkedQueue;

    // Here it all begins:
    private static class SingletonHolder {
        private static InternalOrderQueue  instance = new InternalOrderQueue();
    }
    public static InternalOrderQueue getInstance() {
        return SingletonHolder.instance;
    }
    private InternalOrderQueue () {
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    // class functions:
    public void add(Object object) {
        concurrentLinkedQueue.add(object);
    }

    public void delete(Object object) {
        concurrentLinkedQueue.remove(object);
    }

    public void clear() {
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    public void show(){
        System.out.println(concurrentLinkedQueue.toString());
    }

    public Integer getSize(){
        return concurrentLinkedQueue.size();
    }

}