import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
class Task implements Runnable {
    private final CyclicBarrier barrier;
    private final int taskId;
    public Task(CyclicBarrier barrier, int taskId) {
        this.barrier = barrier;
        this.taskId = taskId;
    }
    @Override
    public void run() {
        try {
            System.out.println("Task " + taskId + " is working...");
            Thread.sleep((long) (Math.random() * 1000)); // Симуляция работы
            System.out.println("Task " + taskId + " completed.");
            barrier.await();
            System.out.println("Task " + taskId + " is proceeding to the next phase.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}