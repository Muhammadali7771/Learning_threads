package epam.com;

public class MainThreadExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t + " ----- " + e);
            }
        });

        if (true){
            throw new RuntimeException("test");
        }
    }
}
