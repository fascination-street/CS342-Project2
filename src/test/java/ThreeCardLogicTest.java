import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;



public class ThreeCardLogicTest {
    final ArrayList<Card> straightFlush = new ArrayList<Card>(Arrays.asList(
                    new Card('H', 10), 
                    new Card('H', 9), 
                    new Card('H', 11)));
    final ArrayList<Card> highCard = new ArrayList<Card>(Arrays.asList(
                    new Card('D', 2), 
                    new Card('S', 13), 
                    new Card('C', 8)));
    final ArrayList<Card> threeOfAKind = new ArrayList<Card>(Arrays.asList(
                    new Card('D', 10), 
                    new Card('H', 10), 
                    new Card('S', 10)));
    final ArrayList<Card> straight = new ArrayList<Card>(Arrays.asList(
                    new Card('S', 2), 
                    new Card('D', 3), 
                    new Card('C', 4)));
    final ArrayList<Card> flush = new ArrayList<Card>(Arrays.asList(
                    new Card('D', 14), 
                    new Card('D', 10), 
                    new Card('D', 5)));
    final ArrayList<Card> pair = new ArrayList<Card>(Arrays.asList(
                    new Card('S', 6), 
                    new Card('H', 6), 
                    new Card('D', 4)));

    final int TIE = 0;
    final int DEALER = 1;
    final int PLAYER = 2;

    //this is just where I test as I code
    @Test
    void playgroundTestsEvalHand() {
        System.out.println(ThreeCardLogic.evalHand(pair));

        //these work...
        assertEquals(0, ThreeCardLogic.evalHand(highCard), "highCard did not return 0");
        assertEquals(1, ThreeCardLogic.evalHand(straightFlush), "straightFlush did not return 1");
        assertEquals(2, ThreeCardLogic.evalHand(threeOfAKind), "threeOfAKind did not return 2");
        assertEquals(3, ThreeCardLogic.evalHand(straight), "straight did not return 3");
        assertEquals(4, ThreeCardLogic.evalHand(flush), "flush did not return 4");
        assertEquals(5, ThreeCardLogic.evalHand(pair), "pair did not return 5");
        
    }

