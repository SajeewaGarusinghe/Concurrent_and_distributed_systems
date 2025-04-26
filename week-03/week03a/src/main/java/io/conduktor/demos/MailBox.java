package io.conduktor.demos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MailBox {
	
	private int mail;
	private boolean availability = false;
	
	ReentrantLock lock = new ReentrantLock(true);
	
	Condition available = lock.newCondition(); // producer if availability is true the producer is put on waitset on available condition
	Condition notAvailable = lock.newCondition();// consumer if availability is false the consumer is put on waitset on notAvailable condition  
	
	// produce() method is called by Producer to produce value if available is false (nothing is available in the mail)
	public void produce(int value) {
		try {
			lock.lock();
			while (availability) {
				try {
					available.await(); // if the value is available then producer will go into WAITING state on available condition
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.mail = value; // producing the value in put it into the mail which is a shared variable
			this.availability = true;
			notAvailable.signalAll();
		} finally {
			lock.unlock();
		}
		
			
	}
	
	// consume() method is called by the Consumer thread to consume a value if available is true (value is available in the mail to consume)
	public int consume() {
		try {
			lock.lock();
			while(!availability) {
				try {
					notAvailable.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			// when available becomes true the consumer will come out of WAITING state
			
			int value = this.mail;
			this.availability = false;
			available.signalAll();
			
			return value;
		} finally {
			lock.unlock();
		}
		
	}

}
