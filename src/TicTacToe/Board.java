/*
    Kyle Spomer (U0000003184)
    COP3330.02 Computer Programming 2
    Programming Exercise 1 - Tic Tac Toe
    Due Sept. 7th, 2017
    
    TicTacToe Operation Instructions: Run main method TicTacTest.java, follow prompts in console
 */
package TicTacToe;

/*
    Board.java
    This class manages the board, and it's operation.
    It also checks the board for a winning or draw state and validates moves
    Methods: Board()
             printBoard()
             getBoard()
             setBoard()
             checkStatus()
             checkWin()
             checkDraw()
 */
public class Board {

    private char[][] board;
    private boolean moveStatus;
    private int openspaces = 9;

    public Board() {
        board = new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

    }

    public void printBoard() {
        System.out.printf("+-----------+%n");
        System.out.printf("| %c | %c | %c |%n", board[0][0], board[0][1], board[0][2]);
        System.out.printf("|---+---+---|%n");
        System.out.printf("| %c | %c | %c |%n", board[1][0], board[1][1], board[1][2]);
        System.out.printf("|---+---+---|%n");
        System.out.printf("| %c | %c | %c |%n", board[2][0], board[2][1], board[2][2]);
        System.out.printf("+-----------+%n");
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char pos, char team) {
        moveStatus = false;
        switch (pos) {
            case '1':
                if (board[0][0] == pos) {
                    board[0][0] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;

                    break;
                }
            case '2':
                if (board[0][1] == pos) {
                    board[0][1] = team;
                    openspaces--;
                    moveStatus = true;

                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '3':
                if (board[0][2] == pos) {
                    board[0][2] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '4':
                if (board[1][0] == pos) {
                    board[1][0] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '5':
                if (board[1][1] == pos) {
                    board[1][1] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '6':
                if (board[1][2] == pos) {
                    board[1][2] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '7':
                if (board[2][0] == pos) {
                    board[2][0] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '8':
                if (board[2][1] == pos) {
                    board[2][1] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            case '9':
                if (board[2][2] == pos) {
                    board[2][2] = team;
                    openspaces--;
                    moveStatus = true;
                    break;
                } else {
                    moveStatus = false;
                    break;
                }
            default:
                moveStatus = false;
        }
    }

    public boolean checkStatus() {
        return moveStatus;
    }

    public boolean checkWin(char team) {
        if (board[0][0] == team && board[0][1] == team && board[0][2] == team) { //check row 1
            return true;
        } else if (board[1][0] == team && board[1][1] == team && board[1][2] == team) { //check row 2
            return true;
        } else if (board[2][0] == team && board[2][1] == team && board[2][2] == team) { //check row 3
            return true;
        } else if (board[0][0] == team && board[1][0] == team && board[2][0] == team) { //check column 1
            return true;
        } else if (board[0][1] == team && board[1][1] == team && board[2][1] == team) { //check column 2
            return true;
        } else if (board[0][2] == team && board[1][2] == team && board[2][2] == team) { //check column 3
            return true;
        } else if (board[0][0] == team && board[1][1] == team && board[2][2] == team) { //check diagonal 1
            return true;
        } else if (board[0][2] == team && board[1][1] == team && board[2][0] == team) { //check diagonal 2
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDraw() {
        return openspaces == 0;

    }

}
