import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class DealerTest {
    
    @Test
    void constructorTest() {
        Dealer deal = new Dealer();

        Deck d = deal.theDeck;

        assertEquals(52, d.size());
        
        int numH = 0, numS = 0, numC = 0, numD = 0;

        for (int i = 0; i < 52; i++) {
            switch(d.get(i).suit) {
                case 'H': 
                    numH++;
                    break;
                case 'S':
                    numS++;
                    break;
                case 'C':
                    numC++;
                    break;
                case 'D':
                    numD++;
                    break;
                default:
                    fail("Somehow received invalid char of " + d.get(i).suit);
            }
        }
        assertEquals(13, numH, "Not 13 hearts, instead there are " + numH);
        assertEquals(13, numC, "Not 13 clubs, instead there are " + numC);   
        assertEquals(13, numD, "Not 13 diamonds, instead there are " + numD);           
        assertEquals(13, numS, "Not 13 spades, instead there are " + numS);  

        ArrayList<Card> comp = new ArrayList<Card>();

        for (int i = 0; i < 3; i++) {
            comp.add(deal.theDeck.get(i));
        }

        deal.dealersHand = deal.dealHand();

        for (int i = 0; i < 3; i++) {
            assertEquals(comp.get(i), deal.dealersHand.get(i), "Cards are not equal, COMP = " + comp.get(i).suit + " "
                                                                + comp.get(i).value + " DEAL = " + deal.dealersHand.get(i).suit + " " +
                                                                deal.dealersHand.get(i).value);
        }

    }

    @Test
    void dealHandTest() {
        Dealer dealer = new Dealer();

        ArrayList<Card> comp = new ArrayList<Card>();

        for (int i = 0; i < 6; i++) {
            comp.add(dealer.theDeck.get(i));
        }

        ArrayList<Card> hand1 = dealer.dealHand();
        ArrayList<Card> hand2 = dealer.dealHand();

        for (int i = 0; i < 3; i++) {
            assertEquals(comp.get(i).value, hand1.get(i).value, "values arent the same");
            assertEquals(comp.get(i).suit, hand1.get(i).suit, "suits arent the same");
        }

        for (int i = 3; i < 6; i++) {
            assertEquals(comp.get(i).value, hand2.get(i%3).value, "values arent the same");
            assertEquals(comp.get(i).suit, hand2.get(i%3).suit, "suits arent the same");
        }

    }

    @Test
    void dealHandResize() {
        Dealer d = new Dealer();
        int expectedSize = 52;
        for (int i = 0; i < 6; i++) {
            assertEquals(expectedSize, d.theDeck.size(), "Sizes are not equal. " + expectedSize + " vs. " + d.theDeck.size());
        }
        
        //should reset then remove 3 cards
        d.dealHand();
        assertEquals(49, d.theDeck.size());
    }

    @Test
    void dealHandCreatesNewDeck() {
        Dealer d = new Dealer();

        Deck copy = (Deck)d.theDeck.clone();

        for (int i = 0; i < 7; i++) {
            d.dealHand();
        }

        assertNotEquals(copy.size(), d.theDeck.size());
        
        for (int i = 0; i < d.theDeck.size(); i++) {
            assertNotEquals(d.theDeck.get(i), copy.get(i));
        }
    }

    @Test 
    void dealHandReturnsGoodHandsSize() {
        Dealer d = new Dealer();

        for (int i = 0; i < 10000; i++) {
            assertEquals(3, d.dealHand().size());
        }
    }

    @Test
    void dealerConstructorIsActuallyAConstructorIGuess() {
        //im tired and running out of test ideas
        Dealer d = new Dealer();

        assertNotNull(d);
        assertNotNull(d.dealersHand);
    }
}
