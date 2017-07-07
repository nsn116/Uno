 

/**
 * Player class takes input from the user and plays the respective Uno card from the player's hand. It also checks the if input given by the player is valid or not and the card he/she chooses to
 * play is valid or not.
 * 
 * @author Subhash
 * @version September 25th 2013
 */
public class Player
{
    /**
     * The method userPlay checks if the card chosen by the user is present in his/her deck and if it is, it checks if it is valid or not - if it can be played, the card is returned otherwise null is returned.
     * If the card is not present in the deck it returns null. 
     * 
     * @param hand the player's hand from which the Uno card is to be played.
     * @param top the top card which is used to check if the card chosen by the user is a valid card or not and if it can be played or not.
     * @param s the card chosen by the user in the form of a String.
     * @return the Uno card chosen by the user if it is valid as per the rules of the game, otherwise null is returned. 
     */
    public static Uno userPlay (Deck hand , Uno top, String s) {
        Uno card = Uno.parseCard(s);
        if (card == null) {
            return null;
        }        
        int n = card.findIndexOfCard(hand.cards);
        if (n < 0) {
            return null;
        }        
        if (top.colour == 4 && card.colour != 4) {
            return card;
        }       
        
        else {
            Uno c1 = new Uno (4,13);
            Uno c2 = new Uno (4,14);
            if (card.equalColour(top) || card.equalRank(top)) {
                return card;
            }
            
            else if (card.sameCard(c1)) {
                if (top.colour == 4) {
                    return null;
                }
                
                else {
                    return card;
                }
            }
                       
            else if (card.sameCard(c2)) {
                int w = -1;                
                for (int i = 0 ; i < hand.cards.length ; i++) {
                    if (hand.cards[i].equalColour(top)) {
                        w = 0;
                        break;
                    }                    
                }                
                if (w == -1 && top.colour != 4) {
                    return card;
                }
                
                else {
                    s = "You cannot play a Wild Draw 4 as it is not allowed. Please follow the rules. ";
                    return null;
                }                
            }
            
            else {
                return null;
            }
        }
    }
}