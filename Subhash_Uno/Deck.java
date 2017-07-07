 

/**
 * Deck class is a class that generates all the required decks of cards and carries out all important functions in the Uno game.
 * 
 * @author Subhash
 * @version September 1st 2013
 */
public class Deck
{
   /**
    * cards is an instance variable of the Deck class which refers to an array of Uno cards.
    */ 
   Uno [] cards;
   /**
    * Constructor for objects of the Deck class which initializes a fully populated array of Uno cards.
    */
   public Deck()
   {
       cards = new Uno [108];
       int index = 0 ;             
       for (int colour = 0; colour < 4; colour++){
           for (int rank = 0;rank < 13; rank++) {
               if ( index == 0 || index == 25 || index == 50 || index == 75){
                   cards[index] = new Uno (colour,0);
                   index++;
               }
               else {
                   cards[index] = new Uno (colour,rank);
                   cards[++index] = new Uno (colour,rank);
                   index++;
               }
           }   
       }          
       int c = 0; // c is the loop variable.
       while (c < 4) { 
           cards[index] = new Uno (4,13);
           cards[index+4] = new Uno (4,14);
           index++;
           c++;
       }
   }
    
   /**
    * A parameterized constructor for objects of the Deck class which declares an array of Uno cards that has a capacity to store as many cards, as passed as the parameter. 
    * 
    * @param n the integer passed as a parameter which determines the length of the Deck object to be created.
    */
   
   public Deck(int n) {
       cards = new Uno[n];   
   }
  
   /**
    * subdeck creates a smaller deck in which only a few cards of this Deck are present.
    * 
    * @param low lowest index of the range.
    * @param high highest index of the range.
    * 
    * @return a subdeck of this Deck if (high - low + 1) is greater than 0 and null, if otherwise. 
    */
   public Deck subdeck (int low, int high) {
       if(high-low+1 <= 0) return null;
       Deck sub = new Deck (high-low+1); // sub refers to the subdeck.
       for (int i = 0; i < sub.cards.length; i++) {
           sub.cards[i] = this.cards[low+i];
       }       
       return sub;
   }
       
   /**
    * printDeck prints all the cards present in this Deck.
    */
   public void printDeck() throws Exception {
       for (int i = 0; i < cards.length; i++) {
           cards[i].printCard();
       }
   }
        
   /**
    * drawCards draws the required number of cards form the pile and adds them to this Deck.
    * 
    * @param pile the central pile of cards that haven't been played or belong to no one's hand.
    * @param n the number of cards to be drawn.
    */
   public void drawCards (Deck pile, int n) throws Exception {
       Deck newHand = new Deck(this.cards.length+n);
       int j = 0;
       while (j < n) {
           newHand.cards[j] = pile.cards[j];
           j++;
       }                    
       for (int i=n, k=0; i < newHand.cards.length; i++, k++) {
           newHand.cards [i] = this.cards[k];
       }       
       this.cards = newHand.cards;
   }
    
    /**
    * subdeckOverloaded is an overloaded version of subdeck which returns a subdeck of this Deck. The subdeck consists of every card in this Deck except the card passed to it as an argument.
    * 
    * @param card the card which is to be removed (or) the card except which the entire subdeck is to be made up of, from this Deck.
    */
   
   public void subdeckOverloaded (Uno card) {
       int n = card.findIndexOfCard (this.cards); // n stores the index of the card in the deck.
       if (n < 0 || this.cards.length==1) {
           this.cards = null;
           return;
       }
       Deck sub = new Deck (this.cards.length-1);
       if (n==0) {
           for (int i = 1; i < this.cards.length; i++) {
               sub.cards[i-1] = this.cards[i];
           }
       }
       
       else if (n==this.cards.length-1) {
           for ( int i = 0; i < this.cards.length-1;i++) {
               sub.cards [i] = this.cards[i];
           }
       }
       
       else {
           Deck sub1 = new Deck (this.cards.length-(n+1)); 
           Deck sub2 = new Deck (n);
           int x = 0; 
           for (int i = n+1; i < this.cards.length; i++) {
               sub1.cards[x] = this.cards[i];
               x++;
           }          
            for (int i = 0 ; i < n; i++){
               sub2.cards[i] = this.cards[i];
           }           
           sub1.sortdeck();
           sub2.sortdeck();
           sub = sub1.merge(sub2);
       }
       this.cards = sub.cards;
   }
         
