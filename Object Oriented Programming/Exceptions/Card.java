package bettercasino;

public class Card implements Comparable<Card> {

	private int suit;
	private int rank;
	public final static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
	public final static String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	//Constructor for Card
	//Only create card if suit and rank are in correct bounds
	public Card(int s, int r) throws BadCardException{ 
		if( s < 0 || s > 3) throw new BadCardException("Suit must be between 0 and 3");
		if(r < 0 || r > 12) throw new BadCardException("Rank must be between 0 and 12");
		this.suit = s;
		this.rank = r;
	}
	
	//Overriding Equals
	@Override
	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if (!(obj instanceof Card)){
			return false;
		}
		Card c = (Card) obj;
		return (this.rank == c.rank && this.suit == c.suit) ? true: false;
	}
	
	//Overriding Compare To
	/*
	 * Returns
	 	* 1 if this card is greater
	 	* -1 if this card is lesser
	 	* 0 if they are equal 
	 */
	@Override
	public int compareTo(Card c){
		if(this.equals(c)) return 0;
		if (this.suit > c.suit) return 1;
		if (this.suit < c.suit) return -1;
		if (this.rank > c.rank) return 1;
		if (this.rank < c.rank) return -1;
		else return 0;
	}
	
	//Return String representation of the card
	public  String toString() {
		String representation = ranks[rank] + " of " + suits[suit];
		return representation;
	}
	
	//Getter for Suit
	public int getSuit(){
		return this.suit;
	}
	
	//Getter for Rank
	public int getRank(){
		return this.rank;
	}

}
