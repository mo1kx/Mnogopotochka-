import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Ma2n {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 1; i <= 20; i++) {
            final int taskNumber = i;
            executorService.submit(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName() + ", Task Number: " + taskNumber);
            });
        }
        executorService.shutdown();
    }
}