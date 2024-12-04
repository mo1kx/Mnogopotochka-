public class BankSimulation {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount("Account1", 1000);
        BankAccount account2 = new BankAccount("Account2", 500);
        BankAccount account3 = new BankAccount("Account3", 300);
        Thread t1 = new Thread(new TransferTask(account1, account2, 200));
        Thread t2 = new Thread(new TransferTask(account2, account3, 100));
        Thread t3 = new Thread(new TransferTask(account1, account3, 300));
        Thread t4 = new Thread(new TransferTask(account3, account1, 150));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Final Balances:");
        System.out.println("Account1: " + account1.getBalance());
        System.out.println("Account2: " + account2.getBalance());
        System.out.println("Account3: " + account3.getBalance());
    }
}