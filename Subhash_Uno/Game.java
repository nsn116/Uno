 
import java.io.*;

/**
 * Game class is where the main operation of the Uno Card Game takes place (or) simply it is the site of all execution. It contains all the methods required to create the interface and allow the playing 
 * of the Game of UNO.
 * 
 * @author Subhash
 * @version September 13th 2013
 */
public class Game
{
    
    /**
     * backgroundInterface is a method that builds up the interface of the game.
     *
     * @return the String value based on which the game starts or ends. If the String is a no then the game stops then and there. Anything else will lead to start of the game.   
     */
    public static String backgroundInterface() throws Exception {
        String s = "                                                                                                Welcome to the : ";
        slowPrint(s);
        s = "                                                                                                    GAME OF ";
        slowPrint(s);                                                              
        s = "                                                                                         |      |            _____  ";
        slowPrint(s);                                                              
        s = "                                                                                         |      | | \\    |  |     | ";
        slowPrint(s);                                                                            
        s = "                                                                                         |      | |  \\   |  |     | ";
        slowPrint(s);                                                                            
        s = "                                                                                         |      | |   \\  |  |     | ";
        slowPrint(s);                                                                            
        s = "                                                                                         |______| |    \\ |  |_____| ";
        slowPrint(s);
        System.out.println ("\n\n\n\n");
        s = "What is your name ? Please type in your name only (could lead to unexpected things if you didn't). ";
        slowPrint(s);
        System.out.println ();
        InputStreamReader in = new InputStreamReader (System.in);
        BufferedReader br = new BufferedReader (in);
        s = br.readLine();
        System.out.println();
        s = "So, " + s + " are you interested in playing the Game of Uno ??? Please type 'yes' or 'no' (without single quotes, anything else could lead to the start of the game).";
        slowPrint(s);
        System.out.println();
        s = br.readLine();
        return s;
    }
    
    /**
     * slowPrint prints the String passed to it slowly with a gap of 1/10th of a second between each character.
     * 
     * @param s the String to be printed at a speed of 1/10th of a second.
     */
    
    public static void slowPrint (String s) throws InterruptedException {
        for (int i = 0 ; i < s.length(); i++){
            Thread.sleep(10);
            System.out.print (s.charAt(i));
        }
        System.out.println ();
    }
            
    /**
     * slowPrintOl1 is the overloaded version of slowPrint which prints the String passed to it slowly with a gap of 1/500th of a second between each character.
     * 
     * @param s the string to be printed at a speed of 1/500th of a second.
     */
    
    public static void slowPrintOl1 (String s) throws InterruptedException {
        for (int i = 0 ; i < s.length(); i++){
            Thread.sleep(2);
            System.out.print (s.charAt(i));
        }
        System.out.println ();
    }
    
    /**
     * intro is a method that gives the introduction to the game and prints the rules for the user if the user wants. 
     *       
     */
    public static void intro () throws Exception {
        String s = backgroundInterface();
        System.out.println();
        if (s.compareToIgnoreCase("no")==0) {
            slowPrint("You have missed out on what people call real fun !!!");
            System.exit(0);
        }
        else {
            s = "Do you want to know the rules of the game ? Please type 'yes' or 'no' (without single quotes , anything else would lead to the printing of the rules). ";
            slowPrint(s);
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            System.out.println ();
            s = br.readLine();
            if (s.compareToIgnoreCase("no")==0) {
                additionalRules();
            }
            else {
                rules();
                additionalRules();
            }
        }
    }
    
    /**
     * additionalRules is a method in which some rules that have to be printed for the user as they are required for playing the Game of Uno on the computer, are printed.
     *
     */
    public static void additionalRules () throws Exception {
        String s = "\n\n\n" + "One IMPORTANT Rule: The Number Of Cards The Computer Has Will Always Be Given So You Have To Say 'caught' Instead Of Typing Your Card When The Computer Has Played Its Second Last Card And Has Forgotten To Say 'uno' . \nWhen You Have Two Cards Left It Is Also Upto To You To Say 'uno' With A Space At The End Of The Command You Pass To The Computer While Playing The Second Last Card!!! ";
        slowPrintOl1 (s);
        s = "Example for saying uno : IF Your Second Last Card Is A Red Skip ---> The Command You Have To Give Is : Skip Of Red uno" ;
        slowPrintOl1 (s);
        s = "The game is sensitive of whitespaces (spaces, new lines), punctuation marks, symbols and numbers --- so please do not type them at the end of your command.";
        slowPrintOl1(s);
    }
    
