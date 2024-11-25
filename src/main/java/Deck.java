import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    
    //A deck of cards has 52 cards in total
    //13 Clubs 'C'
    //13 Diamonds 'D'
    //13 Spades 'S'
    //13 Hearts 'H'
    Deck() {
        this.populateDeck();
    }

    void newDeck() {
        this.clear();
        this.populateDeck();
    }

    //private function to make code easier to read
    private void populateDeck() {
        for (int i = 2; i < 15; i++) {this.add(new Card('C', i));}
        for (int i = 2; i < 15; i++) {this.add(new Card('D', i));}
        for (int i = 2; i < 15; i++) {this.add(new Card('S', i));}
        for (int i = 2; i < 15; i++) {this.add(new Card('H', i));}

        Collections.shuffle(this);
    }
}
