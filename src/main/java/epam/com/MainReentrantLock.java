package epam.com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Restaurant {
    private int availableTables = 5; // Total number of available tables
    private Lock lock = new ReentrantLock();

    public void bookTable(String customerName) {
        lock.lock();
        try {
            if (availableTables > 0) {
                availableTables--;
                System.out.println(customerName + " booked a table. Available tables: " + availableTables);
            } else {
                System.out.println("Sorry, no tables available for " + customerName);
            }
        } finally {
            lock.unlock();
        }
    }
}

class CustomerThread1 extends Thread {
    private Restaurant restaurant;
    private String customerName;

    public CustomerThread1(Restaurant restaurant, String customerName) {
        this.restaurant = restaurant;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        restaurant.bookTable(customerName);
    }
}

public class MainReentrantLock {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // Create multiple customer threads
        CustomerThread1 customer1 = new CustomerThread1(restaurant, "Alice");
        CustomerThread1 customer2 = new CustomerThread1(restaurant, "Bob");
        CustomerThread1 customer3 = new CustomerThread1(restaurant, "John");

        // Start the customer threads
        customer1.start();
        customer2.start();
        customer3.start();
    }
}