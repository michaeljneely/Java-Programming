package bettercasino;

import java.util.ArrayList;

import casino.Card;

public class Deck {

	private ArrayList<Card> cards;
	private int index;
	
	public Deck() throws BadCardException{
		//Try to fill deck with cards
		this.cards = new ArrayList<Card>(Card.suits.length * Card.ranks.length);
		index = 0;
		for(int s = 0; s < 4; s ++){
			for(int r = 0; r < 13; r++){
				cards.add(index, new Card(s,r));
				index++;
			}
		}
	}
	
	public String toString(){
		String out = "Deck: ";
		for(Card card : cards){
			out += card.toString();
		}
		//Trim off trailing comma
		out = out.substring(0, out.length() - 1);
		return out;
	}
	
	public void shuffle(){
		Card temp = new Card(0,0); //Create temporary Card object for swapping
		for(int i=0; i < cards.size(); i++){ //Loop through deck
			int rand = i + (int)(Math.random() * (cards.size() - i)); //Get random index between [i,size-1]
			//Swap cards
			temp = cards.get(rand);
			cards.set(rand, cards.get(i));
			cards.set(i, temp);
		}
	}
	
	public Card getCard(int n) throws EmptyDeckException{
		if (cards.size() == 0){
			throw new EmptyDeckException("The Deck is empty");
		}
		return this.cards.get(n);
	}
	
	public Card removeTopCard() throws EmptyDeckException{
		if (cards.size() == 0){
			throw new EmptyDeckException("The Deck is empty. Cannot remove a Card.");
		}
		Card temp = cards.get(0);
		cards.remove(0);
		return temp;
	}
	
	public int size(){
		return this.cards.size();
	}
}
