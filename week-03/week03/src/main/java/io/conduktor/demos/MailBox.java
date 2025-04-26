package io.conduktor.demos;

public class MailBox {
	
	private int mail;
	private boolean available = false;
	
	// produce() method is called by Producer to produce value if available is false (nothing is available in the mail)
	public synchronized void produce(int value) {

		while (available) {
			try {
				wait();
				// when the wait method is called the thread will be put into WAITING state 
				// while thread in the WAITING state another thread may interrupt 
				// while in the WAITING state if the thread is interrupted 
				// InterruptedException will be thrown 
				// checked exception therefore mandatory to handle 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.mail = value; // producing the value in put it into the mail which is a shared variable
		this.available = true;
		notifyAll();	// for intercommunication between producer and consumer
	}
	
	// consume() method is called by the Consumer thread to consume a value if available is true (value is available in the mail to consume)
	public synchronized int consume() {
		while(!available) { // when available is false the consumer will be put into WAITING state
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// when available becomes true the consumer will come out of WAITING state
		// otherwise it will consume the record again
		
		int value = this.mail;
		this.available = false;
		notifyAll();// for intercommunication between producer and consumer
		
		return value;
	}

}
