/** M Madden, M Schukat, 2005 - 2015: Abstract Stack interface */

package StackArray;

import javax.swing.JOptionPane;

public class StackTest 
{
	public static void main(String[] args) 
	{
		// Create a Stack
		Stack s = new ArrayStack(20); 
		// NOTE: If I had a different stack implementation, 
		// I could substitute it for ArrayStack.
		
		// Put some strings onto the stack
		JOptionPane.showMessageDialog(null,	"About to push words onto stack: \nOnce upon a time");
		s.push("Once");
		s.push("upon");
		s.push("a");
		s.push("time");

		// Now pop them from the stack
		JOptionPane.showMessageDialog(null, "About to pop words from stack ...");
		while(! s.isEmpty()) {
			String word = (String)s.pop(); // Note: have to cast Objects popped to String
			JOptionPane.showMessageDialog(null, "Word popped: " + word);
		}
		
		System.exit(0);
	}

}
