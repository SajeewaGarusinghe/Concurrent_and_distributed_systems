package io.conduktor.demos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MailBox {
	
	BlockingQueue<Integer> mail = new LinkedBlockingQueue<Integer>(1);
	BlockingQueue<Integer> mail2 = new LinkedBlockingQueue<Integer>();
	// giving 1 to the constructor of the LinkedBlockingQueue is important
	// that is to say it is fixed capacity
	// if we do not give one then takes the capacity as dynamically growing 
	

	
	
	 
	
	// produce() method is called by Producer to produce value if available is false (nothing is available in the mail)
	public void produce(int value) {

		try {
			mail.put(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	// consume() method is called by the Consumer thread to consume a value if available is true (value is available in the mail to consume)
	public int consume() {

		try {
			int value = mail.take();
			return value;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
		
	}

}
