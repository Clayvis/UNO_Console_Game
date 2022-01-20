package UNO_GAME;

import UNO_GAME.DeckOfCards;
import UNO_GAME.Player;

import java.util.Scanner;

public class GameLogic {

    private Scanner in = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private DeckOfCards deck;
    private UNO_GAME.Card currentCard;
    private UNO_GAME.Card prevCard;
    private boolean p1Turn;
    private boolean p2Turn;
    private boolean p1Uno;
    private boolean p2Uno;
    private boolean validMove;
    private boolean victory;

    /**
     * Constructor - initializes instance variables: 2 Players, 1 Deck of cards, 1 card to act as current in-play card, 1 card to act as previous card, and several boolean variables to determine turns,
     *  if a player has "UNO", if a move is valid, and if victory has been achieved
     */
    public GameLogic() {

        player1 = new Player(2);
        player2 = new Player(2);
        deck = new DeckOfCards();
        currentCard = null;
        prevCard = null;
        p1Turn = false;
        p2Turn = false;
        p1Uno = false;
        p2Uno = false;

    }

    /**
     * Asks players to enter their names, deals their first hand (7 cards) and starts the game (Player 1 has first move)
     * All methods in this class are private except for startGame() and endGame() since there is no reason for anything outside the class to call these private methods
     */
    public void startGame() {
        System.out.println("Please enter name of player 1: ");
        String name1 = in.nextLine();
        player1.setPlayerName(name1);
        System.out.println("Please enter name of player 2: ");
        String name2 = in.nextLine();
        player2.setPlayerName(name2);

        System.out.println("Starting Game. Good luck, and have fun! ");
        p1Turn = true;
        deck.shuffle(52);
        prevCard = deck.deal(); //<---need a way to flip the first card from the deck to start the game
        System.out.println("Now flipping over the first card...it is " + prevCard);
        startTurn();

    }

    /**
     * Starts turn for each player by giving them the option to play a card from their hand, or draw a card from the deck
     * From there, it will validate this move by calling the isValidMove() method
     * @throws
     */
    private void startTurn()  {
        //determines and starts turn for either player
        while (p1Turn == true) {
            if (player1.playerHand.size() > 1) {
                p1Uno = false;  //resets Uno to false if player has more than 1 card
            }
            System.out.println("***********************************************");
            player1.displayPlayerHand();
            System.out.println("It is " + player1.getPlayerName() + "'s turn. These are your options (press A or B): ");
            System.out.println("A.) Play a card from your hand ");
            System.out.println("B.) Draw a card from the deck ");
            System.out.println("Active card is = "+ prevCard);
            String choice = in.nextLine();

            if (choice.equalsIgnoreCase("A")) {
                try {
                    player1.displayPlayerHand();
                    String choice2 = in.nextLine();
                    currentCard = player1.playCard(Integer.parseInt(choice2));  //sets currentCard to what the playCard() returns (method should return a UNO_GAME.Card object). Also once card played, card will be removed from player's hand
                    isValidMove();
                    player1.removeCardFromHand(Integer.parseInt(choice2));
                    checkVictory();
                    endTurn();
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input. Please enter the correct number for the card you wish to play.");
                }


            } else if (choice.equalsIgnoreCase("B")) {
                player1.addCardToHand(1);
                endTurn();

            } else {
                System.out.println("Invalid choice. Try again.");
                startTurn();
            }

        }
        while (p2Turn == true) {
            if (player2.playerHand.size() > 1) {
                p2Uno = false;   //resets Uno to false if player has more than 1 card
            }
            System.out.println("***********************************************");
            player2.displayPlayerHand();
            System.out.println("It is " + player2.getPlayerName() + "'s turn. These are your options (press A or B): ");
            System.out.println("A.) Play a card from your hand ");
            System.out.println("B.) Draw a card from the pile ");
            System.out.println("Active card is = "+ prevCard);
            String choice = in.nextLine();

            if (choice.equalsIgnoreCase("A")) {
                try {
                    player2.displayPlayerHand();//should list player's cards and give him options to select a card to play
                    String choice2 = in.nextLine();
                    currentCard = player2.playCard(Integer.parseInt(choice2));  //sets currentCard to what the playCard() returns (method should return a UNO_GAME.Card object). Also once card played, card will be removed from player's hand
                    isValidMove();
                    player2.removeCardFromHand(Integer.parseInt(choice2));
                    checkVictory();
                    endTurn();
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input. Please enter the correct number for the card you wish to play.");
                }

            } else if (choice.equalsIgnoreCase("B")) {
                player2.addCardToHand(1);
                endTurn();

            } else {
                System.out.println("Invalid choice. Try again.");
                startTurn();
            }

        }

    }

