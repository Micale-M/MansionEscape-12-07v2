import java.util.TreeMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */

public class CommandWords
{
    // A constant array that holds all valid command words.
    private static final String[] validCommands = {
    "go", "quit", "help", "look", "back", "drop", "listen", "inspect", "inventory", "take", "drop", "use", "piano", "keypad", "eat"
    };
    private TreeMap<String, String> validCommandsList;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommandsList = new TreeMap<>();
        validCommandsList.put("go", "A two word command, starting with \"go\" and ending with the direction you want to move in to traverse rooms");
        validCommandsList.put("quit", "Ends the game");
        validCommandsList.put("help", "Provides a list of all the commands and their functions/use");
        validCommandsList.put("look", "Allows you to look around the room further");
        validCommandsList.put("back", "Retrace your steps all the way to the beginning");
        validCommandsList.put("take", "A two word command, starting with \"take\" and ending with the item you want to pick up");
        validCommandsList.put("drop", "A two word command, starting with \"drop\" and ending with the item you want to drop");
        validCommandsList.put("inventory", "Allows you to look at all the items you're carrying");
        validCommandsList.put("inspect", "Allows you to inspect an item further, maybe for clues");
        validCommandsList.put("use", "Allows you to use an item, only when it is usable though");
        validCommandsList.put("piano", 
        """
        Allows you to play the piano when you are in the same room as it.
        Just type piano followed by some notes, ex. (piano C,D,Db) """);
        validCommandsList.put("keypad", 
        """
        Allows you to use the keypad when you are in the same room as it.
        Just type keypad followed by some numbers, ex. (keypad 123) """);
        validCommandsList.put("eat", "Allows you to eat an edible item, rejuvenating the body");
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString)) {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Print all valid commands to System.out.
     */
    public String showAll()
    {
        String s = "";
        for(String c: validCommandsList.keySet()) {
            s += c + ": " + validCommandsList.get(c) + "\n";
        }
        return s;
    }
    
}
