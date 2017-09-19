/*
    Kyle Spomer (U0000003184)
    COP3330.02 Computer Programming 2
    Programming Exercise 1 - Tic Tac Toe
    Due Sept. 7th, 2017
    
    TicTacToe Operation Instructions: Run main method TicTacTest.java, follow prompts in console
 */
package TicTacToe;

import java.util.Scanner;

/*
    Player.java
    This class manages the human player, their information and choices
    Methods: Player()
             chooseTeam()
             getTeam()
             playAgain()
             move()
             chooseMove()
 */
public class Player {

    Scanner in = new Scanner(System.in);
    private char team;

    public Player(){
        
    }
    
    public void chooseTeam() {
        System.out.println("Which team? (X or O)");
        team = (in.next().charAt(0));
        team = Character.toUpperCase(team);
    }

    public char getTeam() {
        return team;
    }

    public boolean playAgain() {
        System.out.println("Play again? (y/n)");
        char again = (in.next().charAt(0));
        if (again == 'n') {
            System.out.println("Goodbye!");
            return false;
        } else {
            return true;
        }
    }

    public void move(Board theBoard) {
        theBoard.setBoard(chooseMove(), team);
        while (theBoard.checkStatus() == false) {
            System.out.println("Invalid Move! Try again, dummy.");
            theBoard.setBoard(chooseMove(), team);
            theBoard.printBoard();
        }
        theBoard.printBoard();
    }

    private char chooseMove() {
        System.out.println("Which position do you chose?");
        char choice = (in.next().charAt(0));
        return choice;
    }
}
