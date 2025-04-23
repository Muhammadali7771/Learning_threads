package epam.com;

public class ThreadGroup {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread());
        });

        thread1.start();
        thread2.start();
        System.out.println(Thread.currentThread());
    }
}
