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

/**
 *
 * @author kylespomer
 */
public class TicTacTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Game theGame = new Game();
        theGame.playGame();

    }
}
