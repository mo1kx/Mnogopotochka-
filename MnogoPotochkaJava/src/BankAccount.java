import java.util.concurrent.locks.ReentrantLock;
class BankAccount {
    private final String accountNumber;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    public void transfer(BankAccount targetAccount, double amount) {
        boolean firstLock = false;
        boolean secondLock = false;

        try {
            firstLock = lock.tryLock();
            if (firstLock) {
                secondLock = targetAccount.lock.tryLock();
                if (secondLock) {
                    if (amount <= balance) {
                        balance -= amount;
                        targetAccount.balance += amount;
                        System.out.println("Transferred " + amount + " from " + accountNumber + " to " + targetAccount.accountNumber);
                    } else {
                        System.out.println("Insufficient funds for transfer from " + accountNumber);
                    }
                }
            }
        } finally {
            if (firstLock) {
                lock.unlock();
            }
            if (secondLock) {
                targetAccount.lock.unlock();
            }
        }
    }
    public double getBalance() {
        return balance;
    }
}

