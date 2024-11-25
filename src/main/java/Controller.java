import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Controller implements Initializable {
	private Parent root2;
    Player playerOne = new Player();
	Player playerTwo = new Player();
	Dealer theDealer = new Dealer();

    private boolean player1Folded; //for logic involving how to proceed after player 2

    //using this for printing results
    ArrayList<String> hands = new ArrayList<>(
        Arrays.asList("High Card", "Straight Flush", "Three Of A Kind", "Straight", "Flush", "Pair"));

    @FXML
    private MenuBar menubar;

    @FXML
    private MenuItem exit, freshStart, newLook;

	@FXML
	private Pane root;

    @FXML
    private Text titleText;

    @FXML
    private Text p1, p2, p3, 
                 d1, d2, d3,
                 p21,p22,p23,
                 p1Cash, p1AnteText, p1PairPlusText, p1PlayText,
                 p2Cash, p2AnteText, p2PairPlusText, p2PlayText,
                 p1label, p2label, dLabel,
                 sorryLabel;
    
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button exitScreenButton, returnButton;

    @FXML
    private Button player1Deal, player1Play, player1Fold,
                   player2Deal, player2Play, player2Fold;

    @FXML
    private TextArea putText;
    @FXML
    private TextField p1Ante, p1PairPlus,
                      p2Ante, p2PairPlus;

    
    //static so each instance of controller can access to update 
    private static String textEntered = "";
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        System.out.println("FXML Loaded and Initialized");
	}
    //method so that the controller created for second view can update the text
    //field with the text from the first view

    public void clearP1Ante() {p1Ante.setText("");}
    public void clearP1PairPlus() {p1PairPlus.setText("");}
    public void clearP2Ante() {p2Ante.setText("");}
    public void clearP2PairPlus() {p2PairPlus.setText("");}

    public void setText() {
        putText.appendText("It is Player 1's turn.\n");
    }

    public void exitGame() {
        Platform.exit();
    }

    public void obtuseExitGame(ActionEvent g) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/exit.fxml"));
            Parent root = loader.load();

            root.getStylesheets().add("/styles/style2.css");

            Stage stage = (Stage)menubar.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 750));
            stage.show();
        }
        catch (IOException f) {
            f.printStackTrace();
        }
        
    }

    //this doesnt work
    public void returnToGame(ActionEvent g) {
        Stage stage = (Stage)exitScreenButton.getScene().getWindow();
        stage.setScene(exitButton.getScene());
        stage.show();
    }

    public void changeColors() {
        menubar.getScene().getRoot().getStylesheets().add("/styles/style3.css");
    }

    public void resetGame() {
        p1PlayText.setText("Play: $0\n");
        p1PairPlusText.setText("PP: $0\n");
        p2PlayText.setText("Play: $0\n");
        p2PairPlusText.setText("PP: $0\n");
        p1.setText("");
        p2.setText("");
        p3.setText("");
        p21.setText("");
        p22.setText("");
        p23.setText("");
        d1.setText("");
        d2.setText("");
        d3.setText("");
        putText.clear();
        putText.appendText("It is Player 1's turn.\n");
        player1Deal.setDisable(false);
        player1Play.setDisable(true);
        player1Fold.setDisable(true);
        player2Deal.setDisable(true);
        player2Play.setDisable(true);
        player2Fold.setDisable(true);
        playerOne = new Player();
	    playerTwo = new Player();
	    theDealer = new Dealer();
    }

    public void startGame(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/game.fxml"));
        root2 = loader.load(); //load view into parent
        Controller game_controller = loader.getController();//get controller created by FXMLLoader
        game_controller.setText();//use MyLoader class method for setText()
        
        root2.getStylesheets().add("/styles/style2.css");//set style
        
        root.getScene().setRoot(root2);//update scene graph
    }

    public void dealCards() {
        p1PlayText.setText("Play: $0\n");
        p1PairPlusText.setText("PP: $0\n");
        p2PlayText.setText("Play: $0\n");
        p2PairPlusText.setText("PP: $0\n");
        d1.setText("");
        d2.setText("");
        d3.setText("");
        
        int parser = 0;
        //on deal, check to make sure ante and pairplus bets are valid
        try {
            parser = Integer.parseInt(p1Ante.getText());
            
        }
        catch (NumberFormatException e) {
            //putText.appendText("Parse = " + parser + "\n");
            putText.appendText("Please input a valid ante wager\n");
            return;
        }

        if (parser < 5 || parser > 25) {
            putText.appendText("Please input a value between 5 and 25\n");
            return;
        }

        playerOne.anteBet += parser;
        p1AnteText.setText("Ante: $" + playerOne.anteBet);

        playerOne.totalWinnings = playerOne.totalWinnings - playerOne.anteBet;
        p1Cash.setText("$"+playerOne.totalWinnings);
        p1Ante.setDisable(true);

        try {
            parser = Integer.parseInt(p1PairPlus.getText());
        }
        catch (NumberFormatException e) {
            parser = 0;
            playerOne.pairPlusBet = 0;
            p1PairPlus.setText(""+0);
        }

        if (parser != 0 && (parser < 5 || parser > 25)) {
            putText.appendText("Please input a value between 5 and 25\n");
        }
        playerOne.pairPlusBet = parser;
        p1PairPlusText.setText("PP: $" + playerOne.pairPlusBet + "\n");
        playerOne.totalWinnings = playerOne.totalWinnings - playerOne.pairPlusBet;
        p1Cash.setText("$"+playerOne.totalWinnings);
        p1PairPlus.setDisable(true);

        //if we reach here, everything is good to go to begin dealing
        playerOne.hand = theDealer.dealHand();
        theDealer.dealersHand = theDealer.dealHand();

        
        /*putText.setText("Player 1 : " + playerOne.hand.get(0).value + playerOne.hand.get(0).suit + 
                        playerOne.hand.get(1).value + playerOne.hand.get(1).suit +
                        playerOne.hand.get(2).value + playerOne.hand.get(2).suit +
                        " Dealer : " + theDealer.dealersHand.get(0).value + theDealer.dealersHand.get(0).suit + "\n" + 
                        theDealer.dealersHand.get(1).value + theDealer.dealersHand.get(1).suit + "\n" + 
                        theDealer.dealersHand.get(2).value + theDealer.dealersHand.get(2).suit + "\n");*/

        p1.setText("" + playerOne.hand.get(0).value + playerOne.hand.get(0).suit);
        p2.setText("" + playerOne.hand.get(1).value + playerOne.hand.get(1).suit);
        p3.setText("" + playerOne.hand.get(2).value + playerOne.hand.get(2).suit);

        player1Deal.setDisable(true);
        player1Play.setDisable(false);
        player1Fold.setDisable(false);
    }

    public void dealCards2() {
        int parser = 0;
        //on deal, check to make sure ante and pairplus bets are valid
        try {
            parser = Integer.parseInt(p2Ante.getText());
        }
        catch (NumberFormatException e) {
            putText.appendText("Please input a valid ante wager\n");
            return;
        }

        if (parser < 5 || parser > 25) {
            putText.appendText("Please input a value between 5 and 25\n");
            return;
        }

        playerTwo.anteBet += parser;
        p2AnteText.setText("Ante: $" + playerTwo.anteBet);

        playerTwo.totalWinnings = playerTwo.totalWinnings - playerTwo.anteBet;
        p2Cash.setText("$"+playerTwo.totalWinnings);
        p2Ante.setDisable(true);

        try {
            parser = Integer.parseInt(p2PairPlus.getText());
        }
        catch (NumberFormatException e) {
            parser = 0;
            playerTwo.pairPlusBet = 0;
            p2PairPlus.setText(""+0);
        }

        if (parser != 0 && (parser < 5 || parser > 25)) {
            putText.appendText("Please input a value of either 0 or between 5 and 25\n");
        }
        playerTwo.pairPlusBet = parser;
        playerTwo.totalWinnings = playerTwo.totalWinnings - playerTwo.pairPlusBet;
        p2PairPlusText.setText("PP: $" + playerTwo.pairPlusBet + "\n");
        p2Cash.setText("$"+playerTwo.totalWinnings);
        p2PairPlus.setDisable(true);

        //if we reach here, everything is good to go to begin dealing
        playerTwo.hand = theDealer.dealHand();
        theDealer.dealersHand = theDealer.dealHand();

        
        /*putText.setText("Player 1 : " + playerOne.hand.get(0).value + playerOne.hand.get(0).suit + 
                        playerOne.hand.get(1).value + playerOne.hand.get(1).suit +
                        playerOne.hand.get(2).value + playerOne.hand.get(2).suit +
                        " Dealer : " + theDealer.dealersHand.get(0).value + theDealer.dealersHand.get(0).suit + "\n" + 
                        theDealer.dealersHand.get(1).value + theDealer.dealersHand.get(1).suit + "\n" + 
                        theDealer.dealersHand.get(2).value + theDealer.dealersHand.get(2).suit + "\n");*/

        p21.setText("" + playerTwo.hand.get(0).value + playerTwo.hand.get(0).suit);
        p22.setText("" + playerTwo.hand.get(1).value + playerTwo.hand.get(1).suit);
        p23.setText("" + playerTwo.hand.get(2).value + playerTwo.hand.get(2).suit);

        player2Deal.setDisable(true);
        player2Play.setDisable(false);
        player2Fold.setDisable(false);
    }

    public void playHand() {
        boolean validDealerHand = true;
        playerOne.playBet = playerOne.anteBet;
        p1PlayText.setText("Play: $" + playerOne.playBet + "\n");
        playerOne.totalWinnings = playerOne.totalWinnings - playerOne.playBet;
        p1Cash.setText("$"+playerOne.totalWinnings);

        /* 
        d1.setText("" + theDealer.dealersHand.get(0).value + theDealer.dealersHand.get(0).suit);
        d2.setText("" + theDealer.dealersHand.get(1).value + theDealer.dealersHand.get(1).suit);
        d3.setText("" + theDealer.dealersHand.get(2).value + theDealer.dealersHand.get(2).suit); */

        p1Cash.setText("$"+playerOne.totalWinnings);
        if (ThreeCardLogic.evalHand(theDealer.dealersHand) == 0) {
            if (ThreeCardLogic.highestCardVal(theDealer.dealersHand) < 12) {
                validDealerHand = false;
                playerOne.totalWinnings += playerOne.playBet;
            } 
        }
        
        if (validDealerHand) {
            int winner = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
            if (winner == 1) {
                //putText.appendText("Dealer wins with " + hands.get(ThreeCardLogic.evalHand(theDealer.dealersHand)) + "\n");
                //playerOne.anteBet = 0;
                //p1AnteText.setText("Ante: $" + playerOne.anteBet);
            }
            else if (winner == 2) {
                //putText.appendText("Player wins with " + hands.get(ThreeCardLogic.evalHand(playerOne.hand)) + "\n");
                playerOne.totalWinnings += (playerOne.anteBet + playerOne.playBet) * 2;
                //playerOne.anteBet = 0;
                //p1AnteText.setText("Ante: $" + playerOne.anteBet);
            }
        }
        else {
            //putText.appendText("Dealer does not qualify, returning play bet to players." + "\n");
        }
        
        playerOne.totalWinnings += ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
        //p1Cash.setText("$"+playerOne.totalWinnings);

       
        player1Play.setDisable(true);
        player1Fold.setDisable(true);
        //p1Ante.setDisable(false);
        //p1PairPlus.setDisable(false);

        player2Deal.setDisable(false);
        p2Ante.setDisable(false);
        p2PairPlus.setDisable(false);
        putText.appendText("It is Player 2's turn\n");
    }

    public void playHand2() {
        boolean validDealerHand = true;
        playerTwo.playBet = playerTwo.anteBet;
        p2PlayText.setText("Play: $" + playerTwo.playBet + "\n");
        playerTwo.totalWinnings = playerTwo.totalWinnings - playerTwo.playBet;
        p2Cash.setText("$"+playerTwo.totalWinnings);

        
        d1.setText("" + theDealer.dealersHand.get(0).value + theDealer.dealersHand.get(0).suit);
        d2.setText("" + theDealer.dealersHand.get(1).value + theDealer.dealersHand.get(1).suit);
        d3.setText("" + theDealer.dealersHand.get(2).value + theDealer.dealersHand.get(2).suit);

       //p2Cash.setText("$"+playerTwo.totalWinnings);
        if (ThreeCardLogic.evalHand(theDealer.dealersHand) == 0) {
            if (ThreeCardLogic.highestCardVal(theDealer.dealersHand) < 12) {
                validDealerHand = false;
                playerTwo.totalWinnings += playerTwo.playBet;
            } 
        }
        
        if (validDealerHand) {
            int winner2 = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
            
            if (!player1Folded) {
                int winner1 = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
                if (winner1 == 1) {
                    putText.appendText("Dealer beats Player 1 with " + hands.get(ThreeCardLogic.evalHand(theDealer.dealersHand)) + "\n");
                    playerOne.anteBet = 0;
                    p1AnteText.setText("Ante: $" + playerOne.anteBet);
                }
                else if (winner1 == 2) {
                    putText.appendText("Player 1 wins with " + hands.get(ThreeCardLogic.evalHand(playerOne.hand)) + "\n");
                    //playerOne.totalWinnings += (playerOne.anteBet + playerOne.playBet) * 2;
                    playerOne.anteBet = 0;
                    p1AnteText.setText("Ante: $" + playerOne.anteBet);
                }
            }
            

            if (winner2 == 1) {
                putText.appendText("Dealer beats Player 2 with " + hands.get(ThreeCardLogic.evalHand(theDealer.dealersHand)) + "\n");
                playerTwo.anteBet = 0;
                p2AnteText.setText("Ante: $" + playerTwo.anteBet);
            }
            else if (winner2 == 2) {
                putText.appendText("Player 2 wins with " + hands.get(ThreeCardLogic.evalHand(playerTwo.hand)) + "\n");
                playerTwo.totalWinnings += (playerTwo.anteBet + playerTwo.playBet) * 2;
                playerTwo.anteBet = 0;
                p2AnteText.setText("Ante: $" + playerTwo.anteBet);
            }
        }
        else {
            putText.appendText("Dealer does not qualify, returning play bet to players." + "\n");
        }
        
        playerTwo.totalWinnings += ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);
        //p1Cash.setText("$"+playerOne.totalWinnings);

       
        player2Play.setDisable(true);
        player2Fold.setDisable(true);
        //p1Ante.setDisable(false);
        //p1PairPlus.setDisable(false);

        player1Deal.setDisable(false);
        p1Ante.setDisable(false);
        p1PairPlus.setDisable(false);
        putText.appendText("It is Player 1's turn\n");

        p1PairPlusText.setText("PP: $0\n");
        p2PairPlusText.setText("PP: $0\n");
    }

    public void foldHand() {
        putText.appendText("Player 1 folds" + "\n");

        player1Folded = true;

        /*d1.setText("" + theDealer.dealersHand.get(0).value + theDealer.dealersHand.get(0).suit);
        d2.setText("" + theDealer.dealersHand.get(1).value + theDealer.dealersHand.get(1).suit);
        d3.setText("" + theDealer.dealersHand.get(2).value + theDealer.dealersHand.get(2).suit);*/

        playerOne.anteBet = 0;
        playerOne.pairPlusBet = 0;
        p1AnteText.setText("Ante: $" + playerOne.anteBet);
        p1PairPlusText.setText("PP: $0\n");

        //putText.appendText("Dealer wins with " + hands.get(ThreeCardLogic.evalHand(playerOne.hand)) + "\n");
        //player1Deal.setDisable(false);
        player1Play.setDisable(true);
        player1Fold.setDisable(true);
        //p1Ante.setDisable(false);
        //p1PairPlus.setDisable(false);
        player2Deal.setDisable(false);
        p2Ante.setDisable(false);
        p2PairPlus.setDisable(false);
        putText.appendText("It is Player 2's turn\n");
    }

    public void foldHand2() {
        putText.appendText("Player 2 folds" + "\n");

        d1.setText("" + theDealer.dealersHand.get(0).value + theDealer.dealersHand.get(0).suit);
        d2.setText("" + theDealer.dealersHand.get(1).value + theDealer.dealersHand.get(1).suit);
        d3.setText("" + theDealer.dealersHand.get(2).value + theDealer.dealersHand.get(2).suit);

        playerTwo.anteBet = 0;
        playerTwo.pairPlusBet = 0;
        p2AnteText.setText("Ante: $" + playerTwo.anteBet);
        p1PairPlusText.setText("PP: $0\n");
        p2PairPlusText.setText("PP: $0\n");

        //putText.appendText("Dealer wins with " + hands.get(ThreeCardLogic.evalHand(playerOne.hand)) + "\n");
        //player1Deal.setDisable(false);
        player2Play.setDisable(true);
        player2Fold.setDisable(true);
        //p1Ante.setDisable(false);
        //p1PairPlus.setDisable(false);
        player1Deal.setDisable(false);
        p1Ante.setDisable(false);
        p1PairPlus.setDisable(false);
        

        //updated, in case player 2 folds but player 1 plays
        boolean validDealerHand = true;
        if (ThreeCardLogic.evalHand(theDealer.dealersHand) == 0) {
            if (ThreeCardLogic.highestCardVal(theDealer.dealersHand) < 12) {
                validDealerHand = false;
            } 
        }
        
        if (!player1Folded) {
            if (validDealerHand) {
                int winner1 = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
                //int winner2 = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
                if (winner1 == 1) {
                    putText.appendText("Dealer beats Player 1 with " + hands.get(ThreeCardLogic.evalHand(theDealer.dealersHand)) + "\n");
                    playerOne.anteBet = 0;
                    p1AnteText.setText("Ante: $" + playerOne.anteBet);
                }
                else if (winner1 == 2) {
                    putText.appendText("Player 1 wins with " + hands.get(ThreeCardLogic.evalHand(playerOne.hand)) + "\n");
                    playerOne.totalWinnings += (playerOne.anteBet + playerOne.playBet) * 2;
                    playerOne.anteBet = 0;
                    p1AnteText.setText("Ante: $" + playerOne.anteBet);
                }
            }
            else {
                putText.appendText("Dealer does not qualify, returning play bet to players." + "\n");
            }
        }
        

        putText.appendText("It is Player 1's turn\n");
    }
    
	
}
