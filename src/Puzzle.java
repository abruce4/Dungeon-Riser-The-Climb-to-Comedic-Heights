import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {

    //Puzzle attributes
    private String puzzleName;
    private String puzzleDescription;
    private String puzzleAnswer;
    private int attempts;
    private int puzzleID;
    private int initialAttempts;
    private boolean isSolved = false;
    private int expDrop;

    //Constructor and Initialization of attributes
    public Puzzle(String puzzleName, String puzzleDescription, String puzzleAnswer, int attempts, int puzzleID, boolean isSolved) {
        this.puzzleName = puzzleName;
        this.puzzleDescription = puzzleDescription;
        this.puzzleAnswer = puzzleAnswer;
        this.attempts = attempts;
        this.puzzleID = puzzleID;
        this.initialAttempts = attempts;
        this.isSolved = isSolved;
        this.expDrop = 50;
    }

    //Getters and Setters
    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public void setPuzzleDescription(String puzzleDescription) {
        this.puzzleDescription = puzzleDescription;
    }

    public String getPuzzleAnswer() {
        return puzzleAnswer;
    }

    public void setPuzzleAnswer(String puzzleAnswer) {
        this.puzzleAnswer = puzzleAnswer;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public int getInitialAttempts() {
        return initialAttempts;
    }

    public void setInitialAttempts(int initialAttempts) {
        this.initialAttempts = initialAttempts;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    //toString method
    @Override
    public String toString() {
        return puzzleName;
    }

    //Method to read puzzles from the file
    public static void readPuzzles(String filePath, ArrayList<Puzzle> listOfPuzzles) {
        try {
            File myPuzzles = new File(filePath);
            Scanner myReader = new Scanner(myPuzzles);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("-");
                String puzzleName = parts[0].trim();
                String puzzleDescription = parts[1].trim();
                String puzzleAnswer = parts[2].trim();
                int attempts = Integer.parseInt(parts[3].trim());
                int puzzleID = Integer.parseInt(parts[4].trim());
                Puzzle puzzle = new Puzzle(puzzleName, puzzleDescription, puzzleAnswer, attempts, puzzleID, false);
                //Add the puzzle to the list of puzzles
                listOfPuzzles.add(puzzle);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the puzzles file.");
        }
    }

}//end Puzzle