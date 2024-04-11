import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**Class: Room
 * @author Lincoln Bruce
 * @version 1.0
 * Course: ITEC
 * Written: Jan 11, 2024
 *
 * This is the class for the common attribute of all rooms.
 */
public class Room {

    //The following variables are the common attribute between all rooms
    private int roomID;
    private boolean visited;
    private String roomName;
    private int north;
    private int south;
    private int east;
    private int west;
    private String roomDescription;
    private Puzzle puzzle;
    private ArrayList<Item> roomInventory;
    private Monster monster;


    //Constructor and Initialization of attributes
    public Room(int roomID, boolean visited, String roomName, int north, int south, int east, int west, String roomDescription) {
        this.roomID = roomID;
        this.visited = visited;
        this.roomName = roomName;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.roomDescription = roomDescription;
        this.puzzle = null;
        this.roomInventory = new ArrayList<>();
        this.monster = null;
    }

    //Getters and Setters
    public int getRoomID() {
        return roomID;
    }
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getNorth() {
        return north;
    }
    public void setNorth(int north) {
        this.north = north;
    }

    public int getSouth() {
        return south;
    }
    public void setSouth(int south) {
        this.south = south;
    }

    public int getEast() {
        return east;
    }
    public void setEast(int east) {
        this.east = east;
    }

    public int getWest() {
        return west;
    }
    public void setWest(int west) {
        this.west = west;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public ArrayList<Item> getRoomInventory() {
        return roomInventory;
    }
    public void setRoomInventory(ArrayList<Item> roomInventory) {
        this.roomInventory = roomInventory;
    }

    public Monster getMonster() {
        return monster;
    }
    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    //Method to read the text file for the creation of rooms
    public static void readRooms(String filePath, ArrayList<Room> listOfRooms, ArrayList<Puzzle> listOfPuzzles, ArrayList<Monster> listOfMonsters) {
        try {
            //Read the file
            File myRooms = new File(filePath);
            Scanner myReader = new Scanner(myRooms);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("-");
                int roomID = Integer.parseInt(parts[0].trim());
                boolean visited = Boolean.parseBoolean(parts[1].trim());
                String roomName = parts[2].trim();
                int north = Integer.parseInt(parts[3].trim());
                int south = Integer.parseInt(parts[4].trim());
                int east = Integer.parseInt(parts[5].trim());
                int west = Integer.parseInt(parts[6].trim());
                String roomDescription = parts[7].trim();
                int puzzleID = Integer.parseInt(parts[8].trim());

                //Create a new room object based on the data read from the file
                Room room = new Room(roomID, visited, roomName, north, south, east, west, roomDescription);

                //Check if the room has a puzzle or monster
                if (puzzleID != 0) {
                    for (Puzzle puzzle : listOfPuzzles) {
                        if (puzzle.getPuzzleID() == puzzleID) {
                            room.setPuzzle(puzzle);
                            break;
                        }
                    }
                }
                for (Monster monster : listOfMonsters) {
                    if (monster.getMonsterRoomID() == roomID) {
                        room.setMonster(monster);
                        break;
                    }
                }

                //Add the room to the list of rooms
                listOfRooms.add(room);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the rooms file.");
        }
    }//end method

    //Method to add items to the room
    public static void addItemsToRoom(ArrayList<Item> listOfItems, ArrayList<Room> listOfRooms) {
        for (Item item : listOfItems) {
            for (Room room : listOfRooms) {
                if (item.getItemRoomID() == room.getRoomID()) {
                    room.getRoomInventory().add(item);
                }
            }
        }
    }//end method

    //Method to reset the puzzle
    public void resetPuzzleAttempt() {
        if (puzzle != null) {
            puzzle.setAttempts(puzzle.getInitialAttempts());
        }
    }//end method

}//end Room