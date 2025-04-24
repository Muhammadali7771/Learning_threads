package epam.com;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Menu {
    private String[] items = {"Pizza", "Burger", "Pasta", "Salad"};
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public String[] viewMenu() {
        lock.readLock().lock();
        try {
            System.out.println("Menu viewed by a customer.");
            return items;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void updateMenu(String[] newItems) {
        lock.writeLock().lock();
        try {
            System.out.println("Menu updated by the chef.");
            items = newItems;
        } finally {
            lock.writeLock().unlock();
        }
    }
}

class ChefThread extends Thread {
    private Menu menu;

    public ChefThread(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        String[] newMenu = {"Pizza", "Burger", "Pasta", "Salad", "Steak"};
        menu.updateMenu(newMenu);
    }
}

class CustomerThread2 extends Thread {
    private Menu menu;

    public CustomerThread2(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        String[] items = menu.viewMenu();
        System.out.println("Customer viewed the menu: " + String.join(", ", items));
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {
        Menu menu = new Menu();

        // Create a chef thread to update the menu
        ChefThread chefThread = new ChefThread(menu);
        chefThread.start();

        // Create multiple customer threads to view the menu
        for (int i = 1; i <= 3; i++) {
            CustomerThread2 customerThread = new CustomerThread2(menu);
            customerThread.start();
        }
    }
}
