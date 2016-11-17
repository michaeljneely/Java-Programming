package assignment7;

import java.util.ArrayList;
import java.util.Random;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 7 - November 3rd, 2016

// Creates a random number of Producer and Consumer threads at startup to test implementation.
public class Test {

	public static void main(String[] Args) {
		Queue queue = new Queue(50); // Queue Can Hold 50 Strings
		// Random Number of Producers and Consumers Between 1 and 10
		Random r = new Random();
		int numP = 1 + r.nextInt(10);
		int numC = 1 + r.nextInt(10);
		
		//Add Consumers and Producers to Array Lists
		ArrayList<Thread> producers = new ArrayList<Thread>();
		ArrayList<Thread> consumers = new ArrayList<Thread>();
		System.out.println(numP + " Producers Created.\n" + numC + " Consumers Created.");
		for (int i = 0; i < numP; i++) {
				producers.add(new Thread(new Producer(queue, "Producer " + (i + 1))));
		}
		for (int i = 0; i < numC; i++) {
				consumers.add(new Thread(new Consumer(queue, "Consumer " + (i + 1))));
		}
		
		//Start All Threads
		for(Thread t: producers) t.start();
		for(Thread t: consumers) t.start();
	}

}
