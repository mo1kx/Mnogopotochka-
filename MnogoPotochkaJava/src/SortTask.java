import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
class SortTask implements Callable<int[]> {
    private final int[] array;
    public SortTask(int[] array) {
        this.array = array;
    }
    @Override
    public int[] call() {
        Arrays.sort(array);
        return array;
    }
}