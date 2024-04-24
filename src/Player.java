import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    //player attributes
    private int PlayerHealth;
    private int PlayerMaxHealth;
    private int PlayerAttack;
    private EquipableItem weapon;
    private EquipableItem armor;
    private String playerName;
    private Room currentRoom;
    private ArrayList<Item> playerInventory;
    private boolean inCombatMode;
    private int playerLevel;
    private int playerExperience;
    private int playerMaxExperience;

    //Constructor
    public Player(int PlayerHealth, int PlayerAttack, String playerName, Room startingRoom) {
        this.PlayerHealth = PlayerHealth;
        this.PlayerMaxHealth = PlayerHealth;
        this.PlayerAttack = PlayerAttack;
        this.weapon = null;
        this.armor = null;
        this.playerName = playerName;
        this.currentRoom = startingRoom;
        this.playerInventory = new ArrayList<>();
        this.inCombatMode = false;
        this.playerLevel = 1;
        this.playerExperience = 0;
        this.playerMaxExperience = 100;
    }

    //Getters and Setters
    public int getPlayerHealth() {
        return PlayerHealth;
    }
    public void setPlayerHealth(int playerHealth) {
        PlayerHealth = playerHealth;
    }

    public int getPlayerMaxHealth() {
        return PlayerMaxHealth;
    }
    public void setPlayerMaxHealth(int playerMaxHealth) {
        PlayerMaxHealth = playerMaxHealth;
    }

    public int getPlayerAttack() {
        return PlayerAttack;
    }
    public void setPlayerAttack(int playerAttack) {
        PlayerAttack = playerAttack;
    }

    public EquipableItem getWeapon() {
        return weapon;
    }
    public void setWeapon(EquipableItem weapon) {
        this.weapon = weapon;
    }

    public EquipableItem getArmor() {
        return armor;
    }
    public void setArmor(EquipableItem armor) {
        this.armor = armor;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }
    public void setPlayerInventory(ArrayList<Item> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public boolean isInCombatMode() {
        return inCombatMode;
    }
    public void setInCombatMode(boolean inCombatMode) {
        this.inCombatMode = inCombatMode;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    //Method for player navigation
    public void navigate(String direction, ArrayList<Room> listOfRooms) throws InterruptedException {
        if (isInCombatMode()) {
            System.out.println("You cannot navigate in combat mode!");
            return;
        }
        //using a method to store the room number of the next room
        int nextRoomNumber = getNextRoomNumber(direction);
        //check the room number the player entered and if the room has been visited before
        if (nextRoomNumber != 0) {
            Room nextRoom = getRoomByNumber(nextRoomNumber, listOfRooms);
            if (nextRoom != null) {
                setCurrentRoom(nextRoom);
                if (!nextRoom.isVisited()) {
                    System.out.println("You entered the " + getCurrentRoom().getRoomName() + ". " + getCurrentRoom().getRoomDescription());
                    nextRoom.setVisited(true);
                    if (getCurrentRoom().getPuzzle() != null && !getCurrentRoom().getPuzzle().isSolved()) {
                        displayPuzzle();
                    }
                }
                else {
                    System.out.println("You are back in the " + getCurrentRoom().getRoomName());
                    if (getCurrentRoom().getPuzzle() != null && !getCurrentRoom().getPuzzle().isSolved()) {
                        displayPuzzle();
                    }
                }
                getCurrentRoom().resetPuzzleAttempt();
            }
        }
        else {
            System.out.println("You can't go that way.");
        }
    }//end navigate

    //Method to determine room number of next room
    private int getNextRoomNumber(String direction) {
        if (direction.equalsIgnoreCase("N")) {
            return currentRoom.getNorth();
        }
        else if (direction.equalsIgnoreCase("S")) {
            return currentRoom.getSouth();
        }
        else if (direction.equalsIgnoreCase("E")) {
            return currentRoom.getEast();
        }
        else if (direction.equalsIgnoreCase("W")) {
            return currentRoom.getWest();
        }
        else {
            return -1;
        }
    }//end getNextRoomNumber

    //Method to return the room the player entered
    private Room getRoomByNumber(int roomNumber, ArrayList<Room> listOfRooms) {
        //iterate through the list and check room id with room number
        for (Room room : listOfRooms) {
            if (room.getRoomID() == roomNumber) {
                return room;
            }
        }
        return null;
    }//end getRoomByNumber

    //Method to explore a room
    public void exploreRoom() {
        if (isInCombatMode()) {
            System.out.println("You cannot explore in combat mode!");
            return;
        }
        if (getCurrentRoom().getRoomInventory().isEmpty() && getCurrentRoom().getMonster() == null) {
            System.out.println(getCurrentRoom().getRoomDescription());
            System.out.println("There is nothing in this room.");
        }
        else if (!getCurrentRoom().getRoomInventory().isEmpty() && getCurrentRoom().getMonster() == null){
            System.out.println(getCurrentRoom().getRoomDescription());
            System.out.println("You have found the following items in this room: " + getCurrentRoom().getRoomInventory());
        }
        else if (getCurrentRoom().getRoomInventory().isEmpty() && getCurrentRoom().getMonster() != null) {
            System.out.println(getCurrentRoom().getRoomDescription());
            System.out.println("You have encountered the following monsters in this room: " + getCurrentRoom().getMonster());
        }
        else {
            System.out.println(getCurrentRoom().getRoomDescription());
            System.out.println("You have found the following items in this room: " + getCurrentRoom().getRoomInventory());
            System.out.println("You have encountered the following monsters in this room: " + getCurrentRoom().getMonster());
        }
    }//end exploreRoom

    //Method to add an item to the player's inventory
    public void pickupPlayer(String itemName) {
        if (isInCombatMode()) {
            System.out.println("You cannot pick up items in combat mode!");
            return;
        }
        for (Item item : getCurrentRoom().getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                playerInventory.add(item);
                System.out.println("You have picked up " + item.getItemName() + " and added it to your inventory.");
                getCurrentRoom().getRoomInventory().remove(item);
                return;
            }
        }
        System.out.println("That item is not in this room.");
    }//end addItemToInventory

    //Method to remove an item from the player's inventory
    public void dropPlayer(String itemName) {
        if (isInCombatMode()) {
            System.out.println("You cannot drop items in combat mode!");
            return;
        }
        for (Item item : playerInventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                playerInventory.remove(item);
                System.out.println("You have dropped " + item.getItemName() + " from your inventory.");
                getCurrentRoom().getRoomInventory().add(item);
                return;
            }
        }
        System.out.println("That item is not in your inventory.");
    }//end removeItemFromInventory

    //Method to display the player's inventory
    public void displayInventory() {
        if (playerInventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        }
        else {
            System.out.println("Your inventory contains: " + playerInventory + "\n");
        }
    }//end displayInventory

    //Method to solve a puzzle
    public void solvePuzzle(String answer, Puzzle puzzle) throws InterruptedException {
        if (answer.equalsIgnoreCase(puzzle.getPuzzleAnswer())) {
            System.out.println("Congratulations! You have solved the puzzle.");
            getCurrentRoom().setPuzzle(null);
            puzzle.setSolved(true);
        }
        else {
            puzzle.setAttempts(puzzle.getAttempts() - 1);
            if (puzzle.getAttempts() > 0) {
                System.out.println("Incorrect answer. You have " + puzzle.getAttempts() + " attempt(s) left.");
                displayPuzzle();
            } else {
                System.out.println("Failed to solve the puzzle. Attempts exhausted.");
            }
        }
    }//end solvePuzzle

    //Method to display puzzle
    public void displayPuzzle() throws InterruptedException {
        Puzzle puzzle = getCurrentRoom().getPuzzle();
        System.out.println("You have encountered a puzzle: " + puzzle.getPuzzleName());
        System.out.println(puzzle.getPuzzleDescription() + "\n" + "Enter your answer: ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        solvePuzzle(answer, puzzle);
    }//end method

    //Method to inspect an item
    public void inspectItem(String itemName) {
        for (Item item : playerInventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getItemDescription());
                return;
            }
        }
        System.out.println("That item is not in your inventory.");
    }//end inspectItem

    //Method to equip an item
    public void equipItem(String itemName) {
        //iterate through the player's inventory
        for (Item item : playerInventory) {
            //check if the item is in the inventory
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                //check if the item is equipable
                if (item instanceof EquipableItem) {
                    EquipableItem equipableItem = (EquipableItem) item;
                    //check if the item is a weapon
                    if (equipableItem.getItemUtility().equalsIgnoreCase("weapon")) {
                        if (weapon == null) {
                            weapon = equipableItem;
                            playerInventory.remove(item);
                            setPlayerAttack(getPlayerAttack() + weapon.getItemStrength());
                            System.out.println("You brandish the " + weapon.getItemName() + " with such flair that even the monsters pause to applaud your fashionably deadly choice!");
                        }
                        else {
                            System.out.println("Unequip the current weapon before equipping a new one.");
                        }
                    }
                    //check if the item is armor
                    else if (equipableItem.getItemUtility().equalsIgnoreCase("armor")) {
                        if (armor == null) {
                            armor = equipableItem;
                            playerInventory.remove(item);
                            setPlayerMaxHealth(getPlayerMaxHealth() + armor.getItemStrength());
                            System.out.println(armor.getItemName() + " equipped: now ready for battle or an impromptu medieval fashion show!");
                        }
                        else {
                            System.out.println("Unequip the current armor before equipping a new one.");
                        }
                    }
                }
                else {
                    System.out.println("That item is not equipable.");
                }
                return;
            }
        }
        System.out.println("That item is not in your inventory.");
    }//end equipItem

    //Method to unequip an item
    public void unequipItem(String itemName) {
        if (weapon != null && weapon.getItemName().equalsIgnoreCase(itemName)) {
            playerInventory.add(weapon);
            setPlayerAttack(getPlayerAttack() - weapon.getItemStrength());
            System.out.println("You have unequipped the: " + weapon.getItemName());
            weapon = null;
        }
        else if (armor != null && armor.getItemName().equalsIgnoreCase(itemName)) {
            playerInventory.add(armor);
            setPlayerMaxHealth(getPlayerMaxHealth() - armor.getItemStrength());
            System.out.println("You have unequipped the: " + armor.getItemName());
            armor = null;
        }
        else {
            System.out.println("That item is not equipped.");
        }
    }//end unequipItem

    //Method to check player status
    public void checkPlayerStatus() {
        System.out.println("Player Name: " + getPlayerName());
        System.out.println("Player Health: " + getPlayerHealth() + "/" + getPlayerMaxHealth());
        System.out.println("Player Attack: " + getPlayerAttack());
        System.out.println("Player Level: " + getPlayerLevel());
        System.out.println("Player Experience: " + playerExperience + "/" + playerMaxExperience);
        if (weapon != null) {
            System.out.println("Weapon: " + weapon.getItemName());
        }
        if (armor != null) {
            System.out.println("Armor: " + armor.getItemName());
        }
    }//end checkPlayerStatus

    //Method to consume items
    public void consumeItem(String itemName) {
        for (Item item : playerInventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                if (item instanceof ConsumableItem) {
                    ConsumableItem consumableItem = (ConsumableItem) item;
                    setPlayerHealth(getPlayerHealth() + consumableItem.getItemStrength());
                    System.out.println("You have consumed the " + consumableItem.getItemName() + " and gained " + consumableItem.getItemStrength() + " health.");
                    playerInventory.remove(item);
                    if (getPlayerHealth() > getPlayerMaxHealth()) {
                        setPlayerHealth(getPlayerMaxHealth());
                    }
                    return;
                }
                else {
                    System.out.println("That item is not consumable.");
                }
            }
        }
        System.out.println("That item is not in your inventory.");
    }//end consumeItem

    //Method to examine a monster
    public void examineMonster() {
        if (getCurrentRoom().getMonster() != null) {
            System.out.println(getCurrentRoom().getMonster().getMonsterDescription());
            System.out.println("You can attack or ignore the monster.");
        }
        else {
            //randomly generate a funny message
            Random random = new Random();
            String[] strings = {"You look around carefully, but alas, no monster! Maybe it's taking a coffee break somewhere.",
                    "Your keen investigative skills reveal... absolutely nothing! Congratulations, you've mastered the art of monster-spotting in an empty room!"
            };
            int index = random.nextInt(strings.length);
            System.out.println(strings[index]);
        }
    }//end examineMonster

    //Method to ignore a monster
    public void ignoreMonster() {
        if (isInCombatMode()) {
            System.out.println("You're already in combat mode. You can't ignore the monster now!1");
            return;
        }
        if (getCurrentRoom().getMonster() != null) {
            System.out.println("You have chosen to ignore the " + getCurrentRoom().getMonster().getMonsterName() + ".");
            getCurrentRoom().setMonster(null);
            //randomly generate a funny message
            Random random = new Random();
            String[] strings = {"Feeling neglected, the monster considers a career change to professional ghosting!",
                    "The monster contemplates starting a support group for ignored creatures in dungeons. Meetings are held in the 'No Attention Zone.",
                    "The monster is so touched by your indifference, it decides to leave the room and find a more appreciative audience."
            };
            int index = random.nextInt(strings.length);
            System.out.println(strings[index]);
        }
        else {
            System.out.println("You've successfully ignored the invisible monster in this room. Congratulations!");
        }
    }//end ignoreMonster

    //Method to attack a monster
    public void attackMonster(ArrayList<Item> listOfItems) {
        if (getCurrentRoom().getMonster() != null) {
            setInCombatMode(true);
            getCurrentRoom().getMonster().setMonsterHealth(getCurrentRoom().getMonster().getMonsterHealth() - getPlayerAttack());
            System.out.println("You have dealt " + getPlayerAttack() + " damage to the " + getCurrentRoom().getMonster().getMonsterName() + ".");
            if (getCurrentRoom().getMonster().getMonsterHealth() <= 0) {
                exitCombatMode();
                System.out.println("You have defeated the " + getCurrentRoom().getMonster().getMonsterName() + ".");
                playerExperience = playerExperience + getCurrentRoom().getMonster().getExpDrop();
                levelUp();
                getCurrentRoom().getMonster().dropItem(getCurrentRoom().getMonster(), this,listOfItems );
                getCurrentRoom().setMonster(null);
            }
            else {
                System.out.println(getCurrentRoom().getMonster().getMonsterName() + " health: " + getCurrentRoom().getMonster().getMonsterHealth());
                getCurrentRoom().getMonster().attackPlayer(this);
            }
            if (getPlayerHealth() <= 0) {
                exitCombatMode();
                System.out.println("You have been defeated by the " + getCurrentRoom().getMonster().getMonsterName() + ".");
            }
            else {
                System.out.println("Player Health: " + getPlayerHealth() + "/" + getPlayerMaxHealth());
            }
        }
        else {
            Random random = new Random();
            String[] strings = {"Congratulations, you've successfully defended this room against the invisible monster invasion!",
                    "The monster union called a strike in this room; they're demanding better dungeon conditions!",
                    "Plot twist: you are the monster, and this room is just a mirror maze of self-realization!"
            };
            int index = random.nextInt(strings.length);
            System.out.println(strings[index]);
        }
    }//end attackMonster

    //Method to exit combat mode
    public void exitCombatMode() {
        setInCombatMode(false);
    }//end exitCombatMode

    //Method to level up the player
    public void levelUp() {
        if (playerExperience >= playerMaxExperience && playerLevel < 5) {
            playerLevel++;
            playerExperience = playerExperience - playerMaxExperience;
            playerMaxExperience = playerMaxExperience + 100;
            setPlayerMaxHealth(getPlayerMaxHealth() + 20);
            setPlayerHealth(getPlayerMaxHealth());
            setPlayerAttack(getPlayerAttack() + 10);
            System.out.println("Congratulations! You have leveled up to level " + playerLevel + "!");
        }
        else if (playerLevel == 5) {
            System.out.println("Congratulations on reaching the pinnacle of hilarity! You've unlocked the 'Master of Mirth' achievement.\n Now, go forth and spread joy and laughter throughout the dungeon!");
        }
    }//end levelUp

}//end Player