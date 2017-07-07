 



/**
 * The Uno class generates Uno cards which are used in the Game of Uno later and contains all the methods required to operate on Uno objects.
 * 
 * @authour Subhash
 * @version September 4th 2013 
 */
public class Uno
{
    /**
     * colour is the an instance variable of the Uno class which shows the colour of a particular Uno card ---> 0 stands for a red Uno card, 1 ---> stands for a blue Uno card, 2 ---> stands for a 
     * yellow card, 3 ---> stands for a green card and 4 ---> stands for a Wild card or a Wild Draw 4 card. 
     *
     */    
    int colour;    
    /**
     * rank is the an instance variable of the Uno class which shows the rank of a particular Uno card ---> integers from 0 to 9 stand for their respective numerical ranks, 10 stands for a Skip, 11 
     * stands for a reverse, 12 stands for a Draw 2, 13 stands for a Wild and 14 stands for a Wild Draw 4.
     *
     */
    int rank;    
    /**
     * Default constructor for the Uno class which initializes Uno cards by initializing their instance variables.
     */
    public Uno () {
        colour  = 0;
        rank = 0;
    }
    
    /**
     * Parameterized Constructor for objects of the Uno class which initializes Uno cards by initializing their instance variables as per the parameters given. 
     * 
     * @param colour colour of the Uno Card
     * @param rank rank of the Uno Card.
     * 
     */
    public Uno(int colour,int rank) {
        this.colour = colour;
        this.rank = rank;
    }
       
    /**
     * printCard prints this Uno card in a user friendly format.
     */
    public void printCard() throws Exception {
        String[] colours = {"Red" , "Blue" , "Yellow" , "Green" , "Wild"};
        String[] ranks = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Skip","Reverse", "Draw 2", "" , "Draw 4"}; 
        String colour = colours [this.colour];
        String rank = ranks [this.rank];
        String s = "";
        if (this.rank == 13) {
            Game.slowPrint(colour);
        }
        else if (this.rank == 14){
            s = "Wild Draw 4";
            Game.slowPrint(s);
        }
        else {
            s = rank + " of " + colour;
            Game.slowPrint(s);
        }
    }
    
    /**
     * equalColour checks if this Uno card has the same colour as the card passed to it as a parameter.
     * 
     * @param c the card passed as a parameter whose colour is to be checked with this Uno card. 
     * @return true if the colours of the cards are equal and false, if otherwise.
     */
    public boolean equalColour (Uno c) {
        if (this.colour==c.colour) return true;
        return false;
    }
    
    /**
     * equalRank checks if this Uno card has the same rank as the card passed to it as a parameter.
     * 
     * @param c the card passed as a parameter whose rank is to be checked with this Uno card. 
     * @return true if the ranks of the cards are equal and false, if otherwise.
     */
    public boolean equalRank (Uno c) {
        if (this.rank==c.rank) return true;
        return false;
    }
    
    /**
     * findCard finds this Uno card in a deck of Uno cards and returns its index and 0, if otherwise.
     * 
     * @param deck the deck in which this Uno card is to be searched for.
     * @return the index of the this Uno card in the array of Uno cards.
     */
    public int findIndexOfCard (Uno[] deck) {
        for (int i = 0; i< deck.length; i++) {
            if (this.sameCard (deck[i])) return i;
        }
        return -1;
    }
    
    /**
     * sameCard checks if this Uno card is the same as the card passed to it as a parameter.
     * 
     * @param c the card with which this Uno card is to be compared with and checked if they are the same or not.
     * @return true if the two cards are the same and false if they are not the same.
     */
    public boolean sameCard (Uno c) {
        if (this.rank == c.rank && this.colour == c.colour){
            return true;
        }
        else{
            return false;
        }
    }        
    
    /**
     * compareCard is a method that compares two cards and returns 1 if this Uno card is greater than another card and -1 if the second card 
     * is greater than this Uno card and 0 if they are equal.
     * 
     * @param c the card which is to be compared with this Uno card.
     * @return 1 if this Uno card has a greater rank or colour than c or -1 if it is vice versa or 0 if they are the same. 
     */
    public int compareCard (Uno c) {
        
        if (this.colour > c.colour){
            return 1;
        }
        if (this.colour < c.colour){ 
            return -1;
        }
        if (this.rank > c.rank){
            return 1;
        }
        if (this.rank < c.rank){
            return -1;
        }
        return 0;
    }
    
