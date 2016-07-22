package bettercasino;

import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> hand;
	
	public Hand(int n){
		hand = new ArrayList<Card>(n);
	}
	
	public void addCard(Card card){
		hand.add(card);
	}
	
	public void show() {
		String out = "hand: ";
		for(Card card : hand){
			out += card.toString();
		}
		//Trim off trailing comma
		out = out.substring(0, out.length() - 1);
		System.out.println(out);
	}
}
