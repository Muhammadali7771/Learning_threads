package epam.com;

class Printer {
    public static synchronized void printDocument(String documentName, String employeeName) {
        System.out.println(employeeName + " is printing document '" + documentName + "'.");
        try {
            Thread.sleep(2000); // Simulate printing document
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(employeeName + " finished printing document '" + documentName + "'.");
    }
}

class EmployeeThread extends Thread {
    private String documentName;
    private String employeeName;

    public EmployeeThread(String documentName, String employeeName) {
        this.documentName = documentName;
        this.employeeName = employeeName;
    }

    @Override
    public void run() {
        Printer.printDocument(documentName, employeeName);
    }
}

public class MainClassLevelLocks {
    public static void main(String[] args) {
        // Create multiple employee threads trying to print documents
        for (int i = 1; i <= 3; i++) {
            EmployeeThread employeeThread = new EmployeeThread("Document " + i, "Employee " + i);
            employeeThread.start();
        }
    }
}