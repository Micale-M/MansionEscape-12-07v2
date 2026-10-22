import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.*;

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
    //private Set<String> pianoNotes;
    //private ArrayList<Integer> history;
    private ArrayList<String> pianoNotes;
    private SimpleTune PianoTune;
    private SimpleTune TestTune;

    /**
     * Constructor for objects of class TuneCreator
     */
    public TuneCreator()//3.47
    {
        // initialise instance variables
        player = new MidiPlayer();
        //pianoNotes = new Set<>(); 
        //{"Db", "Eb", "Gb", "Ab", "Bb"};
        PianoTune = new SimpleTune();
        TestTune = new SimpleTune();

        // history = new ArrayList<>();
        pianoNotes = new ArrayList<>();
        // pianoNotes.add("Db");
        // pianoNotes.add("Eb");
        // pianoNotes.add("Gb");
        // pianoNotes.add("Ab");
        // pianoNotes.add("Bb");
    }
    
    public void setPianoNotes() {
        if(pianoNotes.isEmpty() == true) {
            pianoNotes.add("Db");
            pianoNotes.add("Eb");
            pianoNotes.add("Gb");
            pianoNotes.add("Ab");
            pianoNotes.add("Bb");
            Collections.shuffle(pianoNotes);
             for(String s: pianoNotes) {
                PianoTune.addNote(s, 2);
            }
        }
    }
    
    public String getPianoNotes(){
        String t = "";
        for(Note s: PianoTune.getNotes()) {
            t += s.getNoteName() + " ";
        }
        return t;
        //PianoTune.getNotes();
    }
    
    public String getTestTuneNotes(){
        String t = "";
        for(Note s: TestTune.getNotes()) {
            t += s.getNoteName() + " ";
        }
        return t;
        //PianoTune.getNotes();
    }
    
    /**
     *
     */
    public void createAndPlay(String... notes)//3.48
    {
        TestTune.clear();
        for(int i = 0; i < notes.length; i++) {
            TestTune.addNote(notes[i], 2);
        }
        TestTune.setInstrument(5);//3.50
        player.playTune(TestTune);
    }
    
    public boolean Same(){
        if(getPianoNotes().equals(getTestTuneNotes())){
            return true;
        } else { 
            return false;
        }
    }
}