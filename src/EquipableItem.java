public class EquipableItem extends Item{
    private int itemStrength;
    private String itemUtility;

    public EquipableItem(String itemType, int itemID, String itemName, String itemDescription, int itemRoomID, int itemStrength, String itemUtility) {
        super(itemType, itemID, itemName, itemDescription, itemRoomID);
        this.itemStrength = itemStrength;
        this.itemUtility = itemUtility;
    }

    public int getItemStrength() {
        return itemStrength;
    }

    public void setItemStrength(int itemStrength) {
        this.itemStrength = itemStrength;
    }

    public String getItemUtility() {
        return itemUtility;
    }

    public void setItemUtility(String itemUtility) {
        this.itemUtility = itemUtility;
    }
}