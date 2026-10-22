import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * GameTest - a class to test the zuul game engine.
 *
 * @author  William H. Hooper
 * @version 2018-11-19
 */
public class GameTest
{
    private Game game1;
    private Console console1;
    // private ZuulApplet zApp1;
    private ZuulGUI zApp1;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("\f");
        game1 = new Game();
        console1 = new Console(game1);
        String message = game1.readMessages();
        System.out.print(message + "> ");
        // zApp1 = new ZuulApplet();
        zApp1 = new ZuulGUI();
        zApp1.begin();
    }

    @Test
    public void starts()
    {
        assertEquals(false, game1.finished());
    }


    @Test
    public void quit()
    {
        console1.testCommand("quit");
        assertEquals(true, game1.finished());
        assertEquals(false, console1.testCommand("go east", "foyer"));
        assertEquals(true, console1.testCommand("anything", "game is over"));
    }
    
    @Test
    public void help()
    {
        String string1 = console1.getResponse("help");
        assertNotNull(string1);
        assertEquals(true, string1.contains("go"));
        assertEquals(true, string1.contains("quit"));
        assertEquals(true, string1.contains("help"));
        assertEquals(true, string1.contains("look"));
        assertEquals(true, string1.contains("back"));
    }
    
    @Test
    public void noDoor()
    {
        assertEquals(true, console1.testCommand("go north", "no door!"));
    }

    @Test
    public void map()
    {
        assertEquals(true, zApp1.testCommand("go east", "foyer"));
        assertEquals(true, zApp1.testCommand("go north", "mechanical"));
        assertEquals(true, zApp1.testCommand("go south", "foyer"));
        assertEquals(true, zApp1.testCommand("go south", "cellar"));
        assertEquals(true, zApp1.testCommand("go north", "foyer"));
        assertEquals(true, zApp1.testCommand("go up", "ground floor"));
        assertEquals(true, zApp1.testCommand("go south", "chairs"));
        assertEquals(true, zApp1.testCommand("go west", "pantry"));
        assertEquals(true, zApp1.testCommand("go west", "kitchen"));
        assertEquals(true, zApp1.testCommand("go north", "flowers"));
        assertEquals(true, zApp1.testCommand("go north", "living room"));
        assertEquals(true, zApp1.testCommand("go east", "library"));
        assertEquals(true, zApp1.testCommand("go west", "living room"));
        assertEquals(true, zApp1.testCommand("go southeast", "ground floor"));
        assertEquals(true, zApp1.testCommand("go up", "first floor"));
        assertEquals(true, zApp1.testCommand("go south", "master bedroom"));
        assertEquals(true, zApp1.testCommand("go west", "hall"));
        assertEquals(true, zApp1.testCommand("go west", "moldy"));
        assertEquals(true, zApp1.testCommand("go east", "hall"));
        assertEquals(true, zApp1.testCommand("go south", "closet"));
        assertEquals(true, zApp1.testCommand("go north", "hall"));
        assertEquals(true, zApp1.testCommand("go east", "master bedroom"));
        assertEquals(true, zApp1.testCommand("go north", "first floor"));
        assertEquals(true, zApp1.testCommand("go north", "another hall"));
        assertEquals(true, zApp1.testCommand("go northwest", "bedroom"));
        assertEquals(true, zApp1.testCommand("go west", "cupboard"));
        assertEquals(true, zApp1.testCommand("go east", "bedroom"));
        assertEquals(true, zApp1.testCommand("go south", "another"));
        assertEquals(true, zApp1.testCommand("go northeast", "bathroom"));
        assertEquals(true, zApp1.testCommand("go east", "tub"));
        zApp1.testCommand("go west");
        zApp1.testCommand("go south");
        zApp1.testCommand("go south");
        zApp1.testCommand("go down");
        zApp1.testCommand("go north");
        assertEquals(true, zApp1.testCommand("go north", "front door"));
    }

    @Test
    public void items()
    {
        assertEquals(true, zApp1.testCommand("go east", "foyer"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go north", "mechanical"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go south", "foyer"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go south", "cellar"));
        assertEquals(true, zApp1.testCommand("look", "wine"));
        assertEquals(true, zApp1.testCommand("go north", "foyer"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go up", "ground floor"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go south", "chairs"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go west", "pantry"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go west", "kitchen"));
        assertEquals(true, zApp1.testCommand("look", "apple"));
        assertEquals(true, zApp1.testCommand("go north", "flowers"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go north", "living room"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go east", "library"));
        assertEquals(true, zApp1.testCommand("look", "poem"));
        assertEquals(true, zApp1.testCommand("go west", "living room"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go southeast", "ground floor"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go up", "first floor"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go south", "master bedroom"));
        assertEquals(true, zApp1.testCommand("look", "diary"));
        assertEquals(true, zApp1.testCommand("go west", "hall"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go west", "moldy"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go east", "hall"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go south", "closet"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go north", "hall"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go east", "master bedroom"));
        assertEquals(true, zApp1.testCommand("go north", "first floor"));
        assertEquals(true, zApp1.testCommand("go north", "another hall"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go northwest", "bedroom"));
        assertEquals(true, zApp1.testCommand("look", "backpack"));
        assertEquals(true, zApp1.testCommand("go west", "cupboard"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go east", "bedroom"));
        assertEquals(true, zApp1.testCommand("go south", "another"));
        assertEquals(true, zApp1.testCommand("go northeast", "bathroom"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        assertEquals(true, zApp1.testCommand("go east", "tub"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
        zApp1.testCommand("go west");
        zApp1.testCommand("go south");
        zApp1.testCommand("go south");
        zApp1.testCommand("go down");
        zApp1.testCommand("go north");
        assertEquals(true, zApp1.testCommand("go north", "front door"));
        assertEquals(true, zApp1.testCommand("look", "nothing"));
    }

    @Test
    public void back()
    {
        zApp1.testCommand("go east");
        zApp1.testCommand("go north");
        zApp1.testCommand("go south");
        zApp1.testCommand("go south");
        zApp1.testCommand("go north");
        zApp1.testCommand("go up");
        zApp1.testCommand("go south");
        zApp1.testCommand("go west");
        zApp1.testCommand("go west");
        zApp1.testCommand("go north");
        zApp1.testCommand("go north");
        zApp1.testCommand("go east");
        zApp1.testCommand("go west");
        zApp1.testCommand("go southeast");
        zApp1.testCommand("go up");
        zApp1.testCommand("go south");
        zApp1.testCommand("go west");
        zApp1.testCommand("go west");
        zApp1.testCommand("go east");
        zApp1.testCommand("go south");
        zApp1.testCommand("go north");
        zApp1.testCommand("go east");
        zApp1.testCommand("go north");
        zApp1.testCommand("go north");
        zApp1.testCommand("go northwest");
        zApp1.testCommand("go west");
        zApp1.testCommand("go east");
        zApp1.testCommand("go south");
        zApp1.testCommand("go northeast");
        zApp1.testCommand("go east");
        zApp1.testCommand("go west");
        zApp1.testCommand("go south");
        zApp1.testCommand("go south");
        zApp1.testCommand("go down");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        zApp1.testCommand("back");
        assertEquals(true, zApp1.testCommand("back", "cell"));
        assertEquals(true, zApp1.testCommand("back", "anymore"));
    }
}










