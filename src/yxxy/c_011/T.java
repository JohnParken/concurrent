/**
 * 程序在执行的过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会出现不一
 * 致的情况，因此要小心处理同步业务中的异常
 */

package yxxy.c_011;

import java.util.concurrent.TimeUnit;

public class T {
    int count;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() +
                " start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()
                    + " count = " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            if ( count == 5){
                int i = 1 / 0; // 抛异常、释放锁
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(()->t.m(), "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->t.m(), "t2").start();
    }
}
