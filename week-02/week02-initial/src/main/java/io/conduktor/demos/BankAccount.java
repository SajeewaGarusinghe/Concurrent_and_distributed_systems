package io.conduktor.demos;

// instance of the bank account is the shared object
// This BankAccount class is the monitor class with implicit lock
// Problem - you cannot implement a reader writer 
public class BankAccount {
	
	private String accountID;
	private double balance; // balance is money and for money you must not use double
	// BigDecimal is what you must use for money
	
	// balance is the shared variable 
	
	public BankAccount(String accountID, double balance) {
		super();
		this.accountID = accountID;
		this.balance = balance;
	}

	public String getAccountID() {
		return accountID;
	}

	// any code that access the shared variable i.e the balance is the 
	// critical section
	// getBalance() only reads the balance - read only operation
	// read operation
	public synchronized double getBalance() {
		return balance;
	}
	
	// since the deposit method updates the balance it is a write operation
	// part of critical section 
	public synchronized void deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
			System.out.println(Thread.currentThread().getName()+" "
					+ "deposited LKR 25000 and balance after deposit is "+this.getBalance());
		} else {
			throw new IllegalArgumentException("the amount cannot be negative!");
		}
	}
	
	// since the deposit method updates the balance it is also part of write operation
	// part of critical section
	public synchronized void withdraw(double amount) {
		if (amount > 0) {
			if(this.getBalance() - amount >= 0 ) {
				this.balance -= amount;
				System.out.println(Thread.currentThread().getName()+" "
						+ "withdraw LKR 25000 and balance after withdraw is "+this.getBalance());	
			} else {
				throw new IllegalArgumentException("Insuffient balance!");
			}
		} else {
			throw new IllegalArgumentException("the amount cannot be negative!");
		}
	}
	

}
