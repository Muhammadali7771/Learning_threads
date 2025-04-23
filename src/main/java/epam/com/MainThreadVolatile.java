package epam.com;

public class MainThreadVolatile {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();
        MyThread myThread = new MyThread(sharedResource);
        myThread.start();

        Thread.sleep(3000);
        sharedResource.flag = false;
        System.out.println("----------------------------------------");
    }
}

class SharedResource {
    public volatile boolean flag = true;
}

class MyThread extends Thread {
    public SharedResource sharedResource;
    public MyThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        while (sharedResource.flag) {

        }
        System.out.println("End");
    }
}
