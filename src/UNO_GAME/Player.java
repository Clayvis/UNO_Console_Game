package UNO_GAME;

import java.util.*;

public class Player {

    //Fields
    private String playerName;
    private int numOfCards;
    private Scanner in = new Scanner(System.in);
    private DeckOfCards playersDeck = new DeckOfCards();
    private List playerHand = new ArrayList();

    //Constructor

    public Player(int numOfCards){
     getPlayerName();
     addCardToHand(numOfCards);
    }

    //Business Methods

    public void addCardToHand(int numOfCards){
        //Shuffles the deck then adds cards to Player's
        // hand according to the num of Cards passed in.
        playersDeck.shuffle(52);
        for (int index = 0; index < numOfCards; index++ )
            playerHand.add(playersDeck.deal());

    }

    public void removeCardFromHand(int index){
        if (playerHand.size() < 1){
            playerHand.remove(index);
        }
        else if (playerHand.size() == 1){
            playerHand.remove(index);
            //go to method that declares winner
        }

    };

    //Display Player Deck to player - call at every turn

    public void displayPlayerHand(){

        System.out.println(playerName+"'s cards:");
        for(Object aCard : playerHand ){
            System.out.println(aCard);

        }
    }

 // Accessors and Mutators
    public void getPlayerName() {
        System.out.print("Enter name of player? ");
        playerName = in.next();
        System.out.println();
        setPlayerName(playerName);

    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void getNumOfCards() {
        //a method of getting random cards from deck
        setNumOfCards(numOfCards);

    }

    public void setNumOfCards(int numOfCards) {
        this.numOfCards = numOfCards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", numOfCards=" + numOfCards +
                '}';
    }
}
