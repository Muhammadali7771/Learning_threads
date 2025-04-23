package epam.com;

public class MainThreadSynchronize {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(counter::increment);
        Thread thread2 = new Thread(counter::increment);

        thread1.start();
        thread2.start();

        Thread.sleep(300);
        System.out.println(thread2.getState());
    }
}

class Counter {
    int counter = 0;
    public synchronized void increment() {
        try {
            System.out.println(Thread.currentThread());
            Thread.sleep(1000);
            if (true){
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter++;
    }

    public int getCounter(){
        return counter;
    }
}
