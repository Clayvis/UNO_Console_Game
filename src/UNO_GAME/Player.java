package UNO_GAME;

import java.util.*;

public class Player {
        private String playerName;
        private int numOfCards;
        List playerHand = new ArrayList();
        private Scanner in = new Scanner(System.in);


        //Constructor

    public Player(int numOfCards){
     getPlayerName();
    }

    //Business Methods

    /*
    public void addCardToHand(int numOfCards){
        for (int index = 0; index < numOfCards; index++ )
        //method grabbing cards from Deck class
        //numOfCards++;
        playerHand.set(numOfCards - 1, x);
    }

     */
    /*
    public Card removeCardFromHand(int index){
        Card toReturn = playerDeck[index];
        for(;index<numberOfCards;index++){
            if(index==numberOfCards-1){
                playerDeck[index] = null;
            }
            else{
                playerDeck[index] = playerDeck[index+1];
            }
        }
        numberOfCards--;
        return toReturn;
    }
     */

    //Display Player Deck to player - call at every turn
    public void displayPlayerHand(){

        System.out.println(playerName+"'s cards:");
        for(int i=0;i<numOfCards;i++){
            System.out.println((i+1)+". "+ playerHand.get(i));

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
