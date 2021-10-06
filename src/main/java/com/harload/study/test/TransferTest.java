package com.harload.study.test;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferTest {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        TransferQueue<Character> transferQueue = new LinkedTransferQueue<Character>();

        Thread t1 = new Thread(()->{
            for(char a : aI){
                try {
                    System.out.println(a);
                    transferQueue.transfer(a);
                    transferQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for(char a : aC){
                try {
                    transferQueue.take();
                    System.out.println(a);
                    transferQueue.transfer(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
