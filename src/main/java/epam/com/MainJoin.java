package epam.com;

import java.lang.ThreadGroup;

public class MainJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " " + i);
            }
        });

        thread1.start();
        thread2.start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        threadGroup.list();
    }
}
