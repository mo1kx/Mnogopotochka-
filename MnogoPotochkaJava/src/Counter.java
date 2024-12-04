class Counter {
    private int count = 0; // Счётчик
    public synchronized void increment() {
        count++;
    }

    // Метод для получения текущего значения счётчика
    public int getCount() {
        return count;
    }
}
class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment(); // Увеличиваем счётчик
        }
    }
}