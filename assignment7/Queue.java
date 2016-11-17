package assignment7;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 7 - November 3rd, 2016

public class Queue {

	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition(); //Put Blocking Condition
	final Condition notEmpty = lock.newCondition(); //Take Blocking Condition

	private LinkedList<String> q; //Use built-in Java FIFO Queue Implementation
	private int maxSize; // bound on queue size

	// Constructor
	public Queue(int bound) {
		this.maxSize = bound;
		this.q = new LinkedList<String>();
	}

	//Getter for Queue Size
	public int getSize() {
		return q.size();
	}

	// Place String in Queue
	public void put(String s) throws InterruptedException {
		lock.lock();
		try {
			// If Queue is Full
			while (maxSize == q.size()) {
				System.out.println("Queue Full. Waiting....");
				//Block Until Space Becomes Available
				notFull.await();
			}
			q.add((String) s);
			//Notify Consumer
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	//Take String From Queue
	public String take() throws InterruptedException {
		lock.lock();
		try {
			//If Queue is Empty
			while (q.size() == 0) {
				System.out.println("Queue Empty. Waiting...");
				//Block Until Item to Take Becomes Available
				notEmpty.await();
			}
			String s = q.remove(0);
			//Notify Producer
			notFull.signal();
			return s;
		} finally {
			lock.unlock();
		}
	}

}
