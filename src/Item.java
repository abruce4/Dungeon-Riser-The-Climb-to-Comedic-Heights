import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {

    //Items attributes
    private String itemType;
    private int itemID;
    private String itemName;
    private String itemDescription;
    private int itemRoomID;

    //Constructor and Initialization of attributes
    public Item(String itemType, int itemID, String itemName, String itemDescription, int itemRoomID) {
        this.itemType = itemType;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemRoomID = itemRoomID;
    }

    //Getters and Setters
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemRoomID() {
        return itemRoomID;
    }

    public void setItemRoomID(int itemRoomID) {
        this.itemRoomID = itemRoomID;
    }

    //toString method
    @Override
    public String toString() {
        return itemName;
    }

    //Method to read items from the file
    public static void readItems(String filePath, ArrayList<Item> listOfItems) {
        try {
            File myItems = new File(filePath);
            Scanner myReader = new Scanner(myItems);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("-");
                String itemType = parts[0].trim();
                int itemID = Integer.parseInt(parts[1].trim());
                String itemName = parts[2].trim();
                String itemDescription = parts[3].trim();
                int itemRoomID = Integer.parseInt(parts[4].trim());

                if(itemType.equalsIgnoreCase("usable")) {
                    Item item = new Item(itemType,itemID, itemName, itemDescription, itemRoomID);
                    listOfItems.add(item);
                }
                else if (itemType.equalsIgnoreCase("consumable")) {
                    int itemStrength = Integer.parseInt(parts[5].trim());
                    String itemUtility = parts[6].trim();
                    ConsumableItem item = new ConsumableItem(itemType,itemID, itemName, itemDescription, itemRoomID, itemStrength, itemUtility);
                    listOfItems.add(item);
                }
                else if (itemType.equalsIgnoreCase("equipable")) {
                    int itemStrength = Integer.parseInt(parts[5].trim());
                    String itemUtility = parts[6].trim();
                    EquipableItem item = new EquipableItem(itemType,itemID, itemName, itemDescription, itemRoomID, itemStrength, itemUtility);
                    listOfItems.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the items file.");
        }
    }//end readItems

}//end Item