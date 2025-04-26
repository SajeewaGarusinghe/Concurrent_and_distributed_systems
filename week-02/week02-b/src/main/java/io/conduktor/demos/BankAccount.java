package io.conduktor.demos;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	
	private String accountID;
	private double balance; 
	
	ReentrantLock lock = new ReentrantLock(true);
	
	public BankAccount(String accountID, double balance) {
		super();
		this.accountID = accountID;
		this.balance = balance;
	}

	public String getAccountID() {
		return accountID;
	}

	public double getBalance() {
		try {
			lock.lock();
			return balance;
		} finally {
			lock.unlock();
		}
		
	}

	public void deposit(double amount) {
		try {
			lock.lock();
			if (amount > 0) {
				this.balance += amount;
				System.out.println(Thread.currentThread().getName()+" "
						+ "deposited LKR 25000 and balance after deposit is "+this.getBalance());
			} else {
				throw new IllegalArgumentException("the amount cannot be negative!");
			}
		}finally {
			// irrespective of a Exception is raised or not the finally block is guaranteed to execute
			// that is the reason to release the lock inside the finally block
			lock.unlock();
			
		}	
	}
	
	public void withdraw(double amount) {
		try {
			lock.lock();
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
		} finally {
			lock.unlock();
		}
	}
		
	



}
