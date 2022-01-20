//import UNO_GAME.Card;
//
//import java.util.Scanner;
//
//public class GameLogic {
//
//    private Scanner in = new Scanner(System.in);
//    private Player player1;
//    private Player player2;
//    private Deck deck;
//    private Card currentCard;
//    private Card prevCard;
//    private boolean p1Turn;
//    private boolean p2Turn;
//    private boolean p1Uno;
//    private boolean p2Uno;
//    private boolean validMove;
//    private boolean victory;
//
//    /**
//     * Constructor - initializes instance variables: 2 Players, 1 Deck of cards, 1 card to act as current in-play card, and several boolean variables to determine turns,
//     *  if a player has "UNO", if a move is valid, and if victory has been achieved
//     */
//    public GameLogic() {
//
//        player1 = new Player();
//        player2 = new Player();
//        deck = new Deck();
//        currentCard = null;
//        prevCard = null;
//        validMove = false;
//        victory = false;
//        p1Turn = false;
//        p2Turn = false;
//        p1Uno = false;
//        p2Uno = false;
//    }
//
//    /**
//     * Asks players to enter their names, deals their first hand (7 cards) and starts the game (Player 1 has first move)
//     * All methods in this class are private except for startGame() and endGame() since there is no reason for anything outside of the class to call these private methods
//     */
//    public void startGame() {
//        System.out.println("Please enter name of player 1: ");
//        String name1 = in.nextLine();
//        player1.setName(name1);
//        System.out.println("Please enter name of player 2: ");
//        String name2 = in.nextLine();
//        player2.setName(name2);
//
//        System.out.println("Starting Game. Good luck, and have fun! ");
//        player1.drawCards(7);   //<---this method should actually be in the Player or Deck class
//        player2.drawCards(7);
//        p1Turn = true;
//        deck.shuffle();
//        prevCard = deck.getCard(); //<---need a way to flip the first card from the deck to start the game
//        startTurn();
//
//    }
//
//    private void startTurn() throws IllegalArgumentException {
//        //determines and starts turn for either player
//        while (p1Turn == true) {
//            if(player1.getNumOfCards() > 1) {
//                p1Uno = false;
//            }
//            System.out.println("***********************************************");
//            System.out.println("It is " + player1.getName() + " turn. These are your options (press A or B): ");
//            System.out.println("A.) Play a card from your hand ");
//            System.out.println("B.) Draw a card from the pile ");
//            String choice = in.nextLine();
//            if (choice.equalsIgnoreCase("A")){
//                player1.displayPlayerHand();  //should list player's cards and give him options to select a card to play
//                currentCard = player1.playCard();  //sets currentCard to what the playCard() returns (method should return a UNO_GAME.Card object). Also once card played, card will be removed from player's hand
//                isValidMove();
//                //endTurn();
//            }
//            else if (choice.equalsIgnoreCase("B")){
//                player1.drawCards(1);
//                isValidMove();
//                //endTurn();
//            }
//            else {
//                throw new IllegalArgumentException("Invalid choice. Try again.");
//            }
//        }
//        while (p2Turn == true) {
//            if(player2.getNumOfCards() > 1) {
//                p2Uno = false;
//            }
//            System.out.println("***********************************************");
//            System.out.println("It is " + player2.getName() + " turn. These are your options (press A or B): ");
//            System.out.println("A.) Play a card from your hand ");
//            System.out.println("B.) Draw a card from the pile ");
//            String choice = in.nextLine();
//            if (choice.equalsIgnoreCase("A")){
//                player2.displayPlayerHand();  //should list player's cards and give him options to select a card to play
//                currentCard = player2.playCard();  //sets currentCard to what the playCard() returns (method should return a UNO_GAME.Card object). Also once card played, card will be removed from player's hand
//                isValidMove();
//
//            }
//            else if (choice.equalsIgnoreCase("B")){
//                player2.drawCards(1);
//                isValidMove();
//
//            }
//            else {
//                throw new IllegalArgumentException("Invalid choice. Try again.");
//            }
//        }
//
//    }
//
//    /**
//     * Need to move this method to the Player or Deck class
//     */
//    private int drawCards(int num){
//        //called if player needs to draw a card (amount determined whether due to starting game(7)/not playing a card(1)/receiving +2/receiving +4)
//        //will need to add cards to Player's Hand
//        return 0;
//    }
//
//    /**
//     * checks if valid move (card played needs to be of same color/suit or both)
//     */
//    private void isValidMove(){
//
//        if ((currentCard.suit() == prevCard.suit()) || (currentCard.rank() == prevCard.rank())) {
//            prevCard = currentCard; //sets previous card to current card played if move is valid
//            checkSpecial();
//            checkVictory();
//            endTurn();
//        }
//        else {
//            System.out.println("Invalid move. Please try again. ");
//            startTurn();
//        }
//
//    }
//
//    /**
//     * ends turn of current player and switches turn to next player
//     */
//    private void endTurn(){
//
//        if (p1Turn == true) {
//            p1Turn = false;
//            p2Turn = true;
//        }
//        else if (p2Turn == true) {
//            p1Turn = true;
//            p2Turn = false;
//        }
//        startTurn();
//    }
//
//    /**
//     * This method checks if a Draw +2, WILD Draw +4, WILD, Reverse, or Skip card is played
//     */
//    private void checkSpecial() {
//        while(p1Turn == true) {
//            if (currentCard.rank() == Draw + 2) {
//                player2.drawCards(2);
//            } else if (currentCard.rank() == Wild Draw + 4) {
//                player2.drawCards(4);
//            } else if (currentCard.rank() == Reverse || currentCard.rank() == Skip) {
//                checkVictory();
//                startTurn();
//            } else if (currentCard.rank() == Wild) {
//                //checkVictory();
//                break;
//            }
//        }
//
//        while (p2Turn == true) {
//            if (currentCard.rank() == Draw + 2) {
//                player2.drawCards(2);
//            } else if (currentCard.rank() == Wild Draw + 4) {
//                player2.drawCards(4);
//            } else if (currentCard.rank() == Reverse || currentCard.rank() == Skip) {
//                checkVictory();
//                startTurn();
//            } else if (currentCard.rank() == Wild) {
//                //checkVictory();
//                break;
//            }
//        }
//
//    }
//
//    /**
//     * Checks whether player still has Cards in Hand
//     * Also checks if player has 1 card left, then gives them the option to say UNO
//     */
//    private void checkVictory(){
//
//        if (player1.getNumOfCards() == 0 || player2.getNumOfCards() == 0) {
//            System.out.println("Congratulations, you win! This is the end of the game. ");
//            endGame();
//        }
//
//        while(p1Turn == true && p1Uno == false) {
//            if (player1.getNumOfCards() == 1) {
//                System.out.println("Type the magic word. ");
//                String uno = in.nextLine();
//                if (uno.equalsIgnoreCase("UNO")) {
//                    System.out.println("Nice! Almost to victory");
//                    p1Uno = true;
//                } else {
//                    System.out.println("Sorry, you didn't say UNO. Draw 2 cards. ");
//                    player1.drawCards(2);
//                }
//            }
//        }
//        while(p2Turn == true && p2Uno == false) {
//            if (player2.getNumOfCards() == 1) {
//                System.out.println("Type the magic word. ");
//                String uno = in.nextLine();
//                if (uno.equalsIgnoreCase("UNO")) {
//                    System.out.println("Nice! Almost to victory");
//                    p2Uno = true;
//                } else {
//                    System.out.println("Sorry, you didn't say UNO. Draw 2 cards. ");
//                    player2.drawCards(2);
//                }
//            }
//        }
//
//        //return victory;
//    }
//
//
//    public void endGame(){
//        //returns back to Main which will end the program
//        break;
//    }
//
//}
