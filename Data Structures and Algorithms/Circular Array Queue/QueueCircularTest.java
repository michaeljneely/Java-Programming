package queue;

import javax.swing.JOptionPane;

public class QueueCircularTest {
	public static void main(String[] args) {
		// Create a Circular Array Queue with capacity 5
		Queue cQ = new QueueCircularArray(5); 

		String[] words = {"A", "B", "C", "D", "E"};

		//Enqueue 5 Words
		JOptionPane.showMessageDialog(null,"About to Enqueue: ");
		for(String word : words){
			try {
				cQ.enqueue(word);
				JOptionPane.showMessageDialog(null, word);
			} catch (QueueFullException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}

		//Dequeue All
		JOptionPane.showMessageDialog(null,"FinalQueue: ");
		while(! cQ.isEmpty()) {
			String word;
			try {
				word = (String)cQ.dequeue();
				JOptionPane.showMessageDialog(null, "Word dequeued: " + word);
			} catch (QueueEmptyException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}
	}
}