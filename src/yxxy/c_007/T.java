package yxxy.c_007;

/**
 * 同步方法和非同步方法是否可以同时调用
 */

public class T{

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() +
                " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +
                " m1 end");
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() +
                " m2 start...");
        try {
            Thread.sleep(500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +
                " m2 end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(()->t.m1(), "t1").start();
        new Thread(()->t.m2(), "t2").start();

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        });
        */
    }
}
