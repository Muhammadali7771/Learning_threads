package epam.com;

public class MainDaemon {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " " + i);
            }
        });

        thread1.setDaemon(true);
        thread1.start();
        System.out.println("Hello World");
    }
}
