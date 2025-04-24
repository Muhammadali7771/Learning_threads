package epam.com;

class CoffeeMachine {
    public void makeCoffee(String customerName) {
        synchronized (this) {
            System.out.println(customerName + " is using the coffee machine.");
            try {
                Thread.sleep(2000); // Simulate making coffee
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(customerName + " finished making coffee.");
        }
    }
}

class CustomerThread extends Thread {
    private CoffeeMachine coffeeMachine;
    private String customerName;

    public CustomerThread(CoffeeMachine coffeeMachine, String customerName) {
        this.coffeeMachine = coffeeMachine;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        coffeeMachine.makeCoffee(customerName);
    }
}

public class MainObjectLevelLock {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        // Create multiple customer threads trying to use the coffee machine
        for (int i = 1; i <= 3; i++) {
            CustomerThread customerThread = new CustomerThread(coffeeMachine, "Customer " + i);
            customerThread.start();
        }
    }
}
