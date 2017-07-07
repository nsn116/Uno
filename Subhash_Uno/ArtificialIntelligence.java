 


/**
 * ArtificialIntelligence Class is chooses the card the computer wishes to play from its compHand . It abides by the rules and plays the game with the style of the programmer (Subhash) when he plays the game. 
 * 
 * @author Subhash
 * @version September 27th 2013
 */
public class ArtificialIntelligence
{
    /**
     * compPlay chooses a suitable card from its compHand and plays it in the game. It abides by the rules and always plays a valid card with reference to the top card. 
     * 
     * @param pile the centre pile of the game which may be used only if the computer needs to draw a card.
     * @param compHand the computer's hand from which a card is to be played.
     * @param top the top card of the played pile which is used as a reference for the computer to play its card.
     * @param userHand the user's hand which is used as a reference for the computer to play a draw 2 based on the circumstance - if the user's hand has lesser cards than the computer,
     * then the computer plays a valid draw 2, if it has one.
     * 
     * @return the Uno card which is to be played by the computer, if it can and null, if it can't.
     */
    
    public static Uno compPlay (Deck pile, Deck compHand , Uno top, Deck userHand) throws Exception {
        int [] s;
        int m; 
        int target;
        int f;
        int x;
        int z;
        int c;
        Uno card;        
        if (top.isWild() || top.isWildDraw4()) {
            s = Uno.colourHist(compHand.cards);
            s[4] = 0;
            m = max (s);
            target = search(s,m);
            card = new Uno (target,11);
            f = card.findIndexOfCard (compHand.cards);
            if (f > -1) {
                return card;
            }
            card = new Uno (target,10);
            f = card.findIndexOfCard (compHand.cards);      
            if (f > -1) {
                return card;
            }          
            for (int i = 0 ; i < compHand.cards.length; i++) { 
                if (compHand.cards[i].colour == target && compHand.cards[i].rank != 12) {
                    return compHand.cards[i];
                }
            }
            card = new Uno (target,12);
            f = card.findIndexOfCard (compHand.cards);
            if (f > -1) {
                return card;
            }
        }
        s = Uno.colourHist (compHand.cards);
        card = new Uno();
        x = s[top.colour];
        if (x > 0) {
            if (userHand.cards.length < compHand.cards.length) {
                card = new Uno (top.colour,12);
                f = card.findIndexOfCard (compHand.cards);
                if (f > -1) {
                    return card;
                }       
            }
            card = new Uno (top.colour,11);
            f = card.findIndexOfCard (compHand.cards);
            if (f > -1) {
                return card;
            }
            card = new Uno (top.colour,10);
            f = card.findIndexOfCard (compHand.cards);
            if (f > -1) {
                return card;
            }
            for (int i = 0 ; i < compHand.cards.length; i++) { 
                if ((compHand.cards[i].equalColour(top)) && (compHand.cards[i].rank != 12)) {
                    card = compHand.cards[i];
                    return card;
                }
            }
            card = new Uno (top.colour,12);
            f = card.findIndexOfCard (compHand.cards);
            if (f > -1) {
                return card;
            }
        }        
        if (x == 0) {
            z = zeroes(s);
            c = s.length - z;
            for (int i = 0; i < c; i++) {
                m = max(s);
                target = search(s,m);
                for (int j = 0 ; j < compHand.cards.length; j++) {
                    if ((compHand.cards[j].equalRank(top)) && (compHand.cards[j].colour == target)) {
                        card = compHand.cards[j];
                        return card;
                    }
                }
                s[target] = 0;
            }
        }
        card = new Uno (4,13);
        f = card.findIndexOfCard(compHand.cards);
        if (f > -1) {
            return card;
        }
        card = new Uno (4,14);
        f = card.findIndexOfCard(compHand.cards);
        if (f > -1) {
            return card;
        }
        String a = "The computer has no card to play. It wishes to draw a card. ";
        Game.slowPrint(a);
        System.out.println();    
        compHand.drawCards(pile,1);
        pile.subdeckOverloaded(pile.cards[0]);
        if (compHand.cards[0].equalColour(top) || compHand.cards[0].equalRank(top) || compHand.cards[0].colour == 4) {
            card = compHand.cards[0];
            return card;
        }
        return null;
    }
    
    /**
     * maxInRange takes an array of integers and a range of indices (lowIndex and highIndex), and finds the largest value in the array, considering only the elements between lowIndex and highIndex, 
     * including both ends.
     * 
     * @param a the array in which the largest value is to be returned.
     * @param lowIndex the beginning index of the range of indices.
     * @param highIndex the ending index of the range of indices.
     * @return the largest integer value present in the array. 
     */
    public static int maxInRange(int[]a,int lowIndex, int highIndex)
    {
        int max = a[lowIndex];
        int i =lowIndex;
        if (lowIndex==highIndex||a.length==1) {
            return a[0];
        }
        if (a.length==0){
            return -1;
        }
        else {
            while (i < highIndex ) {
                if (max < a[i+1] ) max = a[i+1];
                i++;
            }
            return max;
        }        
    }
    
    /**
     * max is a method that takes an array as a parameter and that uses maxInRange to find and return the largest value.
     *
     *@param array the array in which the largest value is to be returned . 
     *@return the largest value present in the array, in the form of an integer.
     */
    
    public static int max (int [] array) {
        return maxInRange (array,0,array.length-1);
    }
    
    /**
     * search is a method which takes an array of integers and a target integer. It returns the index of the first location where the target integer appears in the array, or -1 if it does not appear.
     * 
     * @param array the array in which the target integer is to be searched for.
     * @param target the integer to be searched for in the array.
     * @return the index of the target integer in the array, in the form of an integer.   
     */
    public static int search (int[] array , int target ) { 
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * zeroes is a method which searches for the number of zeroes in an array passed to it as a parameter. 
     * 
     * @param a the array in which the number of zeroes are to be searched for.
     * @return the number of zeroes present in the array in the form of an integer. 
     */
    public static int zeroes (int [] a) {
        int c = 0; 
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                c++;
            }
        }
        return c;
    }
}