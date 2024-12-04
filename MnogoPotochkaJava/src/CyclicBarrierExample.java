import java.util.concurrent.CyclicBarrier;
public class CyclicBarrierExample {
    public static void main(String[] args) {
        final int numberOfTasks = 5;
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks completed. Proceeding to the next phase.");
        });
        Thread[] threads = new Thread[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            threads[i] = new Thread(new Task(barrier, i + 1));
            threads[i].start();
        }
        for (int i = 0; i < numberOfTasks; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}