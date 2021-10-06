package com.harload.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @description: 各种奇奇怪怪的测试
 * @author: hcw
 * @create: 2021-06-28 13:39
 */
public class Test {

    static Thread numberThread = null;
    static Thread letterThread = null;
    static int count = 1;

    public static void main(String[] args) {

        List<Long> longList = new ArrayList<>();
        longList.add(null);

//        Object o = new Object();
//
//        numberThread = new Thread(() -> {
//            for (int i = 1; i <= 26; i++) {
//                try {
//                    synchronized (o) {
//                        while (count == 1){
//                            o.wait();
//                        }
//                        count++;
//                        System.out.println(i);
//                        o.notify();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        letterThread = new Thread(() -> {
//            for (char i = 'a'; i <= 'z'; i++) {
//                try {
//                    synchronized (o) {
//                        while (count == 0){
//                            o.wait();
//                        }
//                        count--;
//                        System.out.println(i);
//                        o.notify();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        numberThread.start();
//        letterThread.start();

        Lock lock = new ReentrantLock();
        Condition p = lock.newCondition();
        Condition c = lock.newCondition();

        numberThread = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                try {
                    lock.lock();
                    while (count == 1) {
                        p.await();
                    }
                    count++;
                    System.out.println(i);
                    c.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        letterThread = new Thread(() -> {
            for (char i = 'a'; i <= 'z'; i++) {
                try {
                    lock.lock();
                    while (count == 0) {
                        c.await();
                    }
                    count--;
                    System.out.println(i);
                    p.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        numberThread.start();
        letterThread.start();

        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                return null;
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        runnable.run();
    }
}
