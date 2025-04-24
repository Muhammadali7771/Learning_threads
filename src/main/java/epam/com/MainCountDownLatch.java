package epam.com;

import java.util.concurrent.CountDownLatch;

class Race {
    private CountDownLatch startSignal;

    public Race(int numberOfRunners) {
        startSignal = new CountDownLatch(1); // Initialize the latch with count 1
    }

    public void startRace() {
        System.out.println("Race official: 'Ready, set, go!'");
        startSignal.countDown(); // Signal that the race has started
    }

    public void waitForStart(String runnerName) throws InterruptedException {
        startSignal.await(); // Wait until the race official says 'go'
        System.out.println(runnerName + " started running.");
    }
}

class RunnerThread extends Thread {
    private Race race;
    private String runnerName;

    public RunnerThread(Race race, String runnerName) {
        this.race = race;
        this.runnerName = runnerName;
    }

    @Override
    public void run() {
        try {
            race.waitForStart(runnerName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MainCountDownLatch {
    public static void main(String[] args) {
        Race race = new Race(5); // 5 runners in the race

        // Create multiple runner threads waiting at the starting line
        for (int i = 1; i <= 5; i++) {
            RunnerThread runnerThread = new RunnerThread(race, "Runner " + i);
            runnerThread.start();
        }

        // Start the race after a countdown
        race.startRace();
    }
}
