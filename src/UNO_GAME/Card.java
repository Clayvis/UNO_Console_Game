package UNO_GAME;

//Do you see this?
public class Card
{
    public static final int WILD   = 5;
    public static final int RED   = 4;
    public static final int BLUE   = 3;
    public static final int GREEN    = 2;
    public static final int YELLOW = 1;

    private static final String[] Suit = { "*", "Yellow", "Green", "Blue", "Red", "Wild"};
    private static final String[] Rank = { "0", "1", "2", "3", "4","5", "6", "7", "8", "9", "DrawTwo","Reverse", "Skip"};


    private int cardSuit;
    private int cardRank;

    public Card( int suit, int rank )
    {
        cardRank = rank;
        cardSuit = suit;
    }

    public int suit()
    {
        return ( cardSuit );         // This is a shorthand for:
        //   this.cardSuit
    }

    public String suitStr()
    {
        return( Suit[ cardSuit ] );  // This is a shorthand for:
        //   this.Suit[ this.cardSuit ]
    }

    public int rank()
    {
        return ( cardRank );
    }

    public String rankStr()
    {
        return ( Rank[ cardRank ] );
    }


    public String toString()
    {
        return (  Suit[ cardSuit ] + Rank[ cardRank ]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (cardSuit != card.cardSuit) return false;
        return cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        int result = cardSuit;
        result = 31 * result + cardRank;
        return result;
    }
}
