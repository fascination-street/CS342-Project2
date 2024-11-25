import java.util.ArrayList;

public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealersHand;

    Dealer() {
        theDeck = new Deck();
        dealersHand = new ArrayList<>(3);
    }

    //before each game starts, the dealer class must check to see if there are more than 34 cards left in the deck.
    //if not, the deck most be reshuffled with a new set of 52 cards in random order.
    public ArrayList<Card> dealHand() {
        if (theDeck.size() < 34) {
            theDeck = new Deck();
        }

        ArrayList<Card> hand = new ArrayList<>(3);
        
        for (int i = 0; i < 3; i++) {
            hand.add(theDeck.remove(0));
        }

        return hand;
    }
}