    /**
     * isWild checks if this Uno card is a wild or not.
     *
     * @return true if this Uno card is a wild and false, if otherwise.
     */
    
    public boolean isWild () {
        if (this.colour==4 && this.rank == 13){
            return true;
        }
        return false;
    }
    
    /**
     * isWildDraw4 checks if this Uno card is a wild draw 4 or not.
     *
     * @return true if this Uno card is a wild draw 4 and false, if otherwise.
     */
    
    public boolean isWildDraw4 () {
        if (this.colour==4 && this.rank == 14){
            return true;
        }
        return false;
    }
    
    /**
     * makeUsedPile adds this Uno card to the played pile to make a new played pile.
     * 
     * @param deck the previous played pile to which this Uno card is to be added.
     * @return a new played pile in the form of a Deck object.
     */
    
    public Deck makeUsedPile (Deck deck) throws Exception{
        Deck d = new Deck (deck.cards.length + 1); // d stands for deck2 which is the new deck that is later aliased to deck before deck is returned.
        d.cards[0] = this;
        for (int i=1; i<d.cards.length; i++) {
            d.cards[i] = deck.cards[i-1];
        }
        deck = d;
        return deck;              
    }
    
    /**
     * parseCard takes a String and returns the corresponding card.
     * 
     * @param s the card written in common English tongue.
     * @return an Uno card based on the String passed to it, if it is valid and null if otherwise.
     */
    public static Uno parseCard(String s)
    {
        Uno card = new Uno (0,0);
        s = s.toLowerCase();
        if (s.length() > 4){            
            if (s.equals("wild draw 4")){
                card.colour = 4;
                card.rank = 14;
                return card;
            }
            else if (s.indexOf (" ") == -1) {
                return null;
            }
            else {
                String rank = s.substring(0,(s.indexOf(" "))); // rank stores the rank of the card in a String format.
                String colour = ""; // colour stores the colour of the card in a String format.
                if (rank.equals("draw")) {
                    rank = s.substring(0,(s.indexOf(" ",5))); 
                    colour = s.substring(s.indexOf(" ",5) + 4);
                }  
                else {
                    String c = ""; // c is a String that stores the colour in a reversed format.
                    int x = s.length()-1;
                    while (s.charAt(x) != ' ' && s.length() > -1){
                        c = c + s.charAt(x);
                        x--;
                    }
                    x = c.length()-1;
                    while (x > -1) { 
                        colour = colour + c.charAt(x);
                        x--;
                    }                    
                    rank =  s.substring(0,(s.indexOf(" ")));
                }
                int c = -1;
                int  index = -1;
                String[] colours = {"red" , "blue" , "yellow" , "green"};
                for (int i = 0;i < colours.length;i++){
                    if (colours[i].equals(colour)) { 
                        c = i;    
                    }
                }
                String[] ranks = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "skip","reverse", "draw 2"}; 
                for (int i = 0;i < ranks.length;i++){
                    if (ranks[i].equals(rank)) { 
                        index = i;    
                    }
                }
                if (index == -1 || c == -1){
                    return null;
                }
                else { 
                    return new Uno (c,index);
                }
            }
        }           
        else {
            if (s.equals("wild")) {
                card.colour = 4;
                card.rank = 13;
                return card;
            }
            else {
                return null;
            }
        }
    }   
        
    /**
     * colourHist returns a histogram of colours of the deck of Uno cards that is passed as a parameter to it.
     * 
     * @param deck the deck of Uno cards whose histogram of colours is to be returned.
     * @return a histogram of the colours of Uno cards in the array of Uno[] cards passed to it as a parameter.
     */
    
    public static int[] colourHist (Uno [] deck) {
        int [] hist = new int [5];
        for (int i = 0; i < deck.length ; i++) {
            hist[deck[i].colour]++;
        }
        return hist;
    }
}