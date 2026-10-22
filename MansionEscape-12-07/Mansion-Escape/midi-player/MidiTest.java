

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MidiTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MidiTest
{
    private MidiPlayer midiPlay1;
    private SimpleTune simpleTu1;

    /**
     * Default constructor for test class MidiTest
     */
    public MidiTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        int w = 6;
        int h = w/2;
        int q = h/2;
        midiPlay1 = new MidiPlayer();
        midiPlay1.showInstruments();
        // midiPlay1.playNote("C", w);
        // midiPlay1.playNote("D", q);
        // midiPlay1.playNote("E", w);
        // midiPlay1.playNote("C", q);
        // midiPlay1.playNote("E", w);
        // midiPlay1.playNote("C", q);
        // midiPlay1.playNote("E", w);
        simpleTu1 = new SimpleTune();
        // simpleTu1.addNote("C", 12);
        simpleTu1.addNote("C", w);
        simpleTu1.addNote("D", q);
        simpleTu1.addNote("E", w);
        simpleTu1.addNote("C", q);
        simpleTu1.addNote("E", w);
        simpleTu1.addNote("C", q);
        simpleTu1.addNote("E", w);
        midiPlay1.playTune(simpleTu1);
        simpleTu1.showNotes();
        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
