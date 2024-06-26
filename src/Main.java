import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // File paths for game elements
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String MAP_FILE_PATH = "src/Rooms.txt";
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String MONSTERS_FILE_PATH = "src/Monsters.txt";

    // Array List to store game elements
    private static final ArrayList<Room> listOfRooms = new ArrayList<>();
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Monster> listOfMonsters = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Game.launch(Game.class, args);

        loadGameElements();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = scanner.nextLine();

        Room startingRoom = listOfRooms.get(0);
        Player mainCharacter = new Player(100, 10, playerName, startingRoom);
        startingRoom.setVisited(true);

        displayStory();

        playGame(mainCharacter);
    }

    //Method to load game elements
    private static void loadGameElements() throws InterruptedException {
        System.out.println("\n...Loading game elements...\n");
        Thread.sleep(1000);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Monster.readMonsters(MONSTERS_FILE_PATH, listOfMonsters);
        Room.readRooms(MAP_FILE_PATH, listOfRooms, listOfPuzzles, listOfMonsters);
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Room.addItemsToRoom(listOfItems, listOfRooms);


        System.out.println("Game elements loaded successfully!\n");
        Thread.sleep(1000);
    }

    //Method to play the game
    private static void playGame(Player player) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("What do you want to do next? (Type 'Help' for commands)");
            userInput = scanner.nextLine();

            switch (userInput.toLowerCase()) {
                case "attack":
                    player.attackMonster(listOfItems);
                    gameOver(player);
                    break;
                case "ignore":
                    player.ignoreMonster();
                    break;
                case "examine":
                    player.examineMonster();
                    break;
                case "consume":
                    System.out.println("Which item would you like to consume?");
                    String itemToConsume = scanner.nextLine();
                    player.consumeItem(itemToConsume);
                    break;
                case "use" :
                    System.out.println("Which item would you like to use?");
                    String itemToUse = scanner.nextLine();
                    player.teleportation(itemToUse, listOfRooms);
                case "status":
                    player.checkPlayerStatus();
                    break;
                case "unequip":
                    System.out.println("Which item would you like to unequip?");
                    String ItemToUnequip = scanner.nextLine();
                    player.unequipItem(ItemToUnequip);
                    break;
                case "equip":
                    System.out.println("Which item would you like to equip?");
                    String ItemToEquip = scanner.nextLine();
                    player.equipItem(ItemToEquip);
                    break;
                case "exit":
                    System.out.println("Game Over. Thanks for playing!");
                    System.exit(0);
                    break;
                case "inspect":
                    System.out.println("Which item would you like to inspect?");
                    String itemToInspect = scanner.nextLine();
                    player.inspectItem(itemToInspect);
                    break;
                case "explore":
                    player.exploreRoom();
                    break;
                case "inventory":
                    player.displayInventory();
                    break;
                case "pickup":
                    System.out.println("Which item would you like to pick up?");
                    String itemToPickup = scanner.nextLine();
                    player.pickupPlayer(itemToPickup);
                    break;
                case "drop":
                    System.out.println("Which item would you like to drop?");
                    String itemToDrop = scanner.nextLine();
                    player.dropPlayer(itemToDrop);
                    break;
                case "help":
                    displayHelp();
                    break;
                default:
                    player.navigate(userInput, listOfRooms);
            }
        }
    }

    //Method to start a new game
    private static void startNewGame() throws InterruptedException {
        System.out.println("...Starting a new game...");
        Thread.sleep(1000);

        // Reload or reset game elements and state
        listOfRooms.clear();
        listOfItems.clear();
        listOfPuzzles.clear();
        listOfMonsters.clear();
        loadGameElements();

        Room startingRoom = listOfRooms.get(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = scanner.nextLine();
        Player mainCharacter = new Player(100, 10, playerName, startingRoom); // Reset player with default values
        startingRoom.setVisited(true);

        System.out.println("New game started!");
        displayStory();
        playGame(mainCharacter); // Start the new game
    }

    //Method to display help
    private static void displayHelp() {
        System.out.println("Commands:");
        System.out.println("N - Move North");
        System.out.println("S - Move South");
        System.out.println("E - Move East");
        System.out.println("W - Move West");
        System.out.println("Attack - Attack a monster");
        System.out.println("Consume - Consume an item");
        System.out.println("Status - Display player status");
        System.out.println("Equip - Equip an item");
        System.out.println("Unequip - Unequip an item");
        System.out.println("Inspect - Inspect an item");
        System.out.println("Examine - Examine a monster");
        System.out.println("Ignore - Ignore a monster");
        System.out.println("Explore - Explore the current room");
        System.out.println("Inventory - Display player inventory");
        System.out.println("Pickup - Pick up an item");
        System.out.println("Drop - Drop an item");
        System.out.println("Help - Display this help message");
        System.out.println("Exit - Quit the game");
    }

    //Method for game over
    public static void gameOver(Player player) throws InterruptedException {
        if (player.getPlayerHealth() <= 0) {
            System.out.println("Game Over. You have died.");
            newGame();
        }
    }//end gameOver

    //Method for new game
    public static void newGame() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'new game' to start a new game or 'exit' to stop playing.");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("new game") && !input.equalsIgnoreCase("exit")) {
            System.out.println("Invalid input. Please enter 'new game' or 'exit'.");
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("new game")) {
            startNewGame();
        }
        else if (input.equalsIgnoreCase("exit")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }

    //Method to check if the player won the game
    public static void checkWin(Player player) {
        for (Item item : player.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase("Scepter of Silliness")) {
                System.out.println("Congratulations! You have won the game!");
                System.exit(0);
            }
        }
    }

    //Method to display the story
    public static String displayStory() {
        String message = "\n\n               Welcome to Dungeon Riser, where dungeons are twistier than a pretzel and monsters have a better sense of humor than most comedians! \n"
        + "     You, dear adventurer, are not your typical hero. No chiseled jawline or flowing cape for you—just a knack for stumbling into trouble and a heart full of \n"
        + "     laughter. It all begins when you, the accidental hero, receive a quest from the town's mayor, who mistakes your enthusiastic nodding for a sign of bravery.\n"
        + "               Your mission? Venture into the notoriously comedic Dungeon of Delirium and retrieve the legendary 'Scepter of Silliness.'\n";

        String message2 = "\n\n               Armed with nothing but your wits and a sense of humor (because every adventurer needs a punchline), you enter the dungeon\n"
        + "     filled with perplexing puzzles, pun-loving monsters, and treasures that are worth more in laughs than in gold. Can you outwit the dungeon's comedic traps,\n"
        + "     defeat the pun-loving monsters, and emerge as the legendary champion of chuckles? It's time to embark on the solo comedy quest of a lifetime in Dungeon Riser!\n";

        String message3 = "\n\n               Armed with your courage and a slightly questionable sense of humor, you step into the Dungeon of Delirium.\n"
        + "     The air is thick with anticipation, or maybe that's just the lingering scent of the last adventurer's failed punchlines. As you take\n"
        + "     your first cautious steps, the echo of a mischievous giggle dances through the corridors, reminding you that this is no ordinary dungeon.\n";

        for (char ch : message.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        for (char ch : message2.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        return message;
    }

}