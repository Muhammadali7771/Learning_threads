package epam.com;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " " + i);
                System.out.println(Thread.currentThread().getState());
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " " + i);
                System.out.println(Thread.currentThread().getState());
            }
        });

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }
}