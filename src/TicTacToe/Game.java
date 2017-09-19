/*
    Kyle Spomer (U0000003184)
    COP3330.02 Computer Programming 2
    Programming Exercise 1 - Tic Tac Toe
    Due Sept. 7th, 2017
    
    TicTacToe Operation Instructions: Run main method TicTacTest.java, follow prompts in console
 */
package TicTacToe;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
    Game.java
    This class manages the game, and it's operation including a 
    human player, a computer player and the board.
    Methods: playGame()
             checkEnd()
 */
public class Game {

    Board theBoard;
    Player thePlayer = new Player();
    Computer theComputer = new Computer();
    //Computer computerTwo = new Computer(); //for training
    private boolean playAgain = true;

    //private int counter = 10000;
    public void playGame() throws FileNotFoundException, IOException {
        thePlayer.chooseTeam();
        //computerTwo.chooseTeam('H');
        theComputer.chooseTeam(thePlayer.getTeam());

        while (playAgain) {
            int move_count = 0;
            //initialize a new game
            theBoard = new Board();
            theBoard.printBoard();

            while (true) {

                //get player move and check for validity
                thePlayer.move(theBoard);
                //check for endgame   
                if (checkEnd()) {
                    theComputer.nextTrain();
                    break;
                }
                //get computer's move and check for validity
                theComputer.move(theBoard, thePlayer.getTeam(), move_count);
                move_count++;

                //check for endgame
                if (checkEnd()) {
                    theComputer.nextTrain();
                    break;
                }
            }
            playAgain = thePlayer.playAgain();
        }
        theComputer.endTrain();
    }

    public boolean checkEnd() {
        if (theBoard.checkWin(theComputer.getTeam()) == true) {
            System.out.println("You lose!");

            return true;
        } else if (theBoard.checkWin(thePlayer.getTeam()) == true) {
            System.out.println("You win!");
            return true;
        } else if (theBoard.checkDraw() == true) {
            System.out.println("Draw!");
            return true;
        } else {
            return false;
        }
    }

}
