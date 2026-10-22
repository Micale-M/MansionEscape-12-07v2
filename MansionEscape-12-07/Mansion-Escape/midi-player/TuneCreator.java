
/**
 * Write a description of class TuneCreator here.
 *
 * @author (Micale Miller)
 * @version (9/20/25)
 */
public class TuneCreator//3.46
{
    // instance variables - replace the example below with your own
    private MidiPlayer player;

    /**
     * Constructor for objects of class TuneCreator
     */
    public TuneCreator()//3.47
    {
        // initialise instance variables
        player = new MidiPlayer();
    }
    
    /**
     *
     */
    public void createAndPlay()//3.48
    {
        SimpleTune Tune = new SimpleTune();//3.49
        Tune.addNote("D4", 1);
        Tune.addNote("F4", 1);
        Tune.addNote("Bb4", 1);
        Tune.addNote("D5", 1);
        Tune.addNote("D5", 1);
        Tune.addNote("Bb4", 1);
        Tune.addNote("C5", 2);
        Tune.addNote("Eb5", 2);
        Tune.addNote("D5", 2);
        Tune.addNote("Bb4", 2);
        Tune.addNote("Bb3", 1);
        Tune.addNote("D4", 1);
        Tune.addNote("F4", 1);
        Tune.addNote("Bb4", 1);
        Tune.addNote("Bb4", 1);
        Tune.addNote("F4", 1);
        Tune.addNote("A4", 2);
        Tune.addNote("C5", 2);
        Tune.addNote("Bb4", 2);
        Tune.setInstrument(12);//3.50
        player.playTune(Tune);
    }
}