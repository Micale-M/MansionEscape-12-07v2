import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Room 
{
    public String description;
    private String imageName;
    private String audioName;
    private ArrayList<Item> items;
    
    public HashMap<String, Room> exits;
    
    // public Room northExit;
    // public Room southExit;
    // public Room eastExit;
    // public Room westExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        this.setAudio("StockTune-Whispers In The Shadows_1765115016.mp3");
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    //8.8
    /**
     * Define an exit from this room.   
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    //public void setExits(Room north, Room east, Room south, Room west) 
    public void setExit(String direction, Room neighbor)
    {
        // if(north != null) {
            // northExit = north;
        // }
        // if(east != null) {
            // eastExit = east;
        // }
        // if(south != null) {
            // southExit = south;
        // }
        // if(west != null) {
            // westExit = west;
        // }
        exits.put(direction, neighbor);
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction the Exit's direction.
     * @return The room in the given direction.
    */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString() {
        String es = "Exits:";
        for(String s: exits.keySet()) {
            es += " " + s;
        }
        return es;
    }
    
    public String getLongDescription() {
        String s = "You are " + description + "\n" +
        getExitString();
        return s;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /*************************************************************
     * added by William H. Hooper, 2006-11-28
    *************************************************************/
    /**
     * @return a String, which hopefully contains the file name
     * of an Image file.
     */
    public String getImage()
    {
        return imageName;
    }
    
    /**
     * associate an image with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format viewable in the Java AWT.
     * Readable formats include: 
     * PNG, JPG (RGB color scheme only), GIF
     */
    public void setImage(String filename)
    {
        imageName = "media/" + filename;
    }
    
    /**
     * @return a String, which hopefully contains the file name
     * of an audio file.
     */
    public String getAudio()
    {
        return audioName;
    }
    
    /**
     * associate an audio clip with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format palyable in the Java AWT.
     * Readable formats include: 
     * WAV, AU.
     */
    public void setAudio(String filename)
    {
        audioName = "media/" + filename;
    }
    
    public void addItem(Item item){
        items.add(item);
    }
    
    public Item getItem(String item){
        for(Item i: items) {
            if(i.getDescription().contains(item)){
                return i;
            }
        }
        return null;
    }
    
    public void removeItem(Item item){
        items.remove(item);
    }

    public String getItemString(){
        String s = "";
        for(Item i: items){
            s += (i.getDescription() + ", ");
        }
        if(!(s.equals(""))) {
            s = s.substring(0, s.length() - 2);
        }
        if(s.equals("")){
            s = ("nothing that seems to be of use");
        }
        return s;
    }
}
