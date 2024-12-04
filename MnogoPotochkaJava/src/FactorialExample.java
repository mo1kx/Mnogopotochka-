import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class FactorialExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Long>[] futures = new Future[10];
        for (int i = 0; i < 10; i++) {
            FactorialTask task = new FactorialTask(i + 1);
            futures[i] = executorService.submit(task);
        }
        for (int i = 0; i < 10; i++) {
            try {
                Long result = futures[i].get();
                System.out.println("Factorial of " + (i + 1) + " is " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}