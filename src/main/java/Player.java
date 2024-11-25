import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;

    Player() {
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 100;
    }
}
