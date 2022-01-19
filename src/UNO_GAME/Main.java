package UNO_GAME;
// Test
public class Main {
    public static void main(String[] args) {
        Player one = new Player(7);
        Player two = new Player(7);
        System.out.println(one);
        one.displayPlayerHand();
        System.out.println(two);
        two.displayPlayerHand();
        one.addCardToHand(7);
        System.out.println();
       // System.out.println(one.playerHand);

    }
}
