package UNO_GAME;
/* -----------------------------------------------------
   Deck: a deck of cards
   ----------------------------------------------------- */

import java.util.*;

public class DeckOfCards
{

    List<Card> deckOfCards = new ArrayList<>(52); // Contains all 52 cards

    public DeckOfCards( )
    {
        for (int suit = Card.YELLOW; suit <= Card.RED; suit++ )
            for ( int rank = 0; rank <= 14; rank++ )
                deckOfCards.add(new Card(suit, rank));


    }

    /* -------------------------------------------
       deal(): deal deckOfCards[currentCard] out
       ------------------------------------------- */

    public Card deal()
    {

        while (deckOfCards.size() > 0){
            Random cardGenerator = new Random();
            Card[] test = deckOfCards.toArray(new Card[0]);
            Card aCard = test[cardGenerator.nextInt(test.length)];
            deckOfCards.remove(aCard);
            return aCard;
        }

        System.out.println("Out of Cards....");
        return null;
    }

    @Override
    public String toString() {
        return "DeckOfCards{" +
                "deckOfCards=" + deckOfCards +
                '}';
    }

    public static void main(String[] args) {
        DeckOfCards testDeck = new DeckOfCards();
        System.out.println(testDeck);
        for(int i = 0; i < 54; i++){
            testDeck.deal();
        }
        System.out.println(testDeck);
    }


}
