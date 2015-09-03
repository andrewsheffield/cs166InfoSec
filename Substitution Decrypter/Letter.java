/**
 * Letter
 * @author sheff
 */
public class Letter implements Comparable<Letter>{
    String  letter;
    int     frequency;
    
    public Letter (String aLetter, int aFrequency) {
        this.letter     = aLetter;
        this.frequency  = aFrequency;
    }
    
    /**
     * 
     * @return char letter
     */
    public String getLetter()     { return this.letter; }
    
    /**
     * 
     * @return int frequency of the letter
     */
    public int getFrequency()   { return this.frequency; }
    
    /**
     * Increment the frequency of the letter
     */
    public void incFrequency()  { this.frequency++; }
    
    @Override
    public String toString()    { return "(" + this.letter + ": " + this.frequency + ")"; }
    
    @Override
    public int compareTo(Letter other) {
        return other.frequency - this.frequency;
    }
    
}
