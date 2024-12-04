import java.util.concurrent.atomic.AtomicBoolean;
public class TimerWithThreads {
    public static void main(String[] args) {
        AtomicBoolean running = new AtomicBoolean(true);
        Thread timerThread = new Thread(() -> {
            while (running.get()) {
                System.out.println("Текущее время: " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread stopperThread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            running.set(false);
            System.out.println("Таймер остановлен.");
        });
        timerThread.start();
        stopperThread.start();
        try {
            timerThread.join();
            stopperThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}