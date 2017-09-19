/*
    Kyle Spomer (U0000003184)
    COP3330.02 Computer Programming 2
    Programming Exercise 1 - Tic Tac Toe
    Due Sept. 7th, 2017
    
    TicTacToe Operation Instructions: Run main method TicTacTest.java, follow prompts in console
 */
package TicTacToe;

/*
    Trainer.java
    This class manages the AI trainer, including file writing and reading
    Methods: Trainer()
             newData()
             trainNext()
             trainEnd()
             dataCleanup()
 */
import java.io.*;

public class Trainer {

    private String fileName;
    private String winName;
    BufferedWriter buffWriter;
    FileWriter fileWriter;

    public Trainer() {
        fileName = "moves.txt";
        winName = "wins.txt";
        try {
            fileWriter = new FileWriter(fileName);
            buffWriter = new BufferedWriter(fileWriter);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file "
                    + fileName);
        }
    }

    public void newData(char move) {
        try {
            buffWriter.write(move);
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file "
                    + fileName);
        }
    }

    public void trainNext() {

        try {
            buffWriter.newLine();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file "
                    + fileName);
        }
    }

    public void trainEnd() {

        try {
            buffWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file "
                    + fileName);
        }
    }

    public void dataCleanup() throws FileNotFoundException, IOException {
        // This will reference one line at a time
        String line = null;

        FileReader fileReader
                = new FileReader(fileName);
        FileWriter writer
                = new FileWriter(winName, true);
        BufferedReader bufferedReader
                = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter
                = new BufferedWriter(writer);
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("W")) {
                    try {
                        bufferedWriter.write(line + '\n');
                    } catch (IOException ex) {
                        System.out.println(
                                "Error writing to file "
                                + winName);
                    }
                }
            }
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                System.out.println(
                        "Error closing file "
                        + winName);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file "
                    + fileName);
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file "
                    + fileName);

        }
    }
}
