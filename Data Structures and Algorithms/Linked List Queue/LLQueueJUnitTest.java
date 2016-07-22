package llistqueue;

import static org.junit.Assert.*;
import org.junit.Test;

public class LLQueueJUnitTest {

	//Instance of a linked list queue
	LLQueue ll = new LLQueue();

	//Array of strings for testing purposes
	String[] words = {"Me First", "Me Second", "Me Third", "Me Fourth", "Me Fifth"};

	//Prove Enqueue/Dequeue Work and Output Results to Console
	@Test
	public void enqueueDequeueTest(){
		//Array to Hold Dequeued Words
		String[] dequeuedWords = new String[5];

		//Enqueue 5, Print to Console
		for(String word: words ){
			ll.enqueue(word);
			System.out.println("Enqueueing: " + word );
		}

		System.out.println("-------" );

		//Dequeue 5, Print to Console
		int size = (int) ll.getSize();
		for(int i = 0; i < size; i++){
			dequeuedWords[i] = ll.dequeue().toString();
			System.out.println("Dequeueing: " + dequeuedWords[i] );
		}
		//Assert dequeuedWords array equal to original words array
		assertArrayEquals(words, dequeuedWords);

	}

	//Prove isEmpty() Works
	@Test
	public void isEmptyTest(){
		//Enqueue 5
		for(String word: words ){
			ll.enqueue(word);
		}
		//Dequeue 5
		int size = (int) ll.getSize();
		for(int i = 0; i < size; i++){
			ll.dequeue();
		}
		//Prove Empty
		assertTrue(ll.isEmpty());
	}

	//Prove front() Works
	@Test
	public void frontTest(){
		//Enqueue 5
		for(String word: words ){
			ll.enqueue(word);
		}
		//Prove Front of LL Queue is Equal to First Word Enqueued
		assertEquals(words[0], ll.front().toString());
	}
}