    /**
     * printDivide is method which divides the user interface using a barrier of underscores to separate the play field fromthe introductory space.
     * 
     */
    public static void printDivide () throws Exception {
        for (int i = 0 ; i < 200; i++) {
            System.out.print("_");
        }
        System.out.println ("\n\n\n");
    }
    
    /**
     * rules is a method that prints all the rules of The Game Of UNO.
     *
     */
    public static void rules () throws Exception {
        FileReader fr = new FileReader ("UnoRules.txt");
        BufferedReader br = new BufferedReader (fr);
        String s = br.readLine();
        int i = 1;
        while (s != null){
            slowPrintOl1(s);
            s = br.readLine();
        }
    }
    
    /**
     * sayUno decides whether to say 'uno' or not, based on a random integer it receives from the randomInt method in the Deck class and the difficulty level chosen by the user.
     * 
     * @param n the integer which is passed to the sayUno method based on the difficulty level using which the method returns the String "uno" or returns "".
     * @return the String which either contains "uno" or nothing in it ("").
     */
    
    public static String sayUno (int n) {
        int r = Deck.randomInt (0,11) + 1;
        if (n == 25) {
            if (r > 0 && r < 4) {
                return "uno";
            }
        }           
        if (n == 50) {
            if (r > 0 && r < 7) {
                return "uno";
            }
        }        
        if (n == 75) {
            if (r > 0 && r < 10) {
                return "uno";
            }
        }        
        return "";
    }
    
    /**
     * colour returns the colour of the card the computer wishes to play provided the card is not a Wild or a Wild Draw 4.
     * 
     * @param n the colour of the Uno card played by the computer encoded in the form of an integer. 
     * @return the colour of the card in the form of a String.
     */
    public static String colour (int n) {
        String s = "";
        if (n == 0) {
            s = "red";
        }        
        if (n == 1) {
            s = "blue";
        }        
        if (n == 2) {
            s = "yellow";
        }        
        if (n == 3) {
            s = "green";
        }        
        return s;
    }
    
    /**
     * startPlay method is the method where all the classes made are used and their methods are invoked to make a proper command-based stimulation of the Game Of Uno.
     * 
     */
    
