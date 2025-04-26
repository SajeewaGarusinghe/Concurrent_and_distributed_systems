package io.conduktor.demos;

public class MultiThreadedApartment {

	public static void main(String[] args) {

		System.out.println("The name of the default thread is " 
		+ Thread.currentThread().getName());
		
		Thread t1 = new MyThread("Thread 01");// This thread enters NEW state
		//t1.run(); // you must not call the run() if you do so it is still a single threaded & sequential programming
		t1.start();// RUNNABLE state
		// if your machine has single core 
		
		// How to create thread instance when implementing the runnable interface
		
		// 1. create an instance of implementation of the runnable interface
		// in our case it is MyRunnable
		// I'm going to create an instance of MyRunnable
		Runnable r1 = new MyRunnable(); // This is the target of the thread or task <------this is the target only
										// that is wish to execute in a separate thread
		
		Thread t2 = new Thread(r1, "Thread 02");// t2 comes into the NEW state
		t2.start(); // t2 comes into RUNNABLE 
		
		// creating thread using lambda function 
		
		Thread t3 = new Thread(()->{
			// this is the body of the thread
			// this is the run() method in this context
			// implementation of Runnable interface 
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
		}, "Thread 03"); // Thread object is created and it is in the NEW state

		t3.start(); // Thread 03 comes into the RUNNABLE state
		// when you go for lambda function or anonymous inner class that is for single use
		// the logic of the thread (body of the thread - body of the run method)
		// cannot be reused 
		// one time use or single use

		// creating thread using anonymous inner class
		Thread a1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// this is the body of the thread
				// implementation of Runnable interface
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName() + " : " + i);
				}
			}
		}, "Thread anonymous");

		a1.start();
		Thread t4 = new Thread(new MyRunnable(), "Thread 04");
		
		Thread t5 = new Thread(r1, "Thread 05");
		
		t4.start();
		t5.start();
		
		
		
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
		}
		
	
		
		// in a multi threaded application order of thread creation doesnt have a say
		// in the order of execution
		// never make an assumption about order in which thread will get executed 
		// if you have any assumption about order of thread execution -> will leads program error
		// Thread execution order is indeterministic 

	}

}
