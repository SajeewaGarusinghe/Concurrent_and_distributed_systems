package io.conduktor.demos;

public class MyThread extends Thread {
	
	public MyThread(String name) {
		super(name);
	}

//	Thread()
//	Allocates a new Thread object.
//	Thread(Runnable target)
//	Allocates a new Thread object.
//	Thread(Runnable target, String name)
//	Allocates a new Thread object.
//	Thread(String name)------------- > used here
//	Allocates a new Thread object.
//	Thread(ThreadGroup group, Runnable target)
//	Allocates a new Thread object.
//	Thread(ThreadGroup group, Runnable target, String name)
//	Allocates a new Thread object so that it has target as its run object, has the specified name as its name, and belongs to the thread group referred to by group.
//	Thread(ThreadGroup group, Runnable target, String name, long stackSize)
//	Allocates a new Thread object so that it has target as its run object, has the specified name as its name, and belongs to the thread group referred to by group, and has the specified stack size.
//			Thread(ThreadGroup group, String name)
//	Allocates a new Thread object.
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+" : "+i);
		}
	}

}
