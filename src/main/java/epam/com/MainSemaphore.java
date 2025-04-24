package epam.com;

import java.util.concurrent.Semaphore;

class ParkingLot {
    private Semaphore semaphore;

    public ParkingLot(int capacity) {
        semaphore = new Semaphore(capacity);
    }

    public void enter(String carName) {
        try {
            semaphore.acquire();
            System.out.println(carName + " entered the parking lot. Available spaces: " + semaphore.availablePermits());
            Thread.sleep(2000); // Simulate time spent in parking
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(carName + " exited the parking lot. Available spaces: " + semaphore.availablePermits());
        }
    }
}

class CarThread extends Thread {
    private ParkingLot parkingLot;
    private String carName;

    public CarThread(ParkingLot parkingLot, String carName) {
        this.parkingLot = parkingLot;
        this.carName = carName;
    }

    @Override
    public void run() {
        parkingLot.enter(carName);
    }
}

public class MainSemaphore {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3); // 3 parking spaces available

        // Create multiple car threads trying to enter the parking lot
        for (int i = 1; i <= 5; i++) {
            CarThread carThread = new CarThread(parkingLot, "Car " + i);
            carThread.start();
        }
    }
}
