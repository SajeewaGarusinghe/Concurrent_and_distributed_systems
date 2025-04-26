package io.conduktor.demos;

public class SingleThreadedApartment {
	
	private static String name = null;
	
	// Sequential program because there is only one thread - single threaded application (apartment)

	public static void main(String[] args) {
		
		System.out.println("The name of the default thread is "
		+Thread.currentThread().getName());
		
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+" : "+i);
		}
		
		System.out.println(name.toString());

	}

}