   /**
    * merge merges two subdecks into either a bigger subdeck or a deck itself . It merges this Deck and the deck passed to it as a parameter.
    * 
    * @param d the deck to be merged with this Deck.
    * @return the merged Deck object.
    */
   
   public Deck merge (Deck d) {
       Deck result  = new Deck (this.cards.length+d.cards.length);
       int i = 0, j = 0, c = 0; 
       for (int k = 0; k < result.cards.length; k++) {
           if (i == this.cards.length) {
               result.cards [k] = d.cards [j];
               j++;
           }
           
           else if (j == d.cards.length) {
               result.cards [k] = this.cards [i];
               i++;
           }
           
           else {
               c = this.cards[i].compareCard(d.cards[j]);
               if (c==1) {
                   result.cards[k] = d.cards[j];
                   j++;
               }
               else{
                   result.cards[k] = this.cards [i];
                   i++;
               }    
           }
       }
       return result;
   }
    
    
   /**
    * sortDeck arranges this Deck from the lowest card it contains to the highest card it contains.  
    */
   
   public void sortdeck (){    
       Uno x = new Uno (0,0);
       int y = 0;
       for (int i = 0 ; i < this.cards.length ; i++) {
           y = this.findIndexOfLowestCard ( i , this.cards.length );
           swapCards(this.cards,i,y);
       }
   }
          
   /**
    * findIndexOfLowestCard finds the index of the lowest card in a specified range of Uno cards in this Deck.
    * 
    * @param lowIndex lowest index of the range.
    * @param highIndex highest index of the range.  
    * @return the index of the lowest card in the specified range. 
    */
   public int findIndexOfLowestCard (int lowIndex, int highIndex) {
       int lowestIndex = lowIndex;
       for (int i=lowIndex+1; i<highIndex; i++) {
           if (this.cards[lowestIndex].compareCard (this.cards[i]) > 0) {
               lowestIndex = i;        
           }
       }
       return lowestIndex;
   }
         
   /**
    * shuffleDeck shuffles this Deck using the algorithm where a card at i is chosen and swapped with a card at a random number between i and length of the deck.
    */
   public void shuffleDeck () {
       int r = 0;
       for (int i = 0; i < this.cards.length; i++) {
           r = randomInt (i,this.cards.length-1);
           swapCards(this.cards, i ,r);
       }
   }
         
   /**
    * If the game goes on for too long and there happens to be a stage where the pile gets over, then, as in any Uno game the played pile of cards is transformed into the new pile with only the top
    * card remaining as part of the original played pile - This is what usedPileTransform accomplishes. this Deck now contains only the card which it had at its zeroeth position originally (the top card). 
    *
    * @return a new deck which does not contain the top card or any card in the user's or computer's hand.
    */
   public Deck usedPileTransform () {
       Deck pile = new Deck (this.cards.length-1);
       for (int i = 1 ; i < this.cards.length ; i++) {
           pile.cards[i-1] = this.cards[i];
       }
       this.cards = pile.cards;
       this.shuffleDeck();
       return this;
    }
    
    /**
    * The randomInt method takes two integers, low and high, and returns a random integer x such that x is greater than or equal to low and x is also lesser than or equal to high.
    * 
    * @param low lowest index of the range.
    * @param high highest index of the range.
    * @return the randomly chosen integer in the specified range.
    */
   public static int randomInt (int low, int high) {
       high+=1;
       return (int) (low + Math.random()*(high-low));    
   }
   
   /**
    * sum returns the total number of Uno cards in the played deck, the user's hand and the the computer's hand.
    * 
    * @param playedDeck the deck of cards which have been played.
    * @param comp the computer's hand.
    * @param user the user's hand.
    * @return the sum of the lengths ofthe played deck, computer's hand and user's hand in the form of an integer.
    */
   public static int sum (Deck playedDeck, Deck comp, Deck user) {
       return playedDeck.cards.length + comp.cards.length + user.cards.length;
   }
   
   /**
    * swapCards takes a deck (array of cards) and two indices, and switches or swaps the cards at those two locations.
    * 
    * @param deck the deck in which the Uno card at the first index is switched or swapped with that at the second index.
    * @param n1 the index of the first card.
    * @param n2 the index of the second card.
    */
   public static void swapCards(Uno[] deck, int n1, int n2)
   {
       Uno temp = deck[n1]; // temp is a temporary Uno reference variable that stores the card to be swapped.
       deck[n1] = deck[n2];
       deck[n2] = temp;
   }
}