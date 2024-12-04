import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Philosopher implements Runnable {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final int eatTime;
    private final int thinkTime;
    public Philosopher(int id, Lock leftFork, Lock rightFork, int eatTime, int thinkTime) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
    }
    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep(thinkTime);
    }
    private void eat() throws InterruptedException {
        if (id % 2 == 0) {
            leftFork.lock();
            rightFork.lock();
        } else {
            rightFork.lock();
            leftFork.lock();
        }

        try {
            System.out.println("Philosopher " + id + " is eating.");
            Thread.sleep(eatTime);
        } finally {
            leftFork.unlock();
            rightFork.unlock();
        }
    }
}