    @Test
    void evalHandHighCard() {      
        assertEquals(0, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 14), 
            new Card('H', 2), 
            new Card('C', 4)))));
        
        assertEquals(0, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 2), 
            new Card('S', 5), 
            new Card('H', 4)))));

        assertEquals(0, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 11), 
            new Card('D', 6), 
            new Card('S', 9)))));
    }

    @Test
    void evalHandStraightFlush() {
        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 14), 
            new Card('C', 12), 
            new Card('C', 13)))), "test 1 fail");
        
        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 6), 
            new Card('H', 5), 
            new Card('H', 7)))), "test 2 fail");

        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 3), 
            new Card('C', 4), 
            new Card('C', 5)))), "test 3 fail");
            
        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 12), 
            new Card('S', 11), 
            new Card('S', 10)))), "test 4 fail");
            
        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 7), 
            new Card('S', 8), 
            new Card('S', 9)))), "test 5 fail");    

        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 7), 
            new Card('H', 8), 
            new Card('H', 6)))), "test 6 fail");  
        
        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 13), 
            new Card('D', 11), 
            new Card('D', 12)))), "test 7 fail");  
            
        assertEquals(1, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 4), 
            new Card('D', 6), 
            new Card('D', 5)))), "test 8 fail");
    }

    @Test
    void evalHandThreeKind() {
        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 5), 
            new Card('S', 5), 
            new Card('D', 5)))), "test 1 fail");
        
        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 2), 
            new Card('H', 2), 
            new Card('S', 2)))), "test 2 fail");

        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 14), 
            new Card('S', 14), 
            new Card('H', 14)))), "test 3 fail");
            
        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 12), 
            new Card('D', 12), 
            new Card('H', 12)))), "test 4 fail");
            
        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 8), 
            new Card('D', 8), 
            new Card('S', 8)))), "test 5 fail");    

        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 7), 
            new Card('S', 7), 
            new Card('D', 7)))), "test 6 fail");  
        
        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 6), 
            new Card('D', 6), 
            new Card('C', 6)))), "test 7 fail");  
            
        assertEquals(2, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 4), 
            new Card('D', 4), 
            new Card('C', 4)))), "test 8 fail");
    }

    @Test
    void evalHandStraight() {
        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 14), 
            new Card('C', 12), 
            new Card('S', 13)))), "test 1 fail");
        
        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 6), 
            new Card('D', 5), 
            new Card('C', 7)))), "test 2 fail");

        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 3), 
            new Card('H', 4), 
            new Card('C', 5)))), "test 3 fail");
            
        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 12), 
            new Card('S', 11), 
            new Card('C', 10)))), "test 4 fail");
            
        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 7), 
            new Card('S', 8), 
            new Card('C', 9)))), "test 5 fail");    

        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 7), 
            new Card('D', 8), 
            new Card('H', 6)))), "test 6 fail");  
        
        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 13), 
            new Card('C', 11), 
            new Card('C', 12)))), "test 7 fail");  
            
        assertEquals(3, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 4), 
            new Card('H', 6), 
            new Card('S', 5)))), "test 8 fail");
    }

    @Test
    void evalHandFlush() {
        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 14), 
            new Card('C', 11), 
            new Card('C', 13)))), "test 1 fail");
        
        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 2), 
            new Card('H', 5), 
            new Card('H', 4)))), "test 2 fail");

        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 9), 
            new Card('C', 4), 
            new Card('C', 11)))), "test 3 fail");
            
        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 3), 
            new Card('S', 11), 
            new Card('S', 6)))), "test 4 fail");
            
        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 3), 
            new Card('S', 8), 
            new Card('S', 13)))), "test 5 fail");    

        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 2), 
            new Card('H', 8), 
            new Card('H', 4)))), "test 6 fail");  
        
        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 10), 
            new Card('D', 11), 
            new Card('D', 13)))), "test 7 fail");  
            
        assertEquals(4, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 8), 
            new Card('D', 2), 
            new Card('D', 14)))), "test 8 fail");
    }

    @Test
    void evalHandPair() {
        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 14), 
            new Card('C', 2), 
            new Card('S', 2)))), "test 1 fail");
        
        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('H', 2), 
            new Card('D', 5), 
            new Card('C', 5)))), "test 2 fail");

        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 7), 
            new Card('H', 4), 
            new Card('C', 7)))), "test 3 fail");
            
        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 12), 
            new Card('S', 6), 
            new Card('C', 6)))), "test 4 fail");
            
        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 7), 
            new Card('S', 7), 
            new Card('C', 9)))), "test 5 fail");    

        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('S', 6), 
            new Card('D', 6), 
            new Card('H', 5)))), "test 6 fail");  
        
        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('D', 2), 
            new Card('C', 11), 
            new Card('C', 11)))), "test 7 fail");  
            
        assertEquals(5, ThreeCardLogic.evalHand(
            new ArrayList<Card>(Arrays.asList(
            new Card('C', 4), 
            new Card('H', 6), 
            new Card('S', 4)))), "test 8 fail");
    }

    @Test
    void evalPPWinningsBasic() {
        int val = ThreeCardLogic.evalPPWinnings(straightFlush, 25);
        assertEquals(1000, val, "Expected 1000, got " + val);

        val = ThreeCardLogic.evalPPWinnings(threeOfAKind, 25);
        assertEquals(750, val, "Expected 750, got " + val);

        val = ThreeCardLogic.evalPPWinnings(straight, 25);
        assertEquals(150, val, "Expected 150, got " + val);

        val = ThreeCardLogic.evalPPWinnings(flush, 25);
        assertEquals(75, val, "Expected 75, got " + val);

        val = ThreeCardLogic.evalPPWinnings(pair, 25);
        assertEquals(25, val, "Expected 25, got " + val);

        val = ThreeCardLogic.evalPPWinnings(highCard, 25);
        assertEquals(0, val, "Expected 0, got " + val);
    }

    //make more 

    @Test
    void compareHandsDealerWin() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(straightFlush, flush), "dealer did not win for a straight flush vs flush");
        assertEquals(DEALER, ThreeCardLogic.compareHands(threeOfAKind, highCard), "dealer did not win for a three of a kind vs high card");
        assertEquals(DEALER, ThreeCardLogic.compareHands(pair, highCard), "dealer did not win for a pair vs high card");
        assertEquals(DEALER, ThreeCardLogic.compareHands(straight, pair), "dealer did not win for a straight vs pair");
        assertEquals(DEALER, ThreeCardLogic.compareHands(flush, highCard), "dealer did not win for a flush vs highcard");
    }

    @Test
    void compareHandsPlayerWin() {
        assertEquals(PLAYER, ThreeCardLogic.compareHands(flush, straightFlush), "player did not win for a straight flush vs flush");
        assertEquals(PLAYER, ThreeCardLogic.compareHands(highCard, threeOfAKind), "player did not win for a three of a kind vs high card");
        assertEquals(PLAYER, ThreeCardLogic.compareHands(highCard, pair), "player did not win for a pair vs high card");
        assertEquals(PLAYER, ThreeCardLogic.compareHands(pair, straight), "player did not win for a straight vs pair");
        assertEquals(PLAYER, ThreeCardLogic.compareHands(highCard, flush), "player did not win for a flush vs highcard");
    }

    @Test
    void compareHandsStraightFlush() {
        //Straight flush should win agains three of a kind
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 9),
                new Card('H', 8)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 6),
                new Card('C', 4),
                new Card('C', 3)
            )
        ))); 

        //Straight flush should win against a straight
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 9),
                new Card('H', 8)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('S', 6),
                new Card('C', 4),
                new Card('D', 3)
            )
        ))); 

        //Straight flush should win against a flush
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 9),
                new Card('H', 8)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 6),
                new Card('C', 12),
                new Card('C', 3)
            )
        )));

        //Straight flush should win against a pair
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 9),
                new Card('H', 8)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 6),
                new Card('S', 6),
                new Card('C', 3)
            )
        ))); 
        
        //win against a high card
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 9),
                new Card('H', 8)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 6),
                new Card('C', 4),
                new Card('C', 3)
            )
        ))); 
    }

    @Test
    void compareHandsThreeKind() {
        //Three kind should lose to Straight flush
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('S', 10),
                new Card('D', 10)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 5),
                new Card('C', 4),
                new Card('C', 3)
            )
        ))); 

        //Three kind should win against a straight
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 2),
                new Card('S', 2),
                new Card('D', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 14),
                new Card('C', 13),
                new Card('S', 12)
            )
        ))); 

        //Three kind should win against a flush
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 2),
                new Card('S', 2),
                new Card('D', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('C', 12),
                new Card('C', 10)
            )
        ))); 

        //Three of a kind should win against a pair
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 2),
                new Card('S', 2),
                new Card('D', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('D', 14),
                new Card('S', 12)
            )
        ))); 

        //Three of a kind should win against a high card
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 2),
                new Card('S', 2),
                new Card('D', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 4),
                new Card('C', 13)
            )
        ))); 
    }

    void compareHandsStraight() {
        //Straight should lose to Straight flush
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('H', 13),
                new Card('C', 12)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 2),
                new Card('C', 3),
                new Card('C', 4)
            )
        ))); 
        //Straight should lose to three of a kind
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('H', 13),
                new Card('C', 12)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('S', 2),
                new Card('D', 2),
                new Card('C', 2)
            )
        )));
        //Straight should win against flush
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 2),
                new Card('H', 2),
                new Card('C', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('C', 13),
                new Card('C', 11)
            )
        )));
        //Straight should win against pair
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 2),
                new Card('H', 2),
                new Card('C', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 14),
                new Card('C', 14),
                new Card('C', 13)
            )
        )));
        //Straight should win against high card
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 2),
                new Card('H', 2),
                new Card('C', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('D', 13),
                new Card('S', 11)
            )
        )));
    }

    @Test
    void compareHandsFlush() {
        //Flush should lose to straight flush
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('S', 13),
                new Card('S', 11)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 2),
                new Card('C', 3),
                new Card('C', 4)
            )
        )));
        
        //Flush should to three of a kind
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('S', 13),
                new Card('S', 11)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 14),
                new Card('C', 14),
                new Card('S', 14)
            )
        )));

        //Flush should lose to straight
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('S', 13),
                new Card('S', 11)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 2),
                new Card('C', 3),
                new Card('S', 4)
            )
        )));

        //Flush should win to pair
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 2),
                new Card('S', 3),
                new Card('S', 5)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 14),
                new Card('C', 14),
                new Card('S', 13)
            )
        )));

        //Flush should win to high card
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('D', 2),
                new Card('D', 3),
                new Card('D', 5)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 14),
                new Card('C', 13),
                new Card('S', 11)
            )
        )));
    }

    @Test
    void compareHandsTrueTie() {
        assertEquals(TIE, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 6),
                new Card('D', 6),
                new Card('S', 4)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 6),
                new Card('S', 6),
                new Card('C', 10)
            )
        )));
    }

    @Test
    void compareHandsTieHighCard() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('D', 6),
                new Card('S', 4)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 10),
                new Card('S', 4),
                new Card('C', 3)
            )
        )));
        
        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 6),
                new Card('S', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('S', 7),
                new Card('C', 3)
            )
        ))); 

        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 10),
                new Card('S', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('S', 10),
                new Card('C', 3)
            )
        )));

        assertEquals(0, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 10),
                new Card('S', 2)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 14),
                new Card('S', 10),
                new Card('C', 2)
            )
        )));
    }

    @Test
    void compareHandsTieStraightFlush() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 11),
                new Card('H', 12),
                new Card('H', 13)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 5),
                new Card('C', 4),
                new Card('C', 3)
            )
        ))); 

        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 11),
                new Card('H', 10),
                new Card('H', 9)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 6),
                new Card('C', 4),
                new Card('C', 5)
            )
        ))); 

        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 11),
                new Card('H', 12)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('S', 12),
                new Card('S', 14),
                new Card('S', 13)
            )
        ))); 

        assertEquals(PLAYER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('H', 3),
                new Card('H', 2),
                new Card('H', 4)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('C', 9),
                new Card('C', 7),
                new Card('C', 8)
            )
        ))); 
    }

    @Test
    void compareHandsTieThreeKind() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 14),
                new Card('C', 14)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('S', 12),
                new Card('D', 12),
                new Card('C', 12)
            )
        )));

    }

    @Test
    void compareHandsTieStraight() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 13),
                new Card('C', 12)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 2),
                new Card('C', 3),
                new Card('S', 4)
            )
        )));
    }

    @Test
    void compareHandsTieFlush() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('S', 13),
                new Card('S', 11)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 2),
                new Card('D', 5),
                new Card('D', 4)
            )
        )));
    }

    @Test
    void compareHandsTiePair() {
        assertEquals(DEALER, ThreeCardLogic.compareHands(
            new ArrayList<>(Arrays.asList(
                new Card('S', 14),
                new Card('D', 14),
                new Card('S', 13)
            )),
            new ArrayList<>(Arrays.asList(
                new Card('D', 13),
                new Card('D', 3),
                new Card('S', 13)
            )
        )));
    }
}
