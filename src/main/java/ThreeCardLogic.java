import java.util.ArrayList;
import java.util.Arrays;

public class ThreeCardLogic {
    //for readability, I'll be returning these
    static final int HIGH_CARD = 0;
    static final int STRAIGHT_FLUSH = 1;
    static final int THREE_KIND = 2;
    static final int STRAIGHT = 3;
    static final int FLUSH = 4;
    static final int PAIR = 5;

    //useful function for controller
    public static int highestCardVal(ArrayList<Card> hand) {
        int max = hand.get(0).value;
        for (int i = 1; i < 3; i++) {
            if (hand.get(i).value > max) {
                max = hand.get(i).value;
            }
        }
        return max;
    }
    //this will return an integer value representing the value of the hand
        // 0 if it's just a high card
            //no special conditions, just the "highest" card
        // 1 for a straight flush
            //cards are numbers in a row all of the same suit (i.e. 8 of clubs, 9 of clubs, 10 of clubs)
        // 2 for a three of a kind
            //three cards of the same value (6 of clubs, 6 of diamonds, 6 of hearts)
        // 3 for a straight
            //numbers are in a row (8 of clubs, 7 of diamonds, 6 of spades)
        // 4 for a flush
            //numbers are all of the same suit (ace of diamonds, 10 of diamonds, 5 of diamonds)
        // 5 for a pair
            //two cards of the same value (2 of diamonds, 2 of hearts)
    public static int evalHand(ArrayList<Card> hand) {
        boolean isStraight = true; //we assume straight and are proved otherwise by the algorithm
        boolean isFlush = false; //we assume flush and are proved otherwise by the algorithm
        int numValEq = 0; //used to determine straight/high card v pair v threekind

        //for readability
        Card c1 = hand.get(0);
        Card c2 = hand.get(1);
        Card c3 = hand.get(2);

        //Initial Algorithm Idea:     //////////////////
        //      Check to see if any hands have the same values
        //          if 3 are the same
        //              return THREE_KIND
        //          if 2 are the same
        //              return PAIR
        //          if none are the same
        //              check to see if there's a straight
        //              if straight, isStraight = true else = false
        //      Check to see if any suits are the same
        //          if 3 are the same
        //             if isStraight
        //              return STRAIGHT_FLUSH
        //             else
        //              return FLUSH
        //          else
        //             if isStraight
        //              return STRAIGHT
        //             else
        //              return HIGH_CARD
        // /////////////////////////////////////////////
        
        //hands with same values, it's just three comparisons so no need for a loop or anything
        //i kinda misled myself so here's the dealio
        //  numValEq < 1 : no cards are the same (high card or straight/straight flush)
        //  numValEq = 1 : 2 cards are the same (pair)
        //  numValEq > 1 : 3 cards are the same (three of a kind)
        if (c1.value == c2.value) {numValEq++;}
        if (c1.value == c3.value) {numValEq++;}
        if (c2.value == c3.value) {numValEq++;}
        
        if (numValEq > 1) return THREE_KIND;
        if (numValEq == 1) return PAIR;
        else {
            //checking for a straight means 7 - 8 - 9 and the like
            //This is an EASY solution, but probably not a GREAT solution. If I have time I'll make this not rely on sorting
            ArrayList<Integer> sort_value = new ArrayList<>(Arrays.asList(c1.value, c2.value, c3.value));
            sort_value.sort(null);
            //System.out.println(sort_value);
            for (int i = 1; i < sort_value.size(); i++) {
                if (sort_value.get(i) != sort_value.get(i-1) + 1) {isStraight = false;}

                /*if (isStraight) {
                    System.out.println("STRAIGHT FOR " + sort_value.get(i) + " AND " + sort_value.get(i-1));
                }
                else {
                    System.out.println("NOT STRAIGHT FOR " + sort_value.get(i) + " AND " + sort_value.get(i-1));
                }*/
            }
        }
        //Now, we check if suits are the same. following the same principle as values...
        //Here, we only care if they're ALL the same. That is a flush
        //if c1 == c2 and c1 == c3 then c2 == c3 (EPIC LOGIC)
        if (c1.suit == c2.suit && c1.suit == c3.suit) isFlush = true;

        //this could be reorganized, maybe?
        if (isFlush) {
            if (isStraight) return STRAIGHT_FLUSH;
            else return FLUSH;
        }
        else {
            if (isStraight) return STRAIGHT;
            else return HIGH_CARD;
        }
        
    }

    //evalPPWinnings will return the amount won for PairPlus bet
    // evaluate hand, evluate winnings, return amount won. if the player lost the bet, return 0.
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        //  40 to 1 - Straight Flush (bet * 40)
        //  30 to 1 - Three Kind (bet * 30)
        //   6 to 1 - Straight (bet * 6)
        //   3 to 1 - Flush (bet * 3)
        //   1 to 1 - Pair (bet)
        
        int handVal = evalHand(hand);
        int modifiedBet = 0;

        switch (handVal) {
            case STRAIGHT_FLUSH:
                modifiedBet = bet * 40;
                break;
            case THREE_KIND:
                modifiedBet = bet * 30;
                break;
            case STRAIGHT:
                modifiedBet = bet * 6;
                break;
            case FLUSH:
                modifiedBet = bet * 3;
                break;
            case PAIR:
                modifiedBet = bet;
                break;
            default:
        }
        return modifiedBet;
    }

    //return 0 if neither hand won
    // 1 if dealer won
    // 2 is player won
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int d_win = evalHand(dealer);
        int p_win = evalHand(player);
        
        //just a workaround from the inconvenience of HIGH_CARD being higher than the rest
        if (d_win == 0) d_win = 6;
        if (p_win == 0) p_win = 6;

        //A sorted list of values will make it much easier to break a tie
        ArrayList<Integer> player_sort = new ArrayList<>(Arrays.asList(player.get(0).value, player.get(1).value, player.get(2).value));
        ArrayList<Integer> dealer_sort = new ArrayList<>(Arrays.asList(dealer.get(0).value, dealer.get(1).value, dealer.get(2).value));
        player_sort.sort(null);
        dealer_sort.sort(null);
        
        //very baseline... it matters the value, change this 
        //THE LOWER VALUE WINS.
        if (d_win == p_win) {
            //This means they got the same type of hand
            //Broker the tie by determining the deck with highest cards
            //The highest value per deck should be last, this works for everything except pairs
            if (p_win == 6 ||
                p_win == STRAIGHT_FLUSH ||
                p_win == THREE_KIND ||
                p_win == STRAIGHT ||
                p_win == FLUSH) {
                if (player_sort.get(2) > dealer_sort.get(2)) return 2;
                else if (player_sort.get(2) < dealer_sort.get(2)) return 1;
                else {
                    if (player_sort.get(1) > dealer_sort.get(1)) return 2;
                    else if (player_sort.get(1) < dealer_sort.get(1)) return 1;
                    else {
                        if (player_sort.get(0) > dealer_sort.get(0)) return 2;
                        else if (player_sort.get(0) < dealer_sort.get(0)) return 1;
                        else return 0;
                    }
                }
            }
            else {
                //yeah so the PAIR... only what the pair is matters. LUCKILY!!!!
                //MATHEMATICALLY THE SECOND INDEX IS GUARANTEED TO BE THE PAIR NUM AFTER A SORT!!
                //SO JUST DO THAT... AGAIN!!
                if (player_sort.get(1) > dealer_sort.get(1)) return 2;
                else if (player_sort.get(1) < dealer_sort.get(1)) return 1;
                else return 0;
            }
        }
        else if (d_win < p_win) return 1;
        else return 2;
    }
}
