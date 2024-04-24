import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Monster {

    //Monster attributes
    private String monsterName;
    private String monsterDescription;
    private int monsterHealth;
    private int monsterDamage;
    private int monsterDoubleDamage;
    private int threshold;
    private int monsterRoomID;
    private int expDrop;
    private int itemDrop;

    //Constructor and Initialization of attributes
    public Monster(String monsterName, String monsterDescription, int monsterHealth, int monsterDamage, int monsterDoubleDamage, int threshold, int monsterRoomID, int expDrop, int itemDrop) {
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
        this.monsterHealth = monsterHealth;
        this.monsterDamage = monsterDamage;
        this.monsterDoubleDamage = monsterDoubleDamage;
        this.threshold = threshold;
        this.monsterRoomID = monsterRoomID;
        this.expDrop = expDrop;
        this.itemDrop = itemDrop;
    }

    //Getters and Setters
    public String getMonsterName() {
        return monsterName;
    }
    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getMonsterDescription() {
        return monsterDescription;
    }
    public void setMonsterDescription(String monsterDescription) {
        this.monsterDescription = monsterDescription;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }
    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public int getMonsterDamage() {
        return monsterDamage;
    }
    public void setMonsterDamage(int monsterDamage) {
        this.monsterDamage = monsterDamage;
    }

    public int getMonsterDoubleDamage() {
        return monsterDoubleDamage;
    }
    public void setMonsterDoubleDamage(int monsterDoubleDamage) {
        this.monsterDoubleDamage = monsterDoubleDamage;
    }

    public int getThreshold() {
        return threshold;
    }
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getMonsterRoomID() {
        return monsterRoomID;
    }
    public void setMonsterRoomID(int monsterRoomID) {
        this.monsterRoomID = monsterRoomID;
    }

    public int getExpDrop() {
        return expDrop;
    }
    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    public int getItemDrop() {
        return itemDrop;
    }

    public void setItemDrop(int itemDrop) {
        this.itemDrop = itemDrop;
    }

    //toString method
    @Override
    public String toString() {
        return monsterName;
    }

    //Method to read monsters from the file
    public static void readMonsters(String filePath, ArrayList<Monster> listOfMonsters) {
        try {
            File myMonsters = new File(filePath);
            Scanner myReader = new Scanner(myMonsters);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] monsterData = data.split("-");
                String monsterName = monsterData[0].trim();
                String monsterDescription = monsterData[1].trim();
                int monsterHealth = Integer.parseInt(monsterData[2].trim());
                int monsterDamage = Integer.parseInt(monsterData[3].trim());
                int monsterDoubleDamage = Integer.parseInt(monsterData[4].trim());
                int threshold = Integer.parseInt(monsterData[5].trim());
                int monsterRoomID = Integer.parseInt(monsterData[6].trim());
                int expDrop = Integer.parseInt(monsterData[7].trim());
                int itemDrop = Integer.parseInt(monsterData[8].trim());
                Monster monster = new Monster(monsterName, monsterDescription, monsterHealth, monsterDamage, monsterDoubleDamage, threshold, monsterRoomID, expDrop, itemDrop);
                listOfMonsters.add(monster);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the monsters file.");
        }
    }

    //Method to attack a player
    public void attackPlayer(Player player) {
        //generate a random number between 1 and 30, if the number is below the threshold, the monster will deal double damage
        int random = (int) (Math.random() * 30 + 1);
        if (random <= threshold) {
            player.setPlayerHealth(player.getPlayerHealth() - monsterDoubleDamage);
            System.out.println("The " + getMonsterName() + " dealt " + monsterDoubleDamage + " damage to you!");
        }
        else {
            player.setPlayerHealth(player.getPlayerHealth() - monsterDamage);
            System.out.println("The " + getMonsterName() + " dealt " + monsterDamage + " damage to you!");
        }
    }

    //Method for monster to drop an item
    public void dropItem(Monster monster, Player player, ArrayList<Item> listOfItems) {
        for (Item item : listOfItems) {
            if (item.getItemID() == monster.getItemDrop() && monster.getItemDrop() != 0) {
                player.getPlayerInventory().add(item);
                System.out.println("The " + monster.getMonsterName() + " dropped a " + item.getItemName() + "!");
            }
        }
    }
}