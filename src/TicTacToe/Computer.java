/*
    Kyle Spomer (U0000003184)
    COP3330.02 Computer Programming 2
    Programming Exercise 1 - Tic Tac Toe
    Due Sept. 7th, 2017
    
    TicTacToe Operation Instructions: Run main method TicTacTest.java, follow prompts in console
 */
package TicTacToe;

/*
    Computer.java
    This class manages the computer player, and it's operation.
    It also handles processing the training data from Trainer.java to make an informed decision
    Methods: Computer()
             chooseTeam()
             getTeam()
             endTrain()
             nextTrain()
             move()
             makeMoveSmart()
             makeMoveTrained()
             makeMoveRandom()
             checkWinningMove()
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class Computer {

    private char team;
    private final Random rand;
    private char[] moves = {0, 0, 0, 0};

    boolean lastResort = false;
    private final String fileName = "wins.txt";

    Trainer theTrainer = new Trainer();

    public Computer() {
        rand = new SecureRandom();
    }

    public void chooseTeam(char player) {
        switch (player) {
            case 'X':
                team = 'O';
                break;
            default:
                team = 'X';
        }

    }

    public char getTeam() {
        return team;
    }

    public void endTrain() throws FileNotFoundException, IOException {
        theTrainer.trainEnd();
        theTrainer.dataCleanup();
    }

    public void nextTrain() {
        theTrainer.trainNext();
    }    
    
    public void move(Board theBoard, char playerTeam, int move_count) throws FileNotFoundException {

        theBoard.setBoard(makeMoveSmart(theBoard, team, playerTeam, move_count), team);
        while (theBoard.checkStatus() != true) {
            theBoard.setBoard(makeMoveSmart(theBoard, team, playerTeam, move_count), team);
        }
        theBoard.printBoard();

    }

    private char makeMoveRand() {
        char random = (char) ((rand.nextInt(9)) + '1');
        System.out.println("Random choice!: " + random);
        theTrainer.newData(random);
        return random;
    }

    private char makeMoveSmart(Board theBoard, char team, char enemyTeam, int mv_count) throws FileNotFoundException {

        if (checkWinningMove(theBoard.getBoard(), team, enemyTeam) != '0') {
            char win = checkWinningMove(theBoard.getBoard(), team, enemyTeam);
            System.out.println("Winning Move!: " + win);
            theTrainer.newData(win);
            theTrainer.newData('W');
            return win;
        } else if (checkWinningMove(theBoard.getBoard(), enemyTeam, team) != '0') {
            char block = checkWinningMove(theBoard.getBoard(), enemyTeam, team);
            System.out.println("Blocked!: " + block);
            theTrainer.newData(block);
            moves[mv_count] = block;
            return block;
        } else if (theBoard.getBoard()[1][1] == '5') {
            System.out.println("Take Center!: 5");
            theTrainer.newData('5');
            moves[mv_count] = '5';
            return '5'; //take center
        } else if (theBoard.getBoard()[1][1] == enemyTeam && mv_count == 0) {
            char [] corners = {'1', '3', '7', '9'};
            char corn = (char) (corners[rand.nextInt(4)]);
            System.out.printf("Corner!: %c \n", corn);
            return corn;
        } else if (!lastResort) {  //AI is built in, but never used. Rules above guarantee a win or a draw

            if (!theBoard.checkStatus()) {

                lastResort = true;
            }

            return makeMoveTrained(mv_count);

        } else {                    //also never used
            lastResort = false;
            return makeMoveRand();
        }
    }

    private char makeMoveTrained(int m_count) throws FileNotFoundException {
        String line = null;
        int[] prob = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        char greatest = '0';
        System.out.println("TicTacToe.Computer.makeMoveTrained()");
        FileReader fileReader
                = new FileReader(fileName);
        BufferedReader bufferedReader
                = new BufferedReader(fileReader);
        try {
            while ((line = bufferedReader.readLine()) != null) {

                switch (line.charAt(m_count)) {
                    case '1':
                        prob[0]++;
                        break;

                    case '2':
                        prob[1]++;
                        break;
                    case '3':
                        prob[2]++;
                        break;
                    case '4':
                        prob[3]++;
                        break;
                    case '5':
                        prob[4]++;
                        break;
                    case '6':
                        prob[5]++;
                        break;
                    case '7':
                        prob[6]++;
                        break;
                    case '8':
                        prob[7]++;
                        break;
                    case '9':
                        prob[8]++;
                        break;
                    default:
                        break;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");

        }
        for (int i = 0; i < 8; i++) {

            if (prob[i + 1] >= prob[i]) {
                greatest = (char) (i + 1 + '1');
                System.out.println(greatest);
            } else {
                greatest = (char) (i + '1');
            }
        }
        return greatest;
    }

    private char checkWinningMove(char[][] board, char tm1, char tm2) {

        if (board[0][0] == tm1 && board[0][1] == tm1 && board[2][0] != tm1 && board[0][2] != tm2) {
            return '3';
        } else if (board[0][0] == tm1 && board[0][2] == tm1 && board[0][1] != tm1 && board[0][1] != tm2) {
            return '2';
        } else if (board[0][1] == tm1 && board[0][2] == tm1 && board[0][0] != tm1 && board[0][0] != tm2) {
            return '1';
        } else if (board[1][0] == tm1 && board[1][1] == tm1 && board[1][2] != tm1 && board[1][2] != tm2) {
            return '6';
        } else if (board[1][0] == tm1 && board[1][2] == tm1 && board[1][1] != tm1 && board[1][1] != tm2) {
            return '5';
        } else if (board[1][1] == tm1 && board[1][2] == tm1 && board[1][0] != tm1 && board[1][0] != tm2) {
            return '4';
        } else if (board[2][0] == tm1 && board[2][1] == tm1 && board[2][2] != tm1 && board[2][2] != tm2) {
            return '9';
        } else if (board[2][0] == tm1 && board[2][2] == tm1 && board[2][1] != tm1 && board[2][1] != tm2) {
            return '8';
        } else if (board[2][1] == tm1 && board[2][2] == tm1 && board[2][0] != tm1 && board[2][0] != tm2) {
            return '7';
        } else if (board[2][0] == tm1 && board[1][0] == tm1 && board[0][0] != tm1 && board[0][0] != tm2) {
            return '1';
        } else if (board[2][0] == tm1 && board[0][0] == tm1 && board[1][0] != tm1 && board[1][0] != tm2) {
            return '4';
        } else if (board[0][0] == tm1 && board[1][0] == tm1 && board[2][0] != tm1 && board[2][0] != tm2) {
            return '7';
        } else if (board[2][1] == tm1 && board[1][1] == tm1 && board[0][1] != tm1 && board[0][1] != tm2) {
            return '2';
        } else if (board[2][1] == tm1 && board[0][1] == tm1 && board[1][1] != tm1 && board[1][1] != tm2) {
            return '5';
        } else if (board[0][1] == tm1 && board[1][1] == tm1 && board[2][1] != tm1 && board[2][1] != tm2) {
            return '8';
        } else if (board[2][2] == tm1 && board[1][2] == tm1 && board[0][2] != tm1 && board[0][2] != tm2) {
            return '3';
        } else if (board[2][2] == tm1 && board[0][2] == tm1 && board[1][2] != tm1 && board[1][2] != tm2) {
            return '6';
        } else if (board[0][2] == tm1 && board[1][2] == tm1 && board[2][2] != tm1 && board[2][2] != tm2) {
            return '9';
        } else if (board[1][1] == tm1 && board[2][2] == tm1 && board[0][0] != tm1 && board[0][0] != tm2) {
            return '1';
        } else if (board[0][0] == tm1 && board[2][2] == tm1 && board[1][1] != tm1 && board[1][1] != tm2) {
            return '5';
        } else if (board[1][1] == tm1 && board[0][0] == tm1 && board[2][2] != tm1 && board[2][2] != tm2) {
            return '9';
        } else if (board[1][1] == tm1 && board[2][0] == tm1 && board[0][2] != tm1 && board[0][2] != tm2) {
            return '3';
        } else if (board[0][2] == tm1 && board[2][0] == tm1 && board[1][1] != tm1 && board[1][1] != tm2) {
            return '5';
        } else if (board[1][1] == tm1 && board[0][2] == tm1 && board[2][0] != tm1 && board[2][0] != tm2) {
            return '7';
        } else {
            return '0';
        }
    }
}
