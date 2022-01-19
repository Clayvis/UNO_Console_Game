public class Card
{
    public static final int RED   = 4;
    public static final int BLUE   = 3;
    public static final int GREEN    = 2;
    public static final int YELLOW = 1;

    private static final String[] Suit = { "*", "Yellow", "Green", "Blue", "Red"};
    private static final String[] Rank = { "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"};

    private byte cardSuit;
    private byte cardRank;

    public Card( int suit, int rank )
    {
        cardRank = (byte) rank;
        cardSuit = (byte) suit;
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
}
