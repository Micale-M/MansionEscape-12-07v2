import java.util.ArrayList;

/**
 * A player that you control in the game.
 *
 * @author (Micale Miller)
 * @version (11/24/25)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int weightLimit;
    private int health;
    private int weight;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        inventory = new ArrayList<>();
        weightLimit = 1;
        weight = 0;
        health = 3;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void addHealth(){
        health++;
    }
    
    public void minusHealth(){
        health = health - 1;
    }

    public Room getRoom() {
        return currentRoom;
    }
    
    public void setRoom(Room r) {
        currentRoom = r;
    }
    
    public void addItem(Item item){
        inventory.add(item);
    }
    
    public Item getItem(String item){
        for(Item i: inventory) {
            if(i.getDescription().contains(item)){
                return i;
            }
        }
        return null;
    }
    
    public String viewInventory(){
        String l = "You have ";
        for(Item i: inventory) {
            l += (i.getDescription() + "\n");
        }
        if(l.equals("You have ")){
            l += ("nothing");
        }
        // } else {
            // l = l.substring(0, l.length() - 2);
        // }
        return l;
        }
    
    
    public void removeItem(Item item){
        inventory.remove(item);
    }
    
    public int getWeight() {
        weight = 0;
        for(Item i: inventory) {
            weight += i.getWeight();
        }
        return weight;
    }
    
    public int getWeightLimit() {
        return weightLimit;
    }
    
    public void setWeightLimit(int num) {
        weightLimit = num;
    }
    
    public void setWeight(int num) {
        weight = num;
    }
}