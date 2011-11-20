import java.util.Random;

/**
 * Een deck met Cards
 * 
 * @author David van Zessen
 *
 */
public class Deck {
	private Card[] cards;
	private int size;

	/**
	 * Constructor
	 */
	public Deck(){
		size = 0;
		cards = new Card[size];
	}
	
	public Deck(boolean fill){
		size = 0;
		if(fill){
			fillDeck();
		} else {
			cards = new Card[size];
		}
	}

	/**
	 * Vult de array met 52 kaarten: 2,3 ... ,10,V,B,K,A van klaveren, schoppen,
	 * harten en ruiten.
	 */
	public void fillDeck(){
		cards = new Card[0];
		for(int suit = 0;suit < 4;++suit){
			for(int number = 1;number < 14;++number){
				addCard(new Card(suit,number));
			}
		}
	}

	/**
	 * Zoals gezegd is dit spel een beetje vreemd. Bijvoorbeeld: spelers kunnen
	 * kaarten toevoegen aan het deck. Hierdoor kan het aantal kaarten groter
	 * worden dan 52.
	 * 
	 * @param card
	 *            een Kaart
	 * @param index
	 *            Op positie
	 */
	public void insertAt(Card c,int index){
		if(index >= size){
			index = size;
		} else if (index < 0){
			index = 0;
		}
		Card[] tmp = cards;
		cards = new Card[++size];
		for(int a = 0;a < index;++a){
			cards[a] = tmp[a];
		}
		cards[index] = c;
		for(int a = index+1;a < cards.length;++a){
			cards[a] = tmp[a-1];
		}
	}
	
	/**
	 * Voeg een kaart toe bovenop de stapel.
	 * 
	 * @param c
				De kaart die moet worden toegevoegd.
	 */
	public void addCard(Card c){
		Card[] tmp = cards;
		cards = new Card[tmp.length + 1];
		for(int a = 0;a < tmp.length;++a){
			cards[a] = tmp[a];
		}
		cards[size++] = c;
	}
	/**
	 * Kaarten kunnen ook verwijderd worden uit het deck. delete Haalt de kaart
	 * met een bepaalde index er uit.
	 * 
	 * Merk op: na delete is de array zo groot als het aantal elementen dat er in zit.
	 * 
	 * @param index
	 */
	public void delete(int index){
		if(index >= size){
			index = size;
		} else if (index < 0){
			index = 0;
		}
		Card[] tmp = cards;
		cards = new Card[--size];
		for(int a = 0;a < index;++a){
			cards[a] = tmp[a];
		}
		for(int a = index;a < cards.length;++a){
			cards[a] = tmp[a+1];
		}
	}

	/**
	 * Schud alle kaarten zodat de volgorde 'willekeurig' is. Hiervoor is het
	 * toegestaan de Java Random generator te gebruiken.
	 *  @param i
				Aantal keer dat shuffle() zal worden aangeroepen;
	 */
	public void shuffle(int i){
		for(int a = 0;a < i;++a){
			shuffle();
		}
	}
	/**
	 * Schud alle kaarten zodat de volgorde 'willekeurig' is. Hiervoor is het
	 * toegestaan de Java Random generator te gebruiken.
	 * 
	 */
	public void shuffle(){
		Random random = new Random(1321366217781l);
		for(int a = 0;a < cards.length;++a){
			swapCards(a,random.nextInt(cards.length));
		}
	}

	/**
	 * Een gegeven kaart moet worden opgezocht in de array, en de index ervan
	 * moet als return worden teruggegeven. Zie [Hubbard p.30]
	 * 
	 * @param card
	 *            de kaart die gezocht wordt
	 * @return De index van de gevonden kaart, -1 als de kaart niet in het deck is.
	 */
	public int sequentialSearch(Card c){
		for(int a = 0;a < size;++a){
			if(cards[a].equals(c)){
				return a;
			}
		}
		return -1;
	}

		
	public void bubbleSort(){
		boolean done = false;
		while(!done){
			done = true;
			for(int a = 0;a < (cards.length - 1);++a){
				if(cards[a].compareTo(cards[a+1]) > 0){
					swapCards(a,a+1);
					done = false;
				}
			}
		}
	}
	
	public void selectionSort(){
		int lowestIndex, lowestValue;
		for(int a = 0;a < cards.length;++a){
			lowestIndex = a;
			for(int b = a;b < cards.length;++b){
				if(cards[b].getCardValue() < cards[lowestIndex].getCardValue()){
					lowestIndex = b;
				}
			}
			swapCards(a,lowestIndex);
		}
	}
	
	public void insertionSort(){
		Card tmp;
		int b;
		for(int a = 1;a < cards.length;++a){
			tmp = cards[a];
			b = a;
			while(b > 0 && cards[b-1].getCardValue() > tmp.getCardValue()){
				cards[b] = cards[b-1];
				b--;
			}
			cards[b] = tmp;
		}
	}
	
	private int partition(int left,int right){
		int pivotIndex = cards[((left + right) / 2)].getCardValue();
		Card tmp;
		while(left <= right){
			while(cards[left].getCardValue() < pivotIndex){
				left++;
			}
			while(cards[right].getCardValue() > pivotIndex){
				right--;
			}
			if(left <= right){
				//swapCards(left,right);
				tmp = cards[left];
				cards[left] = cards[right];
				cards[right] = tmp;
				left++;
				right--;
			}
		}
		return left;
	}
	
	private void quickSort(int left, int right){
		int index = partition(left,right);
		if(left < index - 1){
			quickSort(left,index-1);
		}
		if(index < right){
			quickSort(index,right);
		}
	}
	
	public void quickSort(){
		quickSort(0,cards.length-1);
	}
	
	/**
	 * Legt de kaarten op volgorde. We nemen aan dat een deck op volgorde ligt,
	 * als de volgorde hetzelfde is als na {@link #fillDeck()}
	 */
	public void sort(){
		//long start = System.currentTimeMillis();
		quickSort();
		//bubbleSort();
		//selectionSort();
		//insertionSort();
		//System.out.println((System.currentTimeMillis() - start));
	}

	/**
	 * Een bepaalde kaart moet worden opgezocht in de gesorteerde array op de
	 * binary search manier zoals besproken in [Hubbart p.31].
	 * 
	 * @param card
	 *            de kaart die gezocht wordt
	 * @return De index van de gevonden kaart, -1 als de kaart niet gevonden is.
	 */
	public int binarySearch(Card c){
		if(!sorted()){
			sort();
		}
		int left = 0,right = size - 1;
		int center;
		do{
			center = (left + right) / 2;
			if(cards[center].equals(c)){
				return center;
			}
			if(c.getCardValue() > cards[center].getCardValue()){
				left = center + 1;
			} else {
				right = center - 1;
			}
		} while(!cards[center].equals(c) || (left > right));
		return -1;
	}
	
	/**
	 *  Check of het deck gesorteerd is.
	 
		@return true als het deck gesorteerd is, false als het niet gesorteerd is.
	 */
	
	public boolean sorted(){
		for(int a = 0;a < (cards.length - 1);++a){
			if(cards[a].compareTo(cards[a+1]) > 0){
				return false;
			}
		}
		return true;
	}

	private void swapCards(int a,int b){
		Card tmp = cards[a];
		cards[a] = cards[b];
		cards[b] = tmp;
	}
	
	/**
	 *  Pretty-print het deck.
	 */
	@Override
	public String toString(){
		String s = "";
		for(int a = 0;a < size;++a){
			s += a+": "+cards[a].toString()+"\n";
		}
		return s;
	}
}
