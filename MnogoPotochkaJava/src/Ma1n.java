import java.util.concurrent.ConcurrentLinkedQueue;
public class Ma1n {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> numberQueue = new ConcurrentLinkedQueue<>(); // Создаем потокобезопасную очередь
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new NumberAdderThread(numberQueue);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Numbers in the queue: " + numberQueue);
    }
}

