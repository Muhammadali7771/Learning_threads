package epam.com;

public class MainThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            /*for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + " : " + i);
            }*/
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during the sleep");
            }
        });

        thread1.start();
        Thread.currentThread().sleep(2000);
        thread1.interrupt();
    }
}
