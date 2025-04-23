package epam.com;

public class MainDeadlock {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        Thread thread1 = new CustomThread1(object1, object2);
        Thread thread2 = new CustomThread2(object1, object2);
        thread1.start();
        thread2.start();
    }
}

class CustomThread1 extends Thread {
    private Object object1;
    private Object object2;
    public CustomThread1(Object object1, Object object2) {
        this.object1 = object1;
        this.object2 = object2;
    }
    @Override
    public void run() {
        synchronized (object1) {
            System.out.println(Thread.currentThread() + " acquired monitor of object1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (object2) {
                System.out.println(Thread.currentThread() + " acquired monitor of object2");
            }
        }
    }
}

class CustomThread2 extends Thread {
    private Object object1;
    private Object object2;
    public CustomThread2(Object object1, Object object2) {
        this.object1 = object1;
        this.object2 = object2;
    }
    @Override
    public void run() {
        synchronized (object2) {
            System.out.println(Thread.currentThread() + " acquired monitor of object2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (object1) {
                System.out.println(Thread.currentThread() + " acquired monitor of object1");
            }
        }
    }
}
