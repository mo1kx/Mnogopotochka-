public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(); // Создаем общий счётчик
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new CounterThread(counter);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Final count: " + counter.getCount());
    }
}