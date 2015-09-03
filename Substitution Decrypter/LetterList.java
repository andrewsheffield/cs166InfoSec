
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * LetterList
 * @author sheff
 */
public class LetterList {
    
    ArrayList<Letter> letters = new ArrayList<>();
    ArrayList<Letter> triplePattern = new ArrayList<>();
    String cipherText;
    
    public LetterList(String aCipherText) {
        this.cipherText = aCipherText.toUpperCase();
        this.buildLettersList();
    }

    private void buildLettersList() {
        String cipherLow = this.cipherText.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String[] cipherArray = cipherLow.split("");
        Arrays.sort(cipherArray);
        
        String previous = "";
        for (String s : cipherArray) {
            if (s.equals(previous)) continue;
            String substring    = cipherLow.replaceAll("[^"+ s + "]", "");

            Letter l = new Letter(s, substring.length());
            letters.add(l);
            previous = s;
        }
        
        cipherArray = cipherLow.split("");
        ArrayList<String> triplesFound = new ArrayList<>();
        
        for (int i = 0; i < cipherArray.length-2; i++) {
            String tripString = cipherArray[i] + cipherArray[i+1] + cipherArray[i+2];
            if (!triplesFound.contains(tripString)) {
                triplesFound.add(tripString);
                int count = 0;
                for (int j = 0; j < cipherLow.length()-2; j++) {
                    String subString = cipherLow.substring(j, j+3);
                    if (subString.equals(tripString)) count++;
                }
                
                Letter l = new Letter(tripString, count);
                triplePattern.add(l);
            }
        }
        
        
        
        letters.sort(null);
        triplePattern.sort(null);
        
        
    }
    
    @Override
    public String toString()    { return letters.toString(); }

    public void print() {
        System.out.println(this.letters);
        System.out.println(this.triplePattern);
    }
    
    public void tryKey(String key) {
        this.cipherText = this.cipherText.toUpperCase();
        String[] keyArray = key.split("");
        String result = this.cipherText;
        
        for (int i = 0; i < keyArray.length && i < letters.size(); i++) {
            result = result.replaceAll(letters.get(i).letter.toUpperCase(), keyArray[i]);
        }
        System.out.println(result.toUpperCase());
    }
    
}

