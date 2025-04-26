package io.conduktor.demos;
public class BankAccountDemo {

	public static void main(String[] args) {
		BankAccount obj = new BankAccount("Acc001", 25000);
		
		Runnable cmw = new CareerMindedWife(obj);
		Runnable hbh = new HouseBasedHusband(obj);
		
		Thread cmwThread = new Thread(cmw, "Career Minded Wife"); // When you instantiate a Thread object it enters the NEW state
		Thread hbhThread = new Thread(hbh, "House Based Husband");
		
		
		cmwThread.start(); // when the start() method is called the Thread object goes from NEW state to RUNNABLE state
		hbhThread.start();
		
		

	}

}
