public class Card implements Comparable
{
	public static final int SPADES = 0;
	public static final int HEARTS = 1;
	public static final int DIAMONDS = 2;
	public static final int CLUBS = 3;
	
	
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;
	public static final int NINE = 8;
	public static final int TEN = 9;
	public static final int JACK = 10;
	public static final int QUEEN = 11;
	public static final int KING = 12;
	public static final int ACE = 13;

	private int suit;
	private int number;
	
	
	public Card(int _suit,int _number){
		suit = SPADES;
		if(_suit >= 0 && _suit <= 3){
			suit = _suit;
		}
		number = ACE;
		if(_number >= 1 && _number <= 12){
			number = _number;
		}
	}
	
	public String toString(){
		String s = "";
		switch(suit){
			case SPADES: s = "Spades"; break;
			case HEARTS: s = "Hearts"; break;
			case DIAMONDS: s = "Diamonds"; break;
			case CLUBS: s = "Clubs"; break;
		}
		String n = "";
		switch(number){
			case TWO: n = "Two"; break;
			case THREE: n = "Three"; break;
			case FOUR: n = "Four"; break;
			case FIVE: n = "Five"; break;
			case SIX: n = "Six"; break;
			case SEVEN: n = "Seven"; break;
			case EIGHT: n = "Eight"; break;
			case NINE: n = "Nine"; break;
			case TEN: n = "Ten"; break;
			case JACK: n = "Jack"; break;
			case QUEEN: n = "Queen"; break;
			case KING: n = "King"; break;
			case ACE: n = "Ace"; break;
		}
		
		return (n+" of "+s+"   "+getCardValue());
	}
	
	
	public int compareTo(Object o){
		Card c = (Card)o;
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		if(this == c) {
			return EQUAL;
		}
		if(suit < c.getSuit()){
			return BEFORE;
		}
		if(suit > c.getSuit()){
			return AFTER;
		}
		if(number < c.getNumber()){
			return BEFORE;
		}
		if(number > c.getNumber()){
			return AFTER;
		}
		return 0;
	}
	
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		
		try{
			Card c = (Card)o;
			if(suit == c.getSuit() && number == c.getNumber()){
				return true;
			}
		} catch (Exception e){
			return false;
		}
		return false;
	}
	
	public int getCardValue(){
		return ((suit * 13) + number);
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int _number){
		number = _number;
	}
	
	public int getSuit(){
		return suit;
	}
	
	public void setSuit(int _suit){
		suit = _suit;
	}
}