import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class MultiThreadedSort {
    public static void main(String[] args) {
        int[] array = {5, 3, 8, 6, 2, 7, 4, 1, 9, 0};
        int numberOfThreads = 4;
        int length = array.length;
        int partSize = (int) Math.ceil((double) length / numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Future<int[]>[] futures = new Future[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * partSize;
            int end = Math.min(start + partSize, length);
            if (start < length) {
                int[] part = Arrays.copyOfRange(array, start, end);
                futures[i] = executorService.submit(new SortTask(part));
            }
        }
        int[] sortedArray = new int[length];
        int currentIndex = 0;

        for (Future<int[]> future : futures) {
            if (future != null) {
                try {
                    int[] sortedPart = future.get();
                    System.arraycopy(sortedPart, 0, sortedArray, currentIndex, sortedPart.length);
                    currentIndex += sortedPart.length;
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        Arrays.sort(sortedArray);
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));
        executorService.shutdown();
    }
}