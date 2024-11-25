import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class DeckTest {

    @Test
    void deckIsBuiltCorrectly() {
        Deck d = new Deck();

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
    }

    @Test
    void newDeckCreatesNewDeck() {
        Deck d = new Deck();
        Deck dCopy = (Deck)d.clone();

        //System.out.println("BEFORE NEWDECK: ");
        //for (int i = 0; i < 52; i++) {
        //    System.out.println("d = " + d.get(i).suit + " " + d.get(i).value + " dCopy = " + dCopy.get(i).suit + " " + dCopy.get(i).value);
        //}

        d.newDeck();

        //System.out.println("AFTER NEWDECK: ");
        //for (int i = 0; i < 52; i++) {
        //    System.out.println("d = " + d.get(i).suit + " " + d.get(i).value + " dCopy = " + dCopy.get(i).suit + " " + dCopy.get(i).value);
        //}

        assertNotEquals(d, dCopy);
    }

    @Test
    void moreThoroughNewDeck() {
        //this one is gonna go through the cards and make sure the card objects dont exist in the new deck
        Deck d = new Deck();
        Deck dCopy = (Deck)d.clone();

        d.newDeck();

        for (int i = 0; i < 52; i++) {
            assertFalse(dCopy.contains(d.get(i)), "Card objects were not deleted, just reshuffled");
        }
    }

    @Test
    void noDupes() {
        Deck d = new Deck();

        int num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0, num7 = 0, num8 = 0, num9 = 0, num10 = 0, num11 = 0, num12 = 0, num13=0, num14=0
            , numC = 0, numD = 0, numS = 0, numH = 0;

        for (int i = 0; i < d.size(); i++) {
            switch (d.get(i).value) {
                case 2:
                    num2++;
                    break;
                case 3:
                    num3++;
                    break;
                case 4:
                    num4++;
                    break;
                case 5:
                    num5++;
                    break;
                case 6:
                    num6++;
                    break;
                case 7:
                    num7++;
                    break;
                case 8:
                    num8++;
                    break;
                case 9:
                    num9++;
                    break;
                case 10:
                    num10++;
                    break;                      
                case 11:
                    num11++;
                    break;
                case 12:
                    num12++;
                    break;
                case 13:
                    num13++;
                    break; 
                case 14:
                    num14++;
                    break;
                default:
                    fail();
            }
            switch (d.get(i).suit) {
                case 'H':
                    numH++;
                    break;
                case 'D':
                    numD++;
                    break;
                case 'S':
                    numS++;
                    break;
                case 'C':
                    numC++;
                    break;
                default:
                    fail();
            }
        }

        assertEquals(4, num2);
        assertEquals(4, num3);
        assertEquals(4, num4);
        assertEquals(4, num5);
        assertEquals(4, num6);
        assertEquals(4, num7);
        assertEquals(4, num8);
        assertEquals(4, num9);
        assertEquals(4, num10);
        assertEquals(4, num11);
        assertEquals(4, num12);
        assertEquals(4, num13);
        assertEquals(4, num14);
        assertEquals(13, numH);
        assertEquals(13, numC);
        assertEquals(13, numD);
        assertEquals(13, numS);

    }
}