    public static void startPlay () throws Exception {
        String s = "What is the difficulty level you would like to play ? Please type 'easy' , 'medium' , 'hard'. Anything else would lead to a default version of difficulty level : EASY.  ";
        slowPrint(s);
        System.out.println();     
        InputStreamReader in = new InputStreamReader (System.in);
        BufferedReader br = new BufferedReader(in);
        s = br.readLine();
        printDivide();
        System.out.println("\n\n\n\n\n");
        String dL = s.toLowerCase(); // dL stores the difficulty level chosen by the user.
        int c = 0, n = 0, r = 0; 
        // c stands for chance : as in the computer's chance or the user's chance. n stands for number which is an integer variable that holds the sum of the lenghts of the computer's, user's hands and 
        // the played pile. r is an integer that holds a random integer between 1 and 100 to ward off logical errors related to the user saying 'uno'. 
        
        Deck deck = new Deck();
        deck.shuffleDeck();
        Deck userHand = deck.subdeck(0,6);
        userHand.sortdeck();
        Deck compHand = deck.subdeck(7,13);
        compHand.sortdeck();
        Uno card2 = new Uno(); // card2 stores the top card in case userPlay or compPlay return null. If this happens, top is aliased to card2 to prevent errors.
        Deck played  = new Deck(0);        
        for(int i = 0; i < 14; i++) {
            played = deck.cards[0].makeUsedPile(played);
            deck.subdeckOverloaded (deck.cards[0]);           
        }
        Uno top = deck.cards[0];
        if (top.rank == 14) {
            while (top.rank == 14) {
                deck.shuffleDeck();
                top = deck.cards[0];
            }
        }
        deck.subdeckOverloaded(deck.cards[0]);
        played = top.makeUsedPile(played);
        String u = ""; // u stores 'uno' for the user and is used as a reference for the computer to catch the user when he forgets to say 'uno'.
        String uno = ""; // uno stores 'uno' for the computer and is used as a reference for the computer to either print 'uno' or not based on the circumstance.
        int d = 0; // d is an integer variable which is related to the user's choice for drawing whenever he/she wishes to.  
        s = "Let us begin now.";
        slowPrint(s);
        System.out.println();
        s = "Loading...";
        for (int i = 0; i < s.length(); i++) {
            Thread.sleep(1000);
            System.out.print(s.charAt(i));
        }
        System.out.println ("\n\n");
        while (compHand.cards != null && userHand.cards != null) {
            n = Deck.sum(played,compHand,userHand);
            if (n == 108) {
                top = played.cards[0];
                deck = played.usedPileTransform();
                played = new Deck(0);
                played = top.makeUsedPile(played);
            }            
            if (c == 0) {
                System.out.println();
                s = "It is thy turn now. ";
                slowPrint(s);
                System.out.println();
                s = "Number of cards the computer has : " + compHand.cards.length;
                slowPrint(s);
                System.out.println();
                s = "These are your cards : ";
                slowPrint(s);
                System.out.println();
                userHand.sortdeck();
                userHand.printDeck();
                System.out.println();              
                s = "This is the top card : ";
                slowPrint(s);
                System.out.println();
                top.printCard();
                System.out.println();
                s = "Please give input as a card given in the list of cards above if you want to play or 1 if you want to draw a card !!! \n\nYou may also pass other commands as per the rules of the game. " ;
                slowPrint(s);
                System.out.println();
                s = br.readLine();
                System.out.println();
                if (s.equals("1")) {
                    d = 1;
                }                
                else {
                    s = s.toLowerCase();
                }                
                if (d == 1){
                    d = 0;
                    userHand.drawCards(deck,1);
                    played = deck.cards[0].makeUsedPile(played);
                    deck.subdeckOverloaded (deck.cards[0]);           
                    s = "These are your cards: ";
                    slowPrint(s);
                    System.out.println();
                    userHand.printDeck();
                    System.out.println();
                    s = "This is the top card : ";
                    slowPrint(s);
                    System.out.println();
                    top.printCard();
                    System.out.println();
                    s = "Please give input as a card given in the list of cards above if you can play or 1 if you want to pass (anything else could lead to picking up of a card if you can't play one) !!! \n\nYou may also pass other commands as per the rules of the game.";
                    slowPrint(s);
                    System.out.println();
                    s = br.readLine();
                    System.out.println();
                    if (s.equals("1")) {
                        c = 1;
                        continue;
                    }                    
                    else {
                        s = s.toLowerCase();
                    }                    
                }                                                                                         
                if (s.equals("caught") && compHand.cards.length == 1 && uno.equals ("")) {
                    s = "Good show !!! The computer will receive seven cards as a penalty. It is your turn again. ";
                    slowPrint(s);
                    System.out.println();
                    compHand.drawCards(deck,7);
                    for (int i = 0 ; i < 7; i++) {
                        played = deck.cards[0].makeUsedPile(played);
                        deck.subdeckOverloaded (deck.cards[0]);           
                    }
                    c = 0;
                    continue;
                }        
                if (s.equals ("caught") && compHand.cards.length == 1 && uno.equals ("uno")) {
                    s = "Your late , Monsieur/Madame. Please play a card now. It is your turn again. ";
                    slowPrint(s);
                    System.out.println();
                    c = 0;
                    continue;
                }                
                if (s.equals("caught") && compHand.cards.length != 1) {
                    s = "Please play a card and do not play around. ";
                    slowPrint(s);
                    System.out.println();
                    c = 0;
                    continue;
                }
                try{
                    if (s.length() >= 3) {
                        u = s.substring(s.length()-3);
                    }
                    
                    if (u.equals("uno")) {
                        s = s.substring(0,s.length()-4);
                    }
                }
                catch(Exception e){
                }
                card2 = new Uno (top.colour,top.rank);
                top = Player.userPlay(userHand, top, s);                
                if (top == null) {
                    s = "Please type in a valid command or card. It is your turn again. \n\nThis could also be happening if you have tagged a punctuation mark or a white space with your command.";
                    slowPrint(s);
                    System.out.println();
                    top = card2;
                    c = 0;
                    continue;
                }
                if (u.equals("uno") && userHand.cards.length==2) {
                    u = "Nice one. Your turn will continue. ";
                    r = Deck.randomInt(0,100) + 1;
                    slowPrint(u);
                    System.out.println();
                }                
                else if (u.compareTo("uno") != 0 && userHand.cards.length == 2 && r == 0) {
                    u = "caught";
                    slowPrint(u);
                    System.out.println();
                    u = "You will now receive seven cards as a penalty. ";
                    slowPrint(u);
                    System.out.println();
                    userHand.drawCards(deck,7);
                    for (int i = 0; i < 7; i++) {
                        played = deck.cards[0].makeUsedPile(played);
                        deck.subdeckOverloaded (deck.cards[0]);           
                    }
                }                
                else if (u.equals("uno") && userHand.cards.length != 2) {
                    u = "Please stop playing around. The 'uno' command must be passed only when you have two cards. If you have passed any card along with the 'uno' command it will be taken. ";
                    slowPrint(u);
                    System.out.println();
                }                
                else {
                }                
                if (top != null) {
                    played = top.makeUsedPile(played);
                    userHand.subdeckOverloaded(top);
                }                
                if (top.rank == 14) {
                    s = "You have played a wild draw 4. The computer will receive 4 cards. If this is not your last card, please play another card to choose your colour. ";
                    slowPrint(s);
                    System.out.println();
                    compHand.drawCards(deck,4);
                    for (int i = 0; i < 4; i++) {
                        played = deck.cards[0].makeUsedPile(played);
                        deck.subdeckOverloaded (deck.cards[0]);           
                    }
                    c = 0; 
                    continue;
                }                
                if (top.rank == 13) {
                    s = "You have played a wild. If this is not your last card, please play another card to choose your colour. ";
                    slowPrint(s);
                    System.out.println();
                    c = 0; 
                    continue;
                }                
                if (top.rank == 12) {
                    s = "You have played a draw 2. The computer will receive 2 cards. If this is not your last card it is your turn again. ";
                    slowPrint(s);
                    System.out.println();
                    compHand.drawCards(deck,2);
                    for (int i = 0; i < 2; i++) {
                        played = deck.cards[0].makeUsedPile(played);
                        deck.subdeckOverloaded (deck.cards[0]);           
                    }
                    c = 0; 
                    continue;
                }                
                if (top.rank == 11) {
                    s = "You have played a reverse. If this is not your last card it is your turn again. ";
                    slowPrint(s);
                    System.out.println();
                    c = 0; 
                    continue;
                }
                if (top.rank == 10) {
                    s = "You have played a skip. If this is not your last card it is your turn again. ";
                    slowPrint(s);
                    System.out.println();
                    c = 0; 
                    continue;
                }                
                else {
                    c = 1; 
                    continue;
                }
            }
            if (c == 1) {
                compHand.sortdeck();
                uno = "";
                if (compHand.cards.length == 2) {
                        if (dL.equals("medium")) {
                            uno = sayUno(50);
                        }
                        if (dL.equals("hard")) {
                            uno = sayUno(75);
                        }
                        else {
                            uno = sayUno(25);
                        }
                }                
                card2 = new Uno (top.colour,top.rank);
                top = ArtificialIntelligence.compPlay(deck,compHand,top,userHand);
                if (top != null) {
                    played = top.makeUsedPile(played);
                    compHand.subdeckOverloaded (top);
                    if (top.rank == 14) {
                        s = "Computer : The computer has played a Wild Draw 4. You will receive four cards at the end of this turn.";
                        slowPrint(s);
                        System.out.println();
                        userHand.drawCards(deck,4);
                        for (int i = 0; i < 4; i++) {
                            played = deck.cards[0].makeUsedPile(played);
                            deck.subdeckOverloaded (deck.cards[0]);                            
                        }
                        try{                                                
                            if (uno.compareTo("uno") != 0 && compHand.cards.length == 1) {
                                s = "Computer : Uno !!! Oh yeah!!!";
                                slowPrint(s);
                                System.out.println();
                            }
                        }                        
                        catch (Exception e) {
                        }
                        c = 1;
                    }                    
                    else if (top.rank == 13) {
                        s = "Computer : The computer has played a Wild.";
                        slowPrint(s);
                        System.out.println();
                        try{                                                
                            if (uno.compareTo("uno") != 0 && compHand.cards.length == 1) {
                                s = "Computer : Uno !!! Oh yeah!!!";
                                slowPrint(s);
                                System.out.println();
                            }
                        }                        
                        catch (Exception e) {
                        }  
                        c = 1;
                    }
                   
                    else if (top.rank == 12) {
                        s = "Computer : The computer has played a Draw 2 of " + colour (top.colour) + ". You will receive two cards at the end of this turn.";
                        slowPrint(s);
                        System.out.println();
                        userHand.drawCards(deck,2);
                        for (int i = 0 ; i < 2 ; i++) {
                            played = deck.cards[0].makeUsedPile(played);
                            deck.subdeckOverloaded (deck.cards[0]);           
                        }                        
                        try{                                                
                            if (uno.compareTo("uno") != 0 && compHand.cards.length == 1) {
                                s = "Computer : Uno !!! Oh yeah!!!";
                                slowPrint(s);
                                System.out.println();
                            }
                        }                        
                        catch (Exception e) {
                        } 
                        c = 1;
                    }
                   
                    else if (top.rank == 11) {
                        s = "Computer : The computer has played a reverse of " + colour (top.colour) + ".";
                        slowPrint(s);
                        System.out.println();                       
                        try{                                                
                            if (uno.compareTo("uno") != 0 && compHand.cards.length == 1) {
                                s = "Computer : Uno !!! Oh yeah!!!";
                                slowPrint(s);
                                System.out.println();
                            }
                        }                        
                        catch (Exception e) {
                        }
                        c = 1;                     
                    }                   
                    else if (top.rank == 10) {
                        s = "Computer : The computer has played a skip of " + colour (top.colour) + ".";
                        slowPrint(s);
                        System.out.println();                        
                        try{                                                
                            if (uno.compareTo("uno") != 0 && compHand.cards.length == 1) {
                                s = "Computer : Uno !!! Oh yeah!!!";
                                slowPrint(s);
                                System.out.println();
                            }
                        }                        
                        catch (Exception e) {
                        }
                        c = 1;
                    }                   
                    else {
                        s = "Computer : The computer has played " + top.rank + " of " + colour (top.colour) + ".";
                        slowPrint(s);
                        System.out.println();
                        c = 0;                        
                    }
                    if (uno.equals("uno") && compHand.cards.length == 1) {
                        s = "Computer : Uno !!! Oh yeah!!!";
                        slowPrint(s);
                        System.out.println();
                    }
                    continue;
                }                    
                else {
                    s = "Computer : The computer passes its turn as it is unable to play a card. ";
                    slowPrint(s);
                    System.out.println();
                    top = card2;                        
                    c = 0;
                    continue;
                }
            }
        }        
        if (compHand.cards == null) {
            s = "The computer has won!!!!! Better luck next time !!!!! ";
        }        
        if (userHand.cards == null) {
            s = "You have won !!!!! Did you know what you just did ????? You have just reserved a seat in the HALL OF FAME !!!!! Too Good !!!!! ";
        }        
        slowPrint(s);
        System.out.println();
        s = "The game has come to an end !!!!!  Hope you enjoyed it !!!!! You can now click on the exit button of the window to remove the terminal window.";
        slowPrint(s);
        System.out.println();
        System.exit(0);    
    }
    
    /**
     * The main method is the site of all execution.
     */
    public static void main (String []args) throws Exception {
        intro();
        System.out.println();
        printDivide();
        System.out.println();
        startPlay();
    }
}