import java.util.concurrent.Semaphore;

class Resource {
    private final Semaphore semaphore;

    public Resource(int maxConcurrentAccess) {
        this.semaphore = new Semaphore(maxConcurrentAccess);
    }

    public void accessResource(int threadId) {
        try {
            // Запрашиваем разрешение на доступ к ресурсу
            semaphore.acquire();
            System.out.println("Thread " + threadId + " has acquired access to the resource.");

            // Симуляция работы с ресурсом
            Thread.sleep((long) (Math.random() * 2000)); // Работа с ресурсом

            System.out.println("Thread " + threadId + " is releasing access to the resource.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Освобождаем разрешение
            semaphore.release();
        }
    }
}

class WorkerThread implements Runnable {
    private final Resource resource;
    private final int threadId;

    public WorkerThread(Resource resource, int threadId) {
        this.resource = resource;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        resource.accessResource(threadId);
    }
}

