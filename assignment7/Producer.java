package assignment7;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 7 - November 3rd, 2016

public class Producer implements Runnable {

	// Instance variables
	private final Queue queue;
	private String id; // id of producer
	private String produced; // current string produced
	@SuppressWarnings("unused")
	private int number_produced; // number of strings produced by this producer

	// Static variables
	private static int i; // String production counter

	// Constructor
	public Producer(Queue q, String id) {
		this.queue = q;
		this.id = id;
		this.number_produced = 0;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000); // Sleeps thread for half a second
				produced = "String " + (i + 1); // Creates a string to be
												// added to the queue
				queue.put(produced); // Adds a string to the queue
				number_produced++; // increment number produced by this
									// producer
				// Print Out Details
				System.out.println("Producer " + this.id + " has produced " + this.produced
						+ " and added it to the Queue. " + "Current Queue Size is: " + queue.getSize());
				i++; // Increments the static counter
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}