    /**
     * checks if valid move (card played needs to be of same color/suit or both)
     */
    private void isValidMove(){

        int currentCardSuit = currentCard.suit();
        int prevCardSuit = prevCard.suit();
        int currentCardRank = currentCard.rank();
        int prevCardRank = prevCard.rank();

        if (currentCardSuit == prevCardSuit){
            prevCard = currentCard; //sets previous card to current card played if move is valid
            checkSpecial();
            checkVictory();
//            endTurn();
        }
        else if(currentCardRank == prevCardRank){
            prevCard = currentCard; //sets previous card to current card played if move is valid
            checkSpecial();
            checkVictory();
//            endTurn();
        }
        else {
            System.out.println("Invalid move. Please try again. ");
            startTurn();
        }

    }

    /**
     * ends turn of current player and switches turn to next player
     */
    private void endTurn(){

        if (p1Turn == true) {
            p1Turn = false;
            p2Turn = true;
        }
        else if (p2Turn == true) {
            p2Turn = false;
            p1Turn = true;
        }
        startTurn();
    }

    /**
     * This method checks if a Draw +2, WILD Draw +4, WILD, Reverse, or Skip card is played
     */
   private void checkSpecial() {
        /*while(p1Turn == true) {
            if (currentCard.rank() == Draw + 2) {
                player2.addCardToHand(2);
            } else if (currentCard.rank() == Wild Draw + 4) {
                player2.addCardToHand(4);
            } else if (currentCard.rank() == Reverse || currentCard.rank() == Skip) {
                checkVictory();
                startTurn();
            } else if (currentCard.rank() == Wild) {
                //checkVictory();
                break;
            }
        }

        while (p2Turn == true) {
            if (currentCard.rank() == Draw + 2) {
                player2.addCardToHand(2);
            } else if (currentCard.rank() == Wild Draw + 4) {
                player2.addCardToHand(4);
            } else if (currentCard.rank() == Reverse || currentCard.rank() == Skip) {
                checkVictory();
                startTurn();
            } else if (currentCard.rank() == Wild) {
                //checkVictory();
                break;
           }
       }*/

    }

    /**
     * Checks whether player still has Cards in Hand
     * Also checks if player has 1 card left, then gives them the option to say UNO
     */
    private void checkVictory(){

        if (player1.playerHand.size() == 0 || player2.playerHand.size() == 0) {
            System.out.println("Congratulations, you win! This is the end of the game. ");
            endGame();
        }

        while(p1Uno == false) {
            if (player1.playerHand.size() == 1) {
                System.out.println("Type the magic word. ");
                String uno = in.nextLine();
                if (uno.equalsIgnoreCase("UNO")) {
                    System.out.println("Nice! Almost to victory");
                    p1Uno = true;
                } else {
                    System.out.println("Sorry, you didn't say UNO. Draw 2 cards. ");
                    player1.addCardToHand(2);
                }
            }
            else{
                break;
            }
        }
        while(p2Uno == false) {
            if (player2.playerHand.size() == 1) {
                System.out.println("Type the magic word. ");
                String uno = in.nextLine();
                if (uno.equalsIgnoreCase("UNO")) {
                    System.out.println("Nice! Almost to victory");
                    p2Uno = true;
                } else {
                    System.out.println("Sorry, you didn't say UNO. Draw 2 cards. ");
                    player2.addCardToHand(2);
                }
            }
            else {
                break;
            }
        }

    }


    public void endGame(){
        //returns back to Main which will end the program
        System.exit(0);
    }
}
