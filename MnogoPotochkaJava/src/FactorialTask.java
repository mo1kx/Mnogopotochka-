import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
class FactorialTask implements Callable<Long> {
    private final int number;
    public FactorialTask(int number) {
        this.number = number;
    }
    @Override
    public Long call() {
        return factorial(number);
    }
    private Long factorial(int n) {
        if (n == 0) {
            return 1L;
        }
        return n * factorial(n - 1);
    }
}

