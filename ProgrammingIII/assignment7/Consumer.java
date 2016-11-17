package assignment7;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 7 - November 3rd, 2016

public class Consumer implements Runnable {

	// Instance variables
	private final Queue queue;
	private String id;
	private String consumed;

	// Constructor
	public Consumer(Queue q, String id) {
		this.queue = q;
		this.id = id;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000); // Sleeps thread for half a second
				consumed = queue.take(); // consume first string from queue
				// Print Out Details
				System.out.println("Consumer " + this.id + " has removed " + this.consumed + " From the Queue. "
						+ "Current Queue Size is: " + queue.getSize());
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
