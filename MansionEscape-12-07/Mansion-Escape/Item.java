
/**
 * Creates an item that will be used in the game.
 *
 * @author (Micale Miller)
 * @version (11/23/25)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weight;
    private String description;
    private boolean held;
    private String secondDecription; //Used to give hints to the player
    private String usable;
    private boolean edible;


    /**
     * Constructor for objects of class Item
     * @param description The description of the item
     */
    public Item(String description)
    {
        this.description = description;
        weight = 1;
        held = false;
    }
    
    /**
     * Determines if the item is being held
     * @param held If the item is held or not
     */
    public void setHeld(boolean held) {
        this.held = held;
    }
    
    /**
     * Retruns true if an item is currently held, false otherwise
     * @return If an item is held or not
    */
    public boolean getHeld()
    {
        return held;
    }
    
    /**
     * Determines if the item is being held
     * @param edible If the item is edible
     */
    public void setEdible(boolean edible) {
        this.edible = edible;
    }
    
    /**
     * Retruns true if an item is edible, false otherwise
     * @return If an item can be consumed or not
    */
    public boolean getEdible()
    {
        return edible;
    }
    
    /**
     * Set weight of item.
     * @param weight The weight you want the item to be
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Retruns the weight of the item.
     * @return The weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Retruns the description of the item.
     * @return The description of the item.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Returns the secondary description an item may have
     * @return the secondary description an item may have
     */
    public String getsecondDescription()
    {
        return secondDecription;
    }
    
    /**
     * Gives an item a secondary description
     * @param s The secondary description of an item
     */
    public void setSecondDesciption(String s)
    {
         secondDecription = s;
    }
    
    /**
     * Gives an item a description
     * @param s The description of an item
     */
    public void setDescription(String s)
    {
         description = s;
    }
    
    /**
     * Returns where the item is usable at
     * @return where the item is usable at
     */
    public String getUsableRoom()
    {
        return usable;
    }
    
    /**
     * Gives an item a place to be used
     * @param s The room the item can be used in
     */
    public void setUsableRoom(String s)
    {
         usable = s;
    }
}