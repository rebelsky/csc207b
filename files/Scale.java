package edu.grinnell.sortingvisualizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Scale {
    /** The (MIDI) note values of this scale. */
    private int[] notes;
    
    private static final int REGULAR_VELOCITY = 60;
    private static final int EMPHASIZED_VELOCITY = 120;
    
    ///// Initialization for the Midi sub-system. /////
    @SuppressWarnings("unused")
    private static Synthesizer synth;
    private static MidiChannel instrument;
    static {
        Synthesizer synth;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            instrument = synth.getChannels()[0];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * @param notes the (MIDI) note values of this scale, assumed to be
     *        in ascending order
     */
    public Scale(int[] notes) { this.notes = notes; }
    
    /**
     * @return the number of notes in the scale
     */
    public int size() { return notes.length; }
    
    /**
     * Plays a note of the scale through Swing's MIDI library.
     * @param index the index of the note to play within the scale
     * @param highlighted true if this note be emphasized
     */
    public void playNote(int index, boolean emphasized) {
        instrument.noteOn(notes[index], emphasized ? EMPHASIZED_VELOCITY : REGULAR_VELOCITY);
    }
}
