package epam.com;

public class MainThreadWaitNotifyAll {
    public static void main(String[] args) {
        SharedData shared = new SharedData();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shared.produce(i);
                try {
                    Thread.sleep(500); // Simulate delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shared.consume();
                try {
                    Thread.sleep(1000); // Simulate delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

class SharedData {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait(); // Wait until the data is consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notifyAll(); // Notify consumer that data is available
    }

    public synchronized void consume() {
        while (!hasData) {
            try {
                wait(); // Wait until the data is produced
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed: " + data);
        hasData = false;
        notifyAll(); // Notify producer that the slot is empty
    }
}