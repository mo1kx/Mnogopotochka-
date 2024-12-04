import java.util.concurrent.ConcurrentLinkedQueue;
class NumberAdderThread extends Thread {
    private ConcurrentLinkedQueue<Integer> queue;
    public NumberAdderThread(ConcurrentLinkedQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            queue.add(i);
        }
    }
